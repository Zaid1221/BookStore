package com.example.zaid.bookstore;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager db;
    private ScrollView scrollView;
    private  int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db= new DataBaseManager(this);
        scrollView=(ScrollView) findViewById(R.id.scrollView);
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        buttonWidth = size.x / 2;
        updateView( );

    }

    protected void onResume( ) //this is called when the user comes back
    {                          //from another activity
        super.onResume( );
        updateView( );
    }

    public void updateView( ) //adds all the candy to the main screen
    {
        ArrayList<Book> books = db.selectAll( ); //creates array stores all candy from DB in it
        if( books.size( ) > 0 ) {

            //
            scrollView.removeAllViewsInLayout();

            GridLayout grid = new GridLayout(this);
            grid.setRowCount((books.size() + 1) / 2);
            grid.setColumnCount(2);

            BookButton[] buttons = new BookButton[books.size()];
            ButtonHandler bh = new ButtonHandler();

            int i = 0;

            for (Book book : books) {
                buttons[i] = new BookButton(this, book);
                buttons[i].setText(book.getTitle()
                        + "\n" + book.getISBN()
                        + "\n" + book.getPrice());
                buttons[i].setOnClickListener(bh);


                grid.addView(buttons[i], buttonWidth,
                        GridLayout.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_add:
            {
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            }

            case R.id.action_remove: {
                Intent removeIntent = new Intent(this, RemoveActivity.class);
                this.startActivity(removeIntent);
                return true;
            }

            case R.id.action_update:
            {
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void openWebPage(String url)
    {
        Uri webpage= Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);

        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve price of the candy and add it to total
            // total += ( ( CandyButton ) v ).getPrice( );
            //String pay =
            //      NumberFormat.getCurrencyInstance( ).format( total );
            //Toast.makeText( MainActivity.this, pay,
            //      Toast.LENGTH_LONG ).show( );
            String url="http://www.google.com";
            // TODO (6) Replace the Toast with a call to openWebPage, passing in the URL String from the previous step
            openWebPage(url);

        }
    }
}
