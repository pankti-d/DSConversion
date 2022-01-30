package com.aswdc_dsconversion.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Dal_EvaluatePostfix extends DB_Helper {
    static Dal_EvaluatePostfix instance;
    public static final String EvaluatePostfix = "EvaluatePostfix";
    public static final String _Sr = (String)"SrNo";
    public static final String _UserExp = "UserExp";
    public static final String _Answer="Answer";

    private Dal_EvaluatePostfix(){
        super();
    }

    public static Dal_EvaluatePostfix getInstance(){
        if(instance==null){
            instance = new Dal_EvaluatePostfix();
        }
        return instance;
    }

    public Cursor getPostfix(){
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuery = "Select * from EvaluatePostfix order by SrNo DESC";
        Cursor cursor = db.rawQuery(strQuery, null);
        return cursor;
    }

    public void insertContent(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EvaluatePostfix, null, cv);
        cv.clear();
        db.close();
    }

    public void deleteString(String Sr){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EvaluatePostfix, _Sr + "=?", new String[]{Sr});
        db.close();
    }
}
