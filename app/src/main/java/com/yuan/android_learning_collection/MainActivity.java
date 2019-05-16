package com.yuan.android_learning_collection;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<ListModel> dataArr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1、数据源
        {
            ListModel model = new ListModel("[1]LinearLayout","线性布局，示例演示","");
            dataArr.add(model);

            ListModel model1 = new ListModel("[2]RelativeLayout","相对布局，示例演示","");
            dataArr.add(model1);

            ListModel model2 = new ListModel("[3]TableLayout","表格布局，示例演示","");
            dataArr.add(model2);

            ListModel model3 = new ListModel("[4]FrameLayout","帧布局，示例演示","");
            dataArr.add(model3);

            ListModel model4 = new ListModel("[5]GridLayout","网格布局，示例演示","");
            dataArr.add(model4);

            ListModel model5 = new ListModel("[6]AbsoluteLayout","绝对布局，示例演示","");
            dataArr.add(model5);
        }


        //2、获取列表，添加头部视图，数据适配
        {
            listView = findViewById(R.id.listView);

            ViewGroup group = (ViewGroup)getLayoutInflater().inflate(R.layout.list_head_layout,null);
            Button searchBtn = group.findViewById(R.id.button);
            final EditText editText = group.findViewById(R.id.editText) ;
            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = editText.getText().toString();
                    int index = Integer.valueOf(str);
                    if (index > 0){
                        listView.setSelection(Integer.valueOf(str));
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            });

            listView.addHeaderView(group);

            //1、简单适配器方法
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataArr);
            //listView.setAdapter(adapter);

            //2、自定义适配器方法
            listView.setAdapter(new ListAdapter(this,dataArr));
        }

        //3、列表点击
        {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListModel model = dataArr.get(position-1);
                    Toast.makeText(MainActivity.this, "点击了" + model.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
