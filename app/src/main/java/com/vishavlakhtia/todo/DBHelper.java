package com.vishavlakhtia.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static  String DATABASE_NAME = "tasksData.db";
    public static final String TABLE_NAME = "TaskTable";
    public static final String COLUMN_ID = "id";
    public static final String Task_Column = "Title";
    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table TaskTable "+
                        "(id integer primary key AUTOINCREMENT,Title varchar)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop TABLE IF EXISTS TaskTable");
        onCreate(sqLiteDatabase);
    }
    public void insertTask(String Title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title",Title);
        db.insert("TaskTable",null,contentValues);
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int)DatabaseUtils.queryNumEntries(db,TABLE_NAME);

    }
    public void deleteTask(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TaskTable","id = ?",new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllTask()
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from TaskTable",null);
        res.moveToFirst();
        while (!res.isAfterLast())
        {
            arrayList.add(res.getString(res.getColumnIndex(Task_Column)));
            res.moveToNext();
        }
        res.close();
        return arrayList;
    }


}
