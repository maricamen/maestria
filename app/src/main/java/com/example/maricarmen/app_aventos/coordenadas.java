package com.example.maricarmen.app_aventos;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class coordenadas extends ActionBarActivity implements LocationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordenadas);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Boolean GPSActivado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Boolean RedActivada = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        String provider = "";

        if (RedActivada){
            provider = LocationManager.NETWORK_PROVIDER;
        }

        if (GPSActivado){
            provider = LocationManager.GPS_PROVIDER;
        }

            locationManager.requestLocationUpdates(provider, 0, 0, this);
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider,0,0,this);
            if(location !=  null){
                onLocationChanged(location);
            }
            else {
                Toast.makeText(getApplicationContext(),"Ubicación no encontrada",Toast.LENGTH_LONG).show();
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coordenadas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView textLong = (TextView) findViewById(R.id.logText);
        TextView textLat = (TextView) findViewById(R.id.latText);
        TextView textAlt = (TextView) findViewById(R.id.altText);
        TextView textPres = (TextView) findViewById(R.id.preText);

        textLong.setText(String.valueOf(location.getLongitude()));
        textLat.setText(String.valueOf(location.getLatitude()));
        textAlt.setText(String.valueOf(location.getAltitude()));
        textPres.setText(String.valueOf(location.getAccuracy()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public void btnSigCoord_Click(View view) {
        Intent i = new Intent(this, formulario.class);

        TextView lat = (TextView) findViewById(R.id.latText);
        i.putExtra("latitud", lat.getText().toString());
        TextView log = (TextView) findViewById(R.id.logText);
        i.putExtra("longitud", log.getText().toString());
        TextView alt = (TextView) findViewById(R.id.altText);
        i.putExtra("altitud", alt.getText().toString());
        TextView prec = (TextView) findViewById(R.id.preText);
        i.putExtra("precision", prec.getText().toString());

        startActivity(i);
    }
}

