package com.yuan.android_learning_collection;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.AdapterView;

import com.yuan.android_learning_collection.Layout.AbsolutLayoutActivity;
import com.yuan.android_learning_collection.Layout.FrameLayoutActivity;
import com.yuan.android_learning_collection.Layout.GridLayoutActivity;
import com.yuan.android_learning_collection.Layout.LinearLayoutActivity;
import com.yuan.android_learning_collection.Layout.RelativeLayoutActivity;
import com.yuan.android_learning_collection.Layout.TableLayoutActivity;
import com.yuan.android_learning_collection.Home.ListAdapter;
import com.yuan.android_learning_collection.Home.ListModel;
import com.yuan.android_learning_collection.ListView.DynHeightListActivity;

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

            ListModel model6 = new ListModel("[7]ListView动态行高","示例演示","");
            dataArr.add(model6);
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
                    switch (position) {
                        case 1:
                            this.LinearLayoutTest();
                            break;
                        case 2:
                            this.RelativeLayout();
                            break;
                        case 3:
                            this.TableLayout();
                            break;
                        case 4:
                            this.FrameLayout();
                            break;
                        case 5:
                            this.GridLayout();
                            break;
                        case 6:
                            this.AbsoluteLayout();
                            break;
                        case 7:
                            this.dynHeight();
                            break;

                            default:
                                ListModel model = dataArr.get(position-1);
                                Toast.makeText(MainActivity.this, "点击了" + model.getTitle(), Toast.LENGTH_SHORT).show();
                                break;

                    }
                }

                private void LinearLayoutTest() {
                    System.out.println("线性布局");
                    Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
                    startActivity(intent);
                }

                private void RelativeLayout() {
                    System.out.println("相对布局");
                    Intent intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                    startActivity(intent);
                }

                private void TableLayout() {
                    System.out.println("表格布局");
                    Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                    startActivity(intent);
                }

                private void FrameLayout() {
                    System.out.println("帧布局");
                    Intent intent = new Intent(MainActivity.this, FrameLayoutActivity.class);
                    startActivity(intent);
                }

                private void GridLayout() {
                    System.out.println("网格布局");
                    Intent intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                    startActivity(intent);
                }

                private void AbsoluteLayout() {
                    System.out.println("绝对布局");
                    Intent intent = new Intent(MainActivity.this, AbsolutLayoutActivity.class);
                    startActivity(intent);
                }

                private void dynHeight() {
                    System.out.println("动态行高");
                    Intent intent = new Intent(MainActivity.this, DynHeightListActivity.class);
                    startActivity(intent);
                }

            });
        }
    }
}
