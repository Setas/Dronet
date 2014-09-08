package com.example.setas.drones;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codeminders.ardrone.ARDrone;

import tasks.FetchingDataTask;


public class MyActivity extends Activity {

    private static final long CONNECT_TIMEOUT = 3000;
    private ARDrone drone;
    private TextView statusLabel;
    private TextView textPlane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        statusLabel = (TextView) findViewById(R.id.text);
        textPlane = (TextView) findViewById(R.id.planeText);
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

    public void connectDrone(View view) {
        FetchingDataTask task = new FetchingDataTask(this);
        task.execute("empty");

//        try
//        {
//            // Create ARDrone object,
//            // connect to drone and initialize it.
//            drone = new ARDrone();
//            statusLabel.setText("connecting");
//            try {
//                drone.connect();
//            }catch(Exception e){
//                statusLabel.setText("connection failed");
//            }
//            drone.clearEmergencySignal();
//
//            // Wait until drone is ready
//            drone.waitForReady(CONNECT_TIMEOUT);
//
//            // do TRIM operation
//            drone.trim();
//
//            // Take off
//            statusLabel.setText("Taking off");
//            System.err.println("Taking off");
//            drone.takeOff();
//
//            // Fly a little :)
//            Thread.sleep(5000);
//
//            // Land
//            statusLabel.setText("Landing");
//            System.err.println("Landing");
//            drone.land();
//
//            // Give it some time to land
//            Thread.sleep(2000);
//
//            // Disconnect from the done
//            drone.disconnect();
//
//        } catch(Throwable e)
//        {
//            e.printStackTrace();
//        }

    }

    public void btnForwardClicked(View view) {
    }

    public void alert(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void setData(String str){
        textPlane.setText(str);
    }

}
