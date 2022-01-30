package com.aswdc_dsconversion.DBHelper;

import com.aswdc_dsconversion.AppController;
import com.aswdc_dsconversion.Utility.Constants;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DB_Helper extends SQLiteAssetHelper {
    public DB_Helper(){
        super(AppController.getInstance(), Constants.dbName, null, Constants.dbVersion);
    }
}
