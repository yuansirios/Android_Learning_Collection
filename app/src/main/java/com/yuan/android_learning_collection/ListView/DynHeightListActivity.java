package com.yuan.android_learning_collection.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yuan.android_learning_collection.Home.ListAdapter;
import com.yuan.android_learning_collection.Home.ListModel;
import com.yuan.android_learning_collection.R;

import java.util.ArrayList;
import java.util.List;

import static com.yuan.android_learning_collection.R.*;

public class DynHeightListActivity extends Activity {
    private ListView listView;
    private List<ListModel> dataArr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.dynheightlist_activity_layout);

        {
            for (int i = 0 ; i < 20 ; i++){
                ListModel model = new ListModel("["+i+"]LinearLayout","线性布局，示例演示","");
                if (i%2 == 0){
                    model = new ListModel("["+i+"]LinearLayout","线性布局，示例演示线性布局，示例演示线性布局，示例演示线性布局，示例演示线性布局，示例演示示线性布局，示例演示线性布局，示例演示线性布局，示例演示示线性布局，示例演示线性布局，示例演示线性布局，示例演示示线性布局，示例演示线性布局，示例演示线性布局，示例演示示线性布局，示例演示线性布局，示例演示线性布局，示例演示","");
                }
                if (i%3 == 0){
                    model = new ListModel("["+i+"]LinearLayout","线性布局，示例演示线性布局，示例演示示示线性布局，示例演示线性布局示例演示线性布局，示例演示示线性布局，示例演示线性布局，示例演示线性布局，示例演示","");
                }
                dataArr.add(model);
            }
        }

        listView = findViewById(id.listView);
        listView.setAdapter(new DynHeightListAdapter(this,dataArr));
    }
}
