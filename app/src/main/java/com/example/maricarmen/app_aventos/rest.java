package com.example.maricarmen.app_aventos;

/**
 * Created by Maricarmen on 29/06/2015.
 */
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class rest extends AsyncTask <String, Void, Boolean> {

    boolean guardado;

    String url = "http://appinfo.esy.es/";

    public boolean enviar_evento(
            float latitud,
            float longitud,
            float altitud,
            float precision,
            int aperturbador,
            int calamidad,
            int pafectada,
            int aafectada,
            int tafectacion,
            byte[] imgn1,
            byte[] imgn2,
            byte[] imgn3,
            byte[] imgn4
    ){

        guardado = false;

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url + "guardar_evento.php");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        JSONObject obj = new JSONObject();

        try {
            obj.put("latitud", String.valueOf(latitud));
            obj.put("longitud", String.valueOf(longitud));
            obj.put("altitud", String.valueOf(altitud));
            obj.put("precision", String.valueOf(precision));
            obj.put("aperturbador", String.valueOf(aperturbador));
            obj.put("calamidad", String.valueOf(calamidad));
            obj.put("pafectada", String.valueOf(pafectada));
            obj.put("aafectada", String.valueOf(aafectada));
            obj.put("tafectacion", String.valueOf(tafectacion));
            obj.put("img1", imgn1);
            obj.put("img2", imgn2);
            obj.put("img3", imgn3);
            obj.put("img4", imgn4);
            httpPost.setEntity(new StringEntity(obj.toString(), "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            String message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {

                InputStream instream = entity.getContent();
                Reader reader = new InputStreamReader(instream);
                BufferedReader breader = new BufferedReader(reader);
                String resultadoJSON = breader.readLine();
                JSONObject jsonObject = new JSONObject(resultadoJSON);
                guardado = jsonObject.getBoolean("resultado");
                instream.close();
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return guardado;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return enviar_evento(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]),Float.parseFloat(params[3]),
                Integer.parseInt(params[4]),Integer.parseInt(params[5]),Integer.parseInt(params[6]),Integer.parseInt(params[7]),Integer.parseInt(params[8]),params[9].getBytes(), params[10].getBytes(), params[11].getBytes(), params[12].getBytes());
    }
}
