package com.aswdc_dsconversion.bal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_dsconversion.Bean.Bean_Postfix;
import com.aswdc_dsconversion.DBHelper.Dal_EvaluatePostfix;

import java.util.ArrayList;

public class Bal_EvaluatePostfix {
    static Bal_EvaluatePostfix instance;
    private Bal_EvaluatePostfix(){

    }
    public static Bal_EvaluatePostfix getInstance(){
        if (instance==null){
            instance = new Bal_EvaluatePostfix();
        }
        return instance;
    }

    public ArrayList<Bean_Postfix> getPostfix() {
        ArrayList<Bean_Postfix> postfixList = new ArrayList<>();
        Cursor cursor = Dal_EvaluatePostfix.getInstance().getPostfix();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Bean_Postfix beanPostfix = new Bean_Postfix();
            //beanExamples.setSrNo(cursor.getInt(cursor.getColumnIndex(_Sr))+""));
            beanPostfix.setSrNo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Dal_EvaluatePostfix._Sr))+""));
            beanPostfix.setUserExp(cursor.getString(cursor.getColumnIndex(Dal_EvaluatePostfix._UserExp)));
            beanPostfix.setAnswer(cursor.getString(cursor.getColumnIndex(Dal_EvaluatePostfix._Answer)));
            postfixList.add(beanPostfix);
            cursor.moveToNext();
        }
        cursor.close();
        return postfixList;
    }
}
