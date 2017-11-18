package com.example.zaid.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity
{
    private DataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = new DataBaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v)
    {
        EditText textTitle = (EditText) findViewById(R.id.value_title);
        EditText textISBN = (EditText) findViewById(R.id.value_isbn);
        EditText textPrice = (EditText) findViewById(R.id.value_price);

        String title = textTitle.getText().toString();
        String ISBNString = textISBN.getText().toString();
        String priceString = textPrice.getText().toString();

        try
        {
            int ISBN = Integer.parseInt(ISBNString);
            double price = Double.parseDouble(priceString);
            Book book = new Book(0, title, ISBN, price);
            db.insert(book);
            Toast.makeText( this, "Book added", Toast.LENGTH_LONG ).show( );
        }
        catch(NumberFormatException nfe)
        {
            Toast.makeText( this, "BOOK ERROR", Toast.LENGTH_LONG ).show( );
        }


        textTitle.setText( "" );
        textISBN.setText( "" );
        textPrice.setText("");
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
