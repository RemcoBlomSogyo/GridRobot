package nl.sogyo.rblom.gridrobot;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class CommandsSender extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        try {
            System.out.println(params[0]);
            String urlStr = "http://10.10.4.236:5000/changeRoute/" + params[0];
            //String urlStr = "http://10.10.2.95:4000/changeRoute/" + params[0];
            System.out.println(urlStr);
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("POST");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
