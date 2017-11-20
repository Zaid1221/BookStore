package com.example.zaid.bookstore;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Zaid Javaid on 11/19/2017.
 */

public class BookButton extends Button
{
    private Book book;

    public BookButton(Context context, Book newbook ) {
        super( context );
        book = newbook;
    }
}
