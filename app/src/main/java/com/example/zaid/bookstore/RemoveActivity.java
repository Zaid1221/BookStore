package com.example.zaid.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {

    private DataBaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseManager(this);
        updateView();
    }

    //build gui programmtically
    public void updateView()
    {
        ArrayList<Book> books = db.selectAll();

        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for(Book book : books)
        {
            RadioButton rb = new RadioButton(this);
            rb.setId(book.getID());
            rb.setText(book.toString());
            group.addView(rb);
        }

        //set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);


        //ceating back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        //add back button at bottom
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements  RadioGroup.OnCheckedChangeListener
    {
        public void onCheckedChanged(RadioGroup gorup, int checkedId)
        {
            db.remove(checkedId);
            Toast.makeText( RemoveActivity.this, "Book deleted", Toast.LENGTH_LONG).show( );

            updateView();
        }
    }
}
