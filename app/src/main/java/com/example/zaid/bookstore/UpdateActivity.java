package com.example.zaid.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private DataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = new DataBaseManager(this);
        updateView();
    }

    public void updateView()
    {
        ArrayList<Book> books = db.selectAll();

        if(books.size() > 0)
        {
            //create the layouts
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(books.size());
            grid.setColumnCount(4);

            //create array of elements
            TextView[] ids =  new TextView[books.size()];
            EditText[][] tip
        }
    }



}
