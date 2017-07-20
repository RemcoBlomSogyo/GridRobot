package nl.sogyo.rblom.apptest;

import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView leftArrow = (ImageView) findViewById(R.id.imageView);
        ImageView upArrow = (ImageView) findViewById(R.id.imageView2);
        ImageView rightArrow = (ImageView) findViewById(R.id.imageView3);
        ImageView downArrow = (ImageView) findViewById(R.id.imageView4);
        Button button = (Button) findViewById(R.id.button);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommandSender().execute("left");
                //sendCommando("left");
            }
        });

        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommandSender().execute("forward");
                //sendCommando("forward");
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommandSender().execute("right");
                //sendCommando("right");
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommandSender().execute("backward");
                //sendCommando("backward");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommandSender().execute("stop");
            }
        });

//        String[] names = {"Remco", "Blomr", "Blom"};
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
//
//        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, names));
//
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
//            }
//        };
//
//        drawerLayout.setDrawerListener(drawerToggle);

    }

    private void sendCommando(String commando) {
//        HttpURLConnection urlConnection = null;
//        try {
//            //URL url = new URL("http://192.168.1.50:5000/" + commando);
//            URL url = new URL("http://10.10.4.236:5000/" + commando);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("POST");
//            System.out.println("click");
//            OutputStreamWriter request = new OutputStreamWriter(urlConnection.getOutputStream());
//            request.flush();
//            request.close();
//            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            //readStream(in);
//            String line = "";
//            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
//            BufferedReader reader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            while ((line = reader.readLine()) != null)
//            {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            urlConnection.disconnect();
//        }


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }*/

    private class CommandSender extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            try {
                //URL url = new URL("http://192.168.1.50:5000/" + commando);

                System.out.println(params[0]);
                //String urlStr = "http://10.10.4.236:5000/" + params[0];
                String urlStr = "http://10.10.2.95:4000/changeRoute/" + params[0];
                System.out.println(urlStr);
                URL url = new URL(urlStr);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                //OutputStream stream = urlConnection.getOutputStream();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                OutputStreamWriter request = new OutputStreamWriter(urlConnection.getOutputStream());
//                request.flush();
//                request.close();
//                String line = "";
//                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
//                BufferedReader reader = new BufferedReader(isr);
//                StringBuilder sb = new StringBuilder();
//                while ((line = reader.readLine()) != null)
//                {
//                    sb.append(line + "\n");
//                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return null;


        }
    }
}
