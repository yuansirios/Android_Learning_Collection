package com.yuan.android_learning_collection.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuan.android_learning_collection.Home.ListModel;
import com.yuan.android_learning_collection.R;

import java.util.List;

public class DynHeightListAdapter extends BaseAdapter {
    private Context context;
    private List<ListModel> list;

    public DynHeightListAdapter(Context context, List<ListModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListModel model = list.get(i);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewGroup group = (ViewGroup)inflater.inflate(R.layout.dynheightlist_item_activity_layout,null);

        TextView titleLabel = group.findViewById(R.id.title);
        titleLabel.setText(model.getTitle());

        TextView valueLabel = group.findViewById(R.id.value);
        valueLabel.setText(model.getValue());

        return group;
    }
}
