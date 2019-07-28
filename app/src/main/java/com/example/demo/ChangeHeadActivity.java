package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChangeHeadActivity extends Activity {

    private Button button,exit;//修改头像、退出登陆按钮
    private ImageButton find,message,mine;//发现、消息、我的等
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changehead_layout);
        button= (Button)this.findViewById(R.id.btn);//获取
        find = (ImageButton)this.findViewById(R.id.find);
        message = (ImageButton)this.findViewById(R.id.message);
        mine = (ImageButton)this.findViewById(R.id.mine);
        exit = (Button)this.findViewById(R.id.tuichu);
        button.setOnClickListener(new View.OnClickListener() { //为按钮创建单机事件
            @Override
            public void onClick(View v) {
                //创建Intent对象
                Intent intent=new Intent(ChangeHeadActivity.this,HeadActivity.class);
                startActivityForResult(intent, 0x11);//启动intent对应的Activity
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeHeadActivity.this,StarActivity.class));
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChangeHeadActivity.this,FriendActivity.class));
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeHeadActivity.this,MineActivity.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() { //为按钮创建单机事件
            @Override
            public void onClick(View v) {
                //退出成功后关闭此页面进入主页
                Intent data = new Intent();
                //datad.putExtra( ); name , value ;
                data.putExtra("isLogin", false);
                ChangeHeadActivity.this.finish();
                startActivity(new Intent(ChangeHeadActivity.this,MainActivity.class));//启动intent对应的Activity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x11 && resultCode==0x11){	//判断是否为待处理的结果
            Bundle bundle=data.getExtras();		//获取传递的数据包
            int imageId=bundle.getInt("imageId");	//获取选择的头像ID
            ImageView iv=(ImageView)findViewById(R.id.imageView);	//获取布局文件中添加的ImageView组件
            iv.setImageResource(imageId);	//显示选择的头像
        }
    }
}

