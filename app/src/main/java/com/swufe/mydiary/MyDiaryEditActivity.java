package com.swufe.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class MyDiaryEditActivity extends Activity {

    public static final int CHECK_STATE = 0;
    public static final int EDIT_STATE = 1;
    public static final int ALERT_STATE = 2;

    private int state = -1;
    private Button complete;//编辑完成
    private EditText title;
    private EditText content;
    private DatabaseManage dm = null;

    private String id = "";
    private String titleText = "";
    private String contentText = "";
    private String timeText = "";

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mydiary);

        Intent intent = getIntent();
        state = intent.getIntExtra("state", EDIT_STATE);


        complete = (Button) findViewById(R.id.editComplete);
        title = (EditText) findViewById(R.id.editTitle);
        content = (EditText) findViewById(R.id.editContent);

        complete.setOnClickListener(new EditCompleteListener());
        content.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                content.setSelection(content.getText().toString().length());
                return false;
            }
        });

        if (state == ALERT_STATE) {//修改状态，获取用户输入的数据
            id = intent.getStringExtra("id");
            titleText = intent.getStringExtra("title");
            contentText = intent.getStringExtra("content");
            timeText = intent.getStringExtra("time");

            title.setText(titleText);
            content.setText(contentText);
        }
        dm = new DatabaseManage(this);
    }

    public class EditCompleteListener implements OnClickListener {//监听完成按钮
        public void onClick(View v) {
            // TODO Auto-generated method stub
            titleText = title.getText().toString();
            contentText = content.getText().toString();

            try {
                dm.open();

                if (state == EDIT_STATE)//新增数据状态
                    dm.insert(titleText, contentText);
                if (state == ALERT_STATE)//修改数据状态
                    dm.update(Integer.parseInt(id), titleText, contentText);

                dm.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Intent intent = new Intent();
            intent.setClass(MyDiaryEditActivity.this, MainActivity.class);
            MyDiaryEditActivity.this.startActivity(intent);
            //保存数据完毕
        }
    }
}
