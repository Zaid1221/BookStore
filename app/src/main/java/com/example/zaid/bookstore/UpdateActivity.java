package com.example.zaid.bookstore;

import android.graphics.Interpolator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private DataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Book> books = db.selectAll();

        if (books.size() > 0) {
            //create the layouts
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(books.size());
            grid.setColumnCount(5);

            //create array of elements
            TextView[] ids = new TextView[books.size()];
            EditText[][] tip = new EditText[books.size()][3];
            Button[] buttons = new Button[books.size()];
            ButtonHandler bh = new ButtonHandler();

            //width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (Book book : books) {
                //create textview of the candy id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + book.getID());

                //create the 3 edittexts for the title, isbn, price
                tip[i][0] = new EditText(this); //title
                tip[i][1] = new EditText(this); //isbn
                tip[i][2] = new EditText(this); // price

                tip[i][0].setText(book.getTitle());
                tip[i][1].setText( "" + book.getISBN());
                tip[i][2].setText("" + book.getPrice());

                tip[i][0].setId(10 * book.getID());
                tip[i][1].setId(10 * book.getID() + 1);
                tip[i][2].setId(10 * book.getID() + 2);

                //create button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(book.getID());

                //set up event handeling
                buttons[i].setOnClickListener(bh);

                //add elements to grid
                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(tip[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(tip[i][1], (int) (width * .10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(tip[i][2], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .25),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }

            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            int bookID = v.getId();

            EditText titleET = (EditText) findViewById(10 * bookID);
            EditText isbnET = (EditText) findViewById(10 * bookID + 1);
            EditText priceET = (EditText) findViewById(10 * bookID + 2);

            String title = titleET.getText().toString();
            String isbnString = isbnET.getText().toString();
            String priceString = priceET.getText().toString();

            //updating book in the database
            try {
                double price = Double.parseDouble(priceString);
                int isbn = Integer.parseInt(isbnString);
                db.update(bookID, title, isbn, price);
                Toast.makeText(UpdateActivity.this, "Book updated", Toast.LENGTH_SHORT).show();

                //update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this, "Price error", Toast.LENGTH_LONG).show();
            }

            UpdateActivity.this.finish();
        }
    }
}