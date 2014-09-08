package tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.setas.drones.MyActivity;

/**
 * Created by Setas on 9/8/2014.
 */
public class FetchingDataTask extends AsyncTask<String, Integer, String> {

    private MyActivity activity;
    private Context content;
    private String logTag ="FetchingDataTask";

    public FetchingDataTask(MyActivity activity){
        super();
        this.activity=activity;
        this.content = activity.getApplicationContext();
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(logTag, "Background: "+Thread.currentThread().getName());

        try {
            String result = GetDataFromUrl.downloadFromServer(strings);
            return result;
        } catch (GetDataFromUrl.ApiException e) {
            return new String();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.length()==0){
            activity.alert("Unable to find data");
        }
        JSONDataAnalizer analizer = new JSONDataAnalizer(s);
        activity.setData(analizer.analyze());
    }
}
