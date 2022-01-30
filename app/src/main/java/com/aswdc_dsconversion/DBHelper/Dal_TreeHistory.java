package com.aswdc_dsconversion.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class Dal_TreeHistory extends DB_Helper {
    static Dal_TreeHistory instance;
    public static final String TreeHistory = "TreeHistory";
    public static final String _Sr = (String)"SrNo";
    public static final String _UserTree = "UserTree";
    public static final String _inorder="InOrder";

    private Dal_TreeHistory(){
        super();
    }
    public static Dal_TreeHistory getInstance()
    {
        if (instance==null)
        {
            instance=new Dal_TreeHistory();
        }
        return instance;
    }
    public Cursor getTree()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuery ="Select * from TreeHistory order by SrNo DESC";
        Cursor cursor = db.rawQuery(strQuery, null);
        return cursor;
    }

    public void insertContent(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TreeHistory, null, cv);
        cv.clear();
        db.close();
    }

    public void deleteTree(String Sr){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TreeHistory, _Sr + "=?", new String[]{Sr});
        db.close();
    }

}
