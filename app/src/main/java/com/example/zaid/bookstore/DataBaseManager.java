package com.example.zaid.bookstore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zaid on 11/17/2017.
 */


public class DataBaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "bookDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOOK = "book";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final String PRICE = "price";

    public DataBaseManager (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Poop", "Database created");
    }

    public void onCreate(SQLiteDatabase db)
    {
        String sqlCreate = "create table " + TABLE_BOOK + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TITLE;
        sqlCreate += " text, " + ISBN;
        sqlCreate += " integer, " + PRICE + " real )" ;

        db.execSQL(sqlCreate);

        Log.d("Poop", "table created");
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_BOOK );
        // Re-create tables
        onCreate( db );
    }

    public void insert(Book book)
    {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_BOOK;
        sqlInsert += " values( null, '" + book.getTitle();
        sqlInsert += "', '" + book.getISBN();
        sqlInsert += "', '" + book.getPrice() + "' )";

        db.execSQL( sqlInsert );
        db.close( );

        Log.d("Poop", "Inserted data");
    }
}

