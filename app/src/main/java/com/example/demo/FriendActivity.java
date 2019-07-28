package com.example.demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_layout);
        TextView back = (TextView)this.findViewById(R.id.back);
        ListView listview = (ListView)this.findViewById(R.id.listview); // 获取列表视图
        int[] imageId = new int[]{R.mipmap.head2, R.mipmap.head7, R.mipmap.headb,
                R.mipmap.head7, R.mipmap.head1, R.mipmap.head3,
                R.mipmap.heada, R.mipmap.head3, R.mipmap.head4,
        }; // 定义并初始化保存图片id的数组
        String[] title = new String[]{"小一", "小二", "小三", "小四", "小五",
                "小六", "小七", "小八", "小九"}; // 定义并初始化保存列表项文字的数组
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
            map.put("image", imageId[i]);
            map.put("名字", title[i]);
            listItems.add(map); // 将map对象添加到List集合中
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.singefriend_layout, new String[] { "名字", "image" }, new int[] {
                R.id.title, R.id.image }); // 创建SimpleAdapter
        listview.setAdapter(adapter); // 将适配器与ListView关联
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = ( Map<String, Object> )parent.getItemAtPosition(position); 	//获取选择项的值
                Toast.makeText(FriendActivity.this,map.get("名字").toString(),Toast.LENGTH_SHORT).show();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendActivity.this.finish();
            }
        });
    }
}


