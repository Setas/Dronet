package com.example.setas.drones;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codeminders.ardrone.ARDrone;


public class MyActivity extends Activity {

    private static final long CONNECT_TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView text = (TextView) findViewById(R.id.text);
        ARDrone drone;
        try
        {
            // Create ARDrone object,
            // connect to drone and initialize it.
            drone = new ARDrone();
            text.setText("connecting");
            drone.connect();
            drone.clearEmergencySignal();

            // Wait until drone is ready
            drone.waitForReady(CONNECT_TIMEOUT);

            // do TRIM operation
            drone.trim();

            // Take off
            text.setText("Taking off");
            System.err.println("Taking off");
            drone.takeOff();

            // Fly a little :)
            Thread.sleep(5000);

            // Land
            text.setText("Landing");
            System.err.println("Landing");
            drone.land();

            // Give it some time to land
            Thread.sleep(2000);

            // Disconnect from the done
            drone.disconnect();

        } catch(Throwable e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
