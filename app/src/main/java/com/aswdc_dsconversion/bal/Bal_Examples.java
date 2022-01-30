package com.aswdc_dsconversion.bal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_dsconversion.Bean.Bean_Examples;
import com.aswdc_dsconversion.DBHelper.Dal_Examples;

import java.util.ArrayList;

public class Bal_Examples {
    static Bal_Examples instance;
    private Bal_Examples(){

    }
    public static Bal_Examples getInstance(){
        if (instance==null){
            instance = new Bal_Examples();
        }
        return instance;
    }
    public ArrayList<Bean_Examples> getExamples() {
        ArrayList<Bean_Examples> exampleList = new ArrayList<>();
        Cursor cursor = Dal_Examples.getInstance().getExamples();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Bean_Examples beanExamples = new Bean_Examples();
            //beanExamples.setSrNo(cursor.getInt(cursor.getColumnIndex(_Sr))+""));
            beanExamples.setSrNo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Dal_Examples._Sr))+""));
            beanExamples.setExample(cursor.getString(cursor.getColumnIndex(Dal_Examples._Example)));
            beanExamples.setHtml(cursor.getString(cursor.getColumnIndex(Dal_Examples._html)));
            exampleList.add(beanExamples);
            cursor.moveToNext();
        }
        cursor.close();
        return exampleList;
    }

}
