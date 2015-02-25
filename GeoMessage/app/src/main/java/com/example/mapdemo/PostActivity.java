package com.example.mapdemo;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class PostActivity extends Activity {


    private EditText etTitle ;
    private EditText etSnippet ;
    private Location loc;
    private ParseGeoPoint geoPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etSnippet = (EditText) findViewById(R.id.etSnippet);

        Intent intent = getIntent();
        loc = intent.getParcelableExtra("Location");
        if(loc != null)
            geoPoint = new ParseGeoPoint(loc.getLatitude(), loc.getLongitude());
        else
            geoPoint = null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_map_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnPost_click(View view) {

        String title = etTitle.getText().toString();
        String snippet = etSnippet.getText().toString();

        GeoMessagePost message = new GeoMessagePost();

        message.setTitle(title);
        message.setSnippet(snippet);
        message.setLocation(geoPoint);
        message.setUser(ParseUser.getCurrentUser());


        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                finish();
            }
        });
    }
}
