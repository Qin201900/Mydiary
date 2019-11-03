package com.swufe.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class MyDiaryCheckActivity extends Activity {

    private TextView titleText = null;
    private TextView contentText = null;
    private TextView timeText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_mydiary);

        titleText = (TextView)findViewById(R.id.checkTitle);
        contentText = (TextView)findViewById(R.id.checkContent);
        timeText = (TextView)findViewById(R.id.checkTime);

        Intent intent = getIntent();//获取启动Activity时的intent对象

        String id = intent.getStringExtra("_id");
        String title= intent.getStringExtra("title");
        String time= intent.getStringExtra("time");
        String content = intent.getStringExtra("content");

        long t = Long.parseLong(time);

        String datetime = DateFormat.format("yyyy-MM-dd", t).toString();//获取系统时间并转为字符串对象

        this.titleText.setText(title);
        this.timeText.setText(datetime);
        this.contentText.setText(content);
    }

}
