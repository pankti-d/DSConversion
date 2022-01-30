package com.aswdc_dsconversion.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class Dal_StringHistory extends DB_Helper {
    static Dal_StringHistory instance;
    public static final String StringHistory = "StringHistory";
    public static final String _Sr = (String)"SrNo";
    public static final String _UserString = "UserString";
    public static final String _Answer="Answer";
    public static final String _ConversionType = "ConversionType";

    private Dal_StringHistory(){
        super();
    }
    public static Dal_StringHistory getInstance(){
        if(instance==null){
            instance = new Dal_StringHistory();
        }
        return instance;
    }

    public Cursor getString(){
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuery = "Select * from StringHistory order by SrNo DESC";
        Cursor cursor = db.rawQuery(strQuery, null);
        return cursor;
    }

    public void insertContent(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(StringHistory, null, cv);
        cv.clear();
        db.close();
    }

    public void deleteString(String Sr){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(StringHistory, _Sr + "=?", new String[]{Sr});
        db.close();
    }
}
