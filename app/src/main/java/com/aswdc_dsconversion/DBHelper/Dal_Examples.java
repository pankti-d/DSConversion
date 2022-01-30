package com.aswdc_dsconversion.DBHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dal_Examples extends DB_Helper {
    static Dal_Examples instance;
    public static final String _Sr = (String)"SrNo";
    public static final String _Example = "Example";
    public static final String _html= "HTMLTable";

    public static Dal_Examples getInstance(){
        if (instance==null)
        {
            instance=new Dal_Examples();
        }
        return instance;
    }
    public Cursor getExamples()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuery ="Select * from Examples";
        Cursor cursor = db.rawQuery(strQuery, null);
        return cursor;
    }
}
