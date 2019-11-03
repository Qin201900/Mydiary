package com.swufe.mydiary;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {


    private List<String> listItems;
    private List<String> listItemTimes;


    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<String> listItems, List<String> times){
        this.listItems = listItems;
        this.listItemTimes = times;
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addListItem(String item, String time){//往列表里添加数据
        listItems.add(item);
        listItemTimes.add(time);
    }

    public void removeListItem(int position){//删除指定位置的数据
        listItems.remove(position);
        listItemTimes.remove(position);
    }

    public int getCount() {//获取列表个数
        // TODO Auto-generated method stub
        return listItems.size();
    }

    public Object getItem(int position) {//按索引获取内容
        // TODO Auto-generated method stub
        return listItems.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {//显示数据
        // TODO Auto-generated method stub

        if(convertView == null){
            convertView = inflater.inflate(R.layout.mydiary_list_item,null);
        }

        TextView text = (TextView)convertView.findViewById(R.id.listItem);
        text.setText(listItems.get(position));

        TextView time = (TextView)convertView.findViewById(R.id.listItemTime);
        String datetime = DateFormat.format("yyyy-MM-dd",
                Long.parseLong(listItemTimes.get(position))).toString();
        time.setText(datetime);
        return convertView;
    }
}
