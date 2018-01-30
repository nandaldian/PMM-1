package com.example.chrcuc.localizadorjson;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Context context;

    EditText campoLatitud, campoLongitud;
    Button calcular;
    TextView resultado;

    String direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Obtener Dirección");

        resultado = (TextView)findViewById(R.id.tvResultado);
        campoLatitud = (EditText)findViewById(R.id.etLatitud);
        campoLongitud = (EditText)findViewById(R.id.etLongitud);
        calcular = (Button)findViewById(R.id.btCalcular);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitud, latitud;
                longitud = campoLongitud.getText().toString();
                latitud = campoLatitud.getText().toString();

                final String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitud + "," + longitud + "&sensor=false";
                new TareaHttpAsincrona().execute(url);
            }
        });
    }


    public class TareaHttpAsincrona extends AsyncTask <String, Void, Integer> {

        // INTENTAMOS LA CONEXIÓN, SI CONECTAMOS DEVUELVE 1, SI HAY FALLO DEVUELVE 0
        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection httpURLConnection;

            Integer res = 0;

            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestMethod("GET");
                int statusCode = httpURLConnection.getResponseCode();

                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    String respuesta = convierteInputStreamAString(inputStream);
                    parsearResultado(respuesta);
                    res = 1;
                }
                else
                    res = 0;
            }
            catch (Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
            return res;
        }


        // DESPUÉS DE COMPROBAR LA CONEXIÓN, SI LA CONEXIÓN ES EXITOSA MOSTRAMOS LOS DATOS
        @Override
        protected void onPostExecute(Integer result) {
            if(result == 1)
                resultado.setText(direccion);
            else
                Toast.makeText(getApplicationContext(), "Error en obtener los datos!", Toast.LENGTH_SHORT).show();
        }
    }


    // MÉTODO PARA CONVERTIR LOS DATOS RECOGIDOS POR INPUTSTREAM A UNA CADENA
    private String convierteInputStreamAString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String linea;
        String cadena = "";

        while((linea = bufferedReader.readLine()) != null)
            cadena += linea;

        inputStream.close();

        return cadena;
    }


    // MÉTODO QUE PARSEA EL JSON, ASIGNAMOS A NUESTA VARIABLE EL PRIMER
    // ELEMENTO DEL ARRAY JSON 'results' llamado 'formatted_address'
    private void parsearResultado(String res) {
        try {
            JSONObject resultado = new JSONObject(res);

            JSONArray direcciones = resultado.optJSONArray("results");
            direccion = direcciones.getJSONObject(0).getString("formatted_address");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
