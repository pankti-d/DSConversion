package com.aswdc_dsconversion.bal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_dsconversion.Bean.Bean_Tree;
import com.aswdc_dsconversion.DBHelper.Dal_TreeHistory;

import java.util.ArrayList;

public class Bal_TreeHistory {
    static Bal_TreeHistory instance;
    private Bal_TreeHistory(){

    }
    public static Bal_TreeHistory getInstance(){
        if (instance==null){
            instance = new Bal_TreeHistory();
        }
        return instance;
    }
    public ArrayList<Bean_Tree> getString() {
        ArrayList<Bean_Tree> treeList = new ArrayList<>();
        Cursor cursor = Dal_TreeHistory.getInstance().getTree();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Bean_Tree beanTree = new Bean_Tree();
            //beanExamples.setSrNo(cursor.getInt(cursor.getColumnIndex(_Sr))+""));
            beanTree.setSrNo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Dal_TreeHistory._Sr))+""));
            beanTree.setUserTree(cursor.getString(cursor.getColumnIndex(Dal_TreeHistory._UserTree)));
            beanTree.setInOrder(cursor.getString(cursor.getColumnIndex(Dal_TreeHistory._inorder)));
            treeList.add(beanTree);
            cursor.moveToNext();
        }
        cursor.close();
        return treeList;
    }
}
