package com.example.alicja.aplikacjadietetyczna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alicja on 2018-05-13.
 */

public class DietInfoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dietInfo";
    private static final int DB_VERSION = 1;

    public DietInfoDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "WEIGHT REAL,"
                + "HEIGHT REAL,"
                + "AGE INTEGER,"
                + "SEX TEXT,"
                + "GOAL TEXT,"
                + "NUMBER INTEGER,"
                + "PREFER TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void insertUser(DietInfoDatabaseHelper db, float weight, float height, int age,
                           String sex, String goal, int number, String prefer) {
        ContentValues userValues = new ContentValues();
        userValues.put("WEIGHT", weight);
        userValues.put("HEIGHT", height);
        userValues.put("AGE", age);
        userValues.put("SEX", sex);
        userValues.put("GOAL", goal);
        userValues.put("NUMBER", number);
        userValues.put("PREFER", prefer);
        // db.insert("USER",null,userValues);
    }
    public User getUser(){
        User user=new User();
        SQLiteDatabase db=getReadableDatabase();
        String[]kolumn={"weight","height","age","sex","goal","number","prefer"};
        Cursor kursor=db.query("USER",kolumn,null,null,null,null,null,null);
        if(kursor!=null){
            kursor.moveToLast();
            user.setWeight(Double.parseDouble(kursor.getString(1)));
            user.setHeight(Double.parseDouble(kursor.getString(2)));
            user.setAge(Integer.parseInt(kursor.getString(5)));
            user.setSex(kursor.getString(4));
            user.setGoal(kursor.getString(5));
            user.setNumber(Integer.parseInt(kursor.getString(6)));
            user.setPrefer(kursor.getString(7));
        }
        return user;
    }
}
