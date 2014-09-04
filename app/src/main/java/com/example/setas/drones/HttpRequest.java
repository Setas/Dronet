package com.example.setas.drones;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Setas on 9/4/2014.
 */
public class HttpRequest {
    private URL url;
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;

    public HttpRequest() {
        httpURLConnection = null;

        try {
            url = new URL("http://dronet.projektas.in/api/drones");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(Integer.parseInt("id=hello"));
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(" response: "+responseCode);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}

