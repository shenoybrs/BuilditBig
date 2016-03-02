package com.example.santosh.jokelibrary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends ActionBarActivity {

    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.santosh.jokelibrary.R.layout.activity_joke);

        mJokeTextView = (TextView)findViewById(com.example.santosh.jokelibrary.R.id.joke);
        String mJoke = getIntent().getStringExtra("JOKE");

        mJokeTextView.setText(mJoke);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.santosh.jokelibrary.R.menu.menu_joke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.santosh.jokelibrary.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
