package com.example.zaid.bookstore;

import android.widget.ProgressBar;

/**
 * Created by Zaid on 11/17/2017.
 */

public class Book
{
    private int id;
    private String title;
    private int ISBN;
    private double price;

    public Book(int newID, String newTitle, int newISBN, double newPrice)
    {
        setID(newID);
        setTitle(newTitle);
        setISBN(newISBN);
        setPrice(newPrice);
    }

    public void setID(int newID)
    {
        id = newID;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    public void setISBN(int newISBN)
    {
        ISBN = newISBN;
    }

    public void setPrice(double newPrice)
    {
        price = newPrice;
    }

    public int getID()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public int getISBN()
    {
        return ISBN;
    }

    public double getPrice()
    {
        return price;
    }

    public String toString()
    {
        return title + ";" + ISBN + ";" + price;
    }

}
