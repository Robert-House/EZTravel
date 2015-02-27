package ateam.eztravel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;


public class Splash extends ActionBarActivity {

   String _status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LanguageManager temp;

        //test
        try
        {
            InputStream is = getAssets().open("Language.xml");
            temp = new LanguageManager(is);
            is.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Loader();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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

    // Insert custom code here
    /* This class handles the loading of the core data for the app
    *
    */
    TextView statusText;

    public boolean Loader()
    {
        boolean online = false;
        statusText = (TextView)findViewById(R.id.statText);

        // Init loading of resources
        // Try to connect to server
        _status = "Connecting to Server...";
        statusText.setText(_status);


        if (online)
        {
            // Do stuff
            _status = "Connecting to Server...SUCCESS";
            statusText.setText(_status);
        }
        else
        {
            // Pop-up box
            _status = "Connecting to Server...FAILED";
            statusText.setText(_status);
            // Must continue in offline mode
        }

        // Load translation data from disk
        _status = "Loading Translation Data...";
        statusText.setText(_status);

        // Process Translation Data
        _status = "Building Database...";
        statusText.setText(_status);

        // Load Graphic Resources
        _status = "Loading Resources...";
        statusText.setText(_status);

        // Do rest of stuff
        _status = "Done!";
        statusText.setText(_status);
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        // LAUNCH HOMEPAGE

        return true;
    }

    public void sendMessage(View view)
    {
        Intent i = new Intent(this, Main.class);
        startActivity(i);

    }
}
