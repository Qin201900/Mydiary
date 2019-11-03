package com.swufe.mydiary;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseManage {

    private Context mContext = null;

    private SQLiteDatabase mSQLiteDatabase = null;//操作数据库对象
    private DatabaseHelper dh = null;//创建数据库对象

    private String dbName = "mydiary.db";
    private int dbVersion = 1;

    public DatabaseManage(Context context){
        mContext = context;
    }
    public void open(){//打开数据库
        try{
            dh = new DatabaseHelper(mContext, dbName, null, dbVersion);
            if(dh == null){
                Log.v("msg", "is null");
                return ;
            }
            mSQLiteDatabase = dh.getWritableDatabase();
        }catch(SQLiteException se){
            se.printStackTrace();
        }
    }

    public void close(){//关闭数据库
        mSQLiteDatabase.close();
        dh.close();
    }

    public Cursor selectAll(){//获取日记列表
        Cursor cursor = null;
        try{
            String sql = "select * from record";
            cursor = mSQLiteDatabase.rawQuery(sql, null);
        }catch(Exception ex){
            ex.printStackTrace();
            cursor = null;
        }
        return cursor;
    }
    public Cursor selectById(int id){
        Cursor cursor = null;
        try{
            String sql = "select * from record where _id='" + id +"'";
            cursor = mSQLiteDatabase.rawQuery(sql, null);
        }catch(Exception ex){
            ex.printStackTrace();
            cursor = null;
        }
        return cursor;
    }

    public long insert(String title, String content){//插入数据

        long datetime = System.currentTimeMillis();
        long l = -1;
        try{
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("content", content);
            cv.put("time", datetime);
            l = mSQLiteDatabase.insert("record", null, cv);
        }catch(Exception ex){
            ex.printStackTrace();
            l = -1;
        }
        return l;

    }

    public int delete(long id){//删除数据
        int affect = 0;
        try{
            affect = mSQLiteDatabase.delete("record", "_id=?", new String[]{id+""});
        }catch(Exception ex){
            ex.printStackTrace();
            affect = -1;
        }
        return affect;
    }
    public int update(int id, String title, String content){   //更新数据
        int affect = 0;
        try{
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("content", content);
            String w[] = {id+""};
            affect = mSQLiteDatabase.update("record", cv, "_id=?", w);
        }catch(Exception ex){
            ex.printStackTrace();
            affect = -1;
        }
        return affect;
    }
}
