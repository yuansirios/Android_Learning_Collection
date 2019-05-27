package com.yuan.android_learning_collection;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yuan.android_learning_collection.EditText.EditTextActivity;
import com.yuan.android_learning_collection.Layout.AbsolutLayoutActivity;
import com.yuan.android_learning_collection.Layout.FrameLayoutActivity;
import com.yuan.android_learning_collection.Layout.GridLayoutActivity;
import com.yuan.android_learning_collection.Layout.LinearLayoutActivity;
import com.yuan.android_learning_collection.Layout.RelativeLayoutActivity;
import com.yuan.android_learning_collection.Layout.TableLayoutActivity;
import com.yuan.android_learning_collection.Home.ListAdapter;
import com.yuan.android_learning_collection.Home.ListModel;
import com.yuan.android_learning_collection.ListView.DynHeightListActivity;
import com.yuan.android_learning_collection.TextView.TextViewActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<ListModel> dataArr = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1、数据源
        {
            ListModel model = new ListModel("[1]LinearLayout","线性布局，示例演示","LinearLayout");
            dataArr.add(model);

            ListModel model1 = new ListModel("[2]RelativeLayout","相对布局，示例演示","RelativeLayout");
            dataArr.add(model1);

            ListModel model2 = new ListModel("[3]TableLayout","表格布局，示例演示","TableLayout");
            dataArr.add(model2);

            ListModel model3 = new ListModel("[4]FrameLayout","帧布局，示例演示","FrameLayout");
            dataArr.add(model3);

            ListModel model4 = new ListModel("[5]GridLayout","网格布局，示例演示","GridLayout");
            dataArr.add(model4);

            ListModel model5 = new ListModel("[6]AbsoluteLayout","绝对布局，示例演示","AbsoluteLayout");
            dataArr.add(model5);

            ListModel model6 = new ListModel("[7]ListView动态行高","示例演示","dynHeight");
            dataArr.add(model6);

            ListModel model7 = new ListModel("[8]TextView详解","示例演示","textView");
            dataArr.add(model7);

            ListModel model8 = new ListModel("[9]EditText详解","示例演示","editText");
            dataArr.add(model8);
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

                    try {
                        ListModel model = dataArr.get(position-1);
                        Method method = this.getClass().getMethod(model.getEvent());
                        method.invoke(this);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                public void LinearLayout() {
                    System.out.println("线性布局");
                    Intent intent = new Intent(MainActivity.this,LinearLayoutActivity.class);
                    startActivity(intent);
                }

                public void RelativeLayout() {
                    System.out.println("相对布局");
                    Intent intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                    startActivity(intent);
                }

                public void TableLayout() {
                    System.out.println("表格布局");
                    Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                    startActivity(intent);
                }

                public void FrameLayout() {
                    System.out.println("帧布局");
                    Intent intent = new Intent(MainActivity.this, FrameLayoutActivity.class);
                    startActivity(intent);
                }

                public void GridLayout() {
                    System.out.println("网格布局");
                    Intent intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                    startActivity(intent);
                }

                public void AbsoluteLayout() {
                    System.out.println("绝对布局");
                    Intent intent = new Intent(MainActivity.this, AbsolutLayoutActivity.class);
                    startActivity(intent);
                }

                public void dynHeight() {
                    System.out.println("动态行高");
                    Intent intent = new Intent(MainActivity.this, DynHeightListActivity.class);
                    startActivity(intent);
                }

                public void textView(){
                    System.out.println("textView示例");
                    Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
                    startActivity(intent);
                }

                public void editText(){
                    System.out.println("editText示例");
                    Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
                    startActivity(intent);
                }

            });
        }
    }
}
