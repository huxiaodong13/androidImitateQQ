package com.example.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LostFindActivity extends AppCompatActivity {

    //用户名，密码，再次输入的密码的控件
    private EditText et_userName;
    private EditText et_psw;
    private EditText et_psw_again;
    private EditText et_likes;
    private Button btn_register;
    private Button btn_cancel;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain,likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind_layout);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {

        btn_register = (Button) this.findViewById(R.id.update_btnOk);
        btn_cancel = (Button)this.findViewById(R.id.update_btnCancel);
        et_userName= (EditText) this.findViewById(R.id.update_etUser);
        et_psw= (EditText) this.findViewById(R.id.update_etPws);
        et_psw_again= (EditText) this.findViewById(R.id.update_etPassword);
        et_likes = (EditText) this.findViewById(R.id.update_etAnser);

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //获取输入在相应控件中的字符串
                userName = et_userName.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                pswAgain =  et_psw_again.getText().toString().trim();
                likes = et_likes.getText().toString().trim();

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LostFindActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(LostFindActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(LostFindActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(likes)) {
                    Toast.makeText(LostFindActivity.this, "请再次输入密保答案", Toast.LENGTH_SHORT).show();
                } else if(!psw.equals(pswAgain)){
                    Toast.makeText(LostFindActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("TAG",userName+"_"+psw+"_"+likes+"_");
                    UserServer uService=new UserServer(LostFindActivity.this);
                    if(uService.isUser(userName)!= true){
                        Toast.makeText(LostFindActivity.this, "不存在该用户", Toast.LENGTH_LONG).show();
                    }else{
                        String md5Psw = Md5Utils.md5(psw);//把密码用MD5加密
                        uService.updata(userName,md5Psw);
                        Toast.makeText(LostFindActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                        // 返回值到MainActivity显示
                        Intent data = new Intent();
                        data.putExtra("userName", userName);
                        setResult(RESULT_OK, data);
                        //RESULT_OK为Activity系统常量，状态码为-1，
                        // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                        //销毁登录界面
                        //跳转到主界面，登录成功的状态传递到 MainActivity 中
                        LostFindActivity.this.finish();
                    }

                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LostFindActivity.this.finish();
            }
        });
    }

}

