package com.aswdc_dsconversion.bal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_dsconversion.Bean.Bean_Postfix;
import com.aswdc_dsconversion.Bean.Bean_String;
import com.aswdc_dsconversion.DBHelper.Dal_EvaluatePostfix;
import com.aswdc_dsconversion.DBHelper.Dal_StringHistory;

import java.util.ArrayList;

public class Bal_StringHistory {
    static Bal_StringHistory instance;
    private Bal_StringHistory(){

    }
    public static Bal_StringHistory getInstance(){
        if (instance==null){
            instance = new Bal_StringHistory();
        }
        return instance;
    }


    public ArrayList<Bean_String> getString() {
        ArrayList<Bean_String> stringsList = new ArrayList<>();
        Cursor cursor = Dal_StringHistory.getInstance().getString();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Bean_String beanString = new Bean_String();
            //beanExamples.setSrNo(cursor.getInt(cursor.getColumnIndex(_Sr))+""));
            beanString.setSrNo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Dal_StringHistory._Sr))+""));
            beanString.setUserString(cursor.getString(cursor.getColumnIndex(Dal_StringHistory._UserString)));
            beanString.setAnswer(cursor.getString(cursor.getColumnIndex(Dal_StringHistory._Answer)));
            beanString.setConversionType(cursor.getString(cursor.getColumnIndex(Dal_StringHistory._ConversionType)));
            stringsList.add(beanString);
            cursor.moveToNext();
        }
        cursor.close();
        return stringsList;
    }
}
