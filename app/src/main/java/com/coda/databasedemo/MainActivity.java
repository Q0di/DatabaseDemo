package com.coda.databasedemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private EditText dbET;
    private TextView dbTV;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbET = (EditText) findViewById(R.id.dbET);
        dbTV = (TextView) findViewById(R.id.dbTV);

        dbHandler = new MyDBHandler(this,null,null,1);
        printDB();


    }

    public void deleteClick(View view) {
        String inputText = dbET.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDB();

    }

    public void addClick(View view) {
        Products products = new Products(dbET.getText().toString());
        dbHandler.addProduct(products);
        printDB();
    }

    public void printDB(){
        List<Products> dbSTR = dbHandler.dbToString();
        String log="";

        for(Products pn:dbSTR){

            log += "\nID: "+ pn.get_id() + " Name: "+ pn.get_productName()+"\n";

        }
        dbTV.setText(log);
        dbET.setText("");
    }

}
