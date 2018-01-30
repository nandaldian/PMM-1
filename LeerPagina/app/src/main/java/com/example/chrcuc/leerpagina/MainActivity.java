package com.example.chrcuc.leerpagina;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText urlField;
    private TextView data;
    String link = "http://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlField = (EditText)findViewById(R.id.editText);
        data = (TextView)findViewById(R.id.text2);
    }

    public void download(View view){
        String url = "http://" + urlField.getText().toString();
        DownloadWebPage paginaWeb=new DownloadWebPage(this,data);

        paginaWeb.execute(url);
    }

    class DownloadWebPage extends AsyncTask<String, String, String> {

        private TextView dataField;
        private Context context;


        public DownloadWebPage(Context context,TextView dataField) {
            this.context = context;
            this.dataField = dataField;
        }

        //check Internet conenction.
        private void checkInternetConenction(){
            ConnectivityManager check = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (check != null)
            {
                NetworkInfo[] info = check.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i <info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            Toast.makeText(context, "Internet is connected",
                                    Toast.LENGTH_SHORT).show();
                        }

            }
            else{
                Toast.makeText(context, "not conencted to internet",
                        Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPreExecute(){
            checkInternetConenction();
        }

        protected String doInBackground(String... arg0) {
            //protected String doInBackground(Object... arg0) {
            String webPage = "";
            HttpURLConnection conn=null;
            try{
                //String link = (String)arg0[0];
                String link = arg0[0];
                URL url = new URL(link);
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                if (conn.getResponseCode()==HttpURLConnection.HTTP_OK) {

                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String data = null;


                    while ((data = reader.readLine()) != null) {
                        webPage += data + "\n";

                    }
                    is.close();
                }
                publishProgress(webPage);
            }catch(Exception e){
                webPage= new String("Exception: " + e.getMessage());
            }
            finally {
                conn.disconnect();
            }
            return webPage;
        }
        protected void onProgressUpdate(String... pasos) {
            Toast.makeText(context, pasos[0], Toast.LENGTH_SHORT).show();
        }
        protected void onPostExecute(String result){
            this.dataField.setText(result);
        }
    }
}