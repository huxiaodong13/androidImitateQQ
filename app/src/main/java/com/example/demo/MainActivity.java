package com.example.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;//登录按钮
    private EditText etUserName;//用户名
    private EditText etPwd;//密码
    private TextView tvQues;//无法登陆
    private TextView tvNew;//新用户注册
    private TextView deal;//协议
    private String userName,pwd;
    private Boolean isOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isOk = false;
        deal = (TextView)this.findViewById(R.id.deal);
        deal.setOnClickListener(this);
        init();
    }

    private void init() {
        //找控件
        btnLogin = (Button)this.findViewById(R.id.activity_main_btnLogin);
        etUserName = (EditText)this.findViewById(R.id.activity_main_etUser);
        etPwd = (EditText)this.findViewById(R.id.activity_main_etPwd);
        tvQues = (TextView)this.findViewById(R.id.activity_main_tvQues);
        tvNew = (TextView)this.findViewById(R.id.activity_main_tvNew);
        //设置点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //并实现登陆功能
                //Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                //开始登录，获取用户名和密码 getText().toString().trim();
                userName = etUserName.getText().toString().trim();
                pwd = etPwd.getText().toString().trim();
                UserServer uService=new UserServer(MainActivity.this);
                // TextUtils.isEmpty
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if(uService.isUser(userName)!=true) {
                    Toast.makeText(MainActivity.this, "不存在该用户", Toast.LENGTH_SHORT).show();
                } else if(isOk!=true) {
                    Toast.makeText(MainActivity.this, "请先同意登录协议", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("TAG",userName+"_"+pwd);
                    boolean flag=uService.login(userName, pwd);
                    if(flag){
                        Log.i("TAG","username+pwd");
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        //销毁登录界面
                        MainActivity.this.finish();
                        //跳转到主界面，登录成功的状态传递到 MainActivity 中
                        startActivity(new Intent(MainActivity.this, ChangeHeadActivity.class));
                    }else{
                        Log.i("TAG","username");
                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        tvNew.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        tvQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到更改密码界面，并实现更新密码功能
                //startActivity(new Intent(MainActivity.this,LostFindActivity.class));
                Intent intent=new Intent(MainActivity.this,LostFindActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    /**
     * 注册成功的数据返回至此
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    @Override
    //显示数据， onActivityResult
    //startActivityForResult(intent, 1); 从注册界面中获取数据
    //int requestCode , int resultCode , Intent data
    // RegisterActivity -> startActivityForResult -> onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                //设置用户名到 et_user_name 控件
                etUserName.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                etUserName.setSelection(userName.length());
            }
        }
    }

    @Override
    public void onClick(View v) {
        //创建一个builder对象
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //设置builder对象的属性
        builder.setTitle("协议");
        builder.setIcon(R.mipmap.logo);
        builder.setMessage("默认为不同意该协议，请问你同意此协议吗？");
        builder.setCancelable(false);

        //设置两个按钮对应的事件
        builder.setPositiveButton("同意", new  DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isOk = true;
            }
        });
        builder.setNegativeButton("不同意",null);
        //创建AlertDialog对象
        AlertDialog alertDialog=builder.create();
        //显示对话框
        alertDialog.show();
    }
}
