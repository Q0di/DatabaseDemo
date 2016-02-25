package com.coda.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 660161986 on 2/24/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    //variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME="productname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //query string
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCTNAME + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //add row
    public void addProduct(Products product){

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

    //delete row
    public void deleteProduct (String productName){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME +
                "=\"" + productName + "\";");
    }

   //To String
    public List<Products> dbToString(){
        List<Products> products = new ArrayList<Products>();
        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //CURSOR
        Cursor c = db.rawQuery(query,null);
        //go to first row in results

        c.moveToFirst();

        while(!c.isAfterLast()){
            Products product = cursorToProduct(c);
            products.add(product);
            c.moveToNext();

        }
        db.close();

    return products;
    }

    private Products cursorToProduct(Cursor c) {
        Products product = new Products();
        product.set_id(c.getInt(0));
        product.set_productName(c.getString(1));

        return product;

    }
}
