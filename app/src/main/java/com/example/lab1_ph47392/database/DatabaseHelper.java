package com.example.lab1_ph47392.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SalesManagement.db";
    private static final int DATABASE_VERSION = 1;

    // Table tb_cat
    private static final String TABLE_CAT = "tb_cat";
    private static final String CAT_ID = "id";
    private static final String CAT_NAME = "name";

    // Table tb_product
    private static final String TABLE_PRODUCT = "tb_product";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_CAT_ID = "id_cat";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCat = "CREATE TABLE " + TABLE_CAT + " (" +
                CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAT_NAME + " TEXT)";
        db.execSQL(createTableCat);

        String createTableProduct = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_NAME + " TEXT, " +
                PRODUCT_PRICE + " REAL, " +
                PRODUCT_CAT_ID + " INTEGER, " +
                "FOREIGN KEY(" + PRODUCT_CAT_ID + ") REFERENCES " + TABLE_CAT + "(" + CAT_ID + "))";
        db.execSQL(createTableProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        onCreate(db);
    }

}
