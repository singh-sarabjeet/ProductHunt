package com.example.sarabjeet.producthunt.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sarabjeet.producthunt.data.ProductDbContract.ProductData;

/**
 * Created by sarabjeet on 11/4/17.
 */


/**
 * This is the Helper method for the database to execute SQL queries on the DB
 */
public class ProductDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Prod.db";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_PROD_TABLE = "CREATE TABLE IF NOT EXISTS " + ProductData.TABLE_NAME +
                " (" + ProductData.COLUMN_ID + " TEXT NOT NULL, " +
                ProductData.COLUMN_PROD_NAME + " TEXT NOT NULL, " +
                ProductData.COLUMN_PROD_DESC + " TEXT NOT NULL, " +
                ProductData.COLUMN_PROD_CATERGORY + " TEXT NOT NULL, " +
                ProductData.COLUMN_PROD_IMG + " TEXT NOT NULL" + ");";

        db.execSQL(SQL_CREATE_PROD_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductData.TABLE_NAME);
        onCreate(db);
    }
}
