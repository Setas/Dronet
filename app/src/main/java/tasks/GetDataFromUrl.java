package tasks;

import android.util.Log;

import com.example.setas.drones.HttpRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Setas on 9/8/2014.
 */
public class GetDataFromUrl {

    private static String url = "http://ws.audioscrobbler.com/2.0/?method=geo.getevents&location=madrid&api_key=ab6bd6191a36224bfddf699c33c3829c&format=json";
    private static final int HTTP_STATUS_OK = 200;
    private static byte[] buff = new byte[1024];
    private static String logTag = "GetDataFormUrl";

    public static class ApiException extends Exception {

        public ApiException(String msg) {
            super(msg);
        }

        public ApiException(String msg, Throwable tr) {
            super(msg, tr);
        }
    }

    public static synchronized String downloadFromServer(String... params) throws ApiException {
        String retVal = "";
        String fullUrl = url;

        Log.d(logTag, "fetching url: " + fullUrl);

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(fullUrl);

        try {
            HttpResponse response = client.execute(request);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != HTTP_STATUS_OK) {
                throw new ApiException("Error in response");
            }

            HttpEntity entity = response.getEntity();
            InputStream ist = entity.getContent();

            ByteArrayOutputStream content = new ByteArrayOutputStream();

            int readCount = 0;
            while ((readCount = ist.read(buff)) != -1) {
                content.write(buff, 0, readCount);
            }

            retVal = new String(content.toByteArray());
            Log.d(logTag, "fetched data: " + retVal);
        }catch (IOException e){
            throw new ApiException("Problem connection to the server "+e.getMessage(), e);
        }
        return retVal;
    }
}
