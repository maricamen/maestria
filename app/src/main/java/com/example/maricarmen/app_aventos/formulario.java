package com.example.maricarmen.app_aventos;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;


public class formulario extends ActionBarActivity {

    float latitud;
    float longitud;
    float altitud;
    float precision;

    Spinner subspinner;

    ArrayAdapter<CharSequence> geologico;
    ArrayAdapter<CharSequence> hidromet;
    ArrayAdapter<CharSequence> quimico;
    ArrayAdapter<CharSequence> sanitario;
    ArrayAdapter<CharSequence> socioorg;
    ArrayAdapter<CharSequence> arreglo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Spinner spinnerA = (Spinner) findViewById(R.id.spinnerAP);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterA = ArrayAdapter.createFromResource(this,
                R.array.aPerturbador, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerA.setAdapter(adapterA);
        spinnerA.setOnItemSelectedListener(new spinnerListener());



        Spinner spinnerAf = (Spinner) findViewById(R.id.spinnerAfect);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAf = ArrayAdapter.createFromResource(this,
                R.array.afectacion, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterAf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerAf.setAdapter(adapterAf);

        Bundle bundle = getIntent().getExtras();

        latitud = Float.parseFloat(bundle.getString("latitud"));
        longitud = Float.parseFloat(bundle.getString("longitud"));
        altitud = Float.parseFloat(bundle.getString("altitud"));
        precision = Float.parseFloat(bundle.getString("precision"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
    public class spinnerListener implements AdapterView.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id){
            cargaSpinnerCalamidad(parent.getSelectedItemPosition());
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }

    }

    private void cargaSpinnerCalamidad (int posicion){
        subspinner = (Spinner) findViewById(R.id.spinnerCalam);

        geologico = ArrayAdapter.createFromResource
                (this, R.array.cGeologico,
                        android.R.layout.simple_spinner_item);

        hidromet = ArrayAdapter.createFromResource
                (this, R.array.cHidromet,
                        android.R.layout.simple_spinner_item);

        quimico = ArrayAdapter.createFromResource
                (this, R.array.cQuimico,
                        android.R.layout.simple_spinner_item);

        sanitario = ArrayAdapter.createFromResource
                (this, R.array.cSanitario,
                        android.R.layout.simple_spinner_item);

        socioorg = ArrayAdapter.createFromResource
                (this, R.array.cSocioorg,
                        android.R.layout.simple_spinner_item);

        arreglo = ArrayAdapter.createFromResource
                (this, R.array.arregloVacio,
                        android.R.layout.simple_spinner_item);

        switch (posicion) {
            case 1: subspinner.setAdapter(geologico);
                break;
            case 2: subspinner.setAdapter(hidromet);
                break;
            case 3: subspinner.setAdapter(quimico);
                break;
            case 4: subspinner.setAdapter(sanitario);
                break;
            case 5: subspinner.setAdapter(socioorg);
                break;
            default: subspinner.setAdapter(arreglo);
                break;

        }
    }
    public void btnSigForm_Click(View view) {
        Intent i = new Intent(this, imagenes.class);

        i.putExtra("latitud", String.valueOf(latitud));
        i.putExtra("longitud", String.valueOf(longitud));
        i.putExtra("altitud", String.valueOf(altitud));
        i.putExtra("precision", String.valueOf(precision));

        Spinner spinnerAp = (Spinner) findViewById(R.id.spinnerAP);
        i.putExtra("agenteP",String.valueOf(spinnerAp.getSelectedItemId()));

        Spinner spinnerCa = (Spinner) findViewById(R.id.spinnerCalam);
        i.putExtra("calamidad",String.valueOf(spinnerCa.getSelectedItemId()));

        TextView pobAfec = (TextView) findViewById(R.id.pobA_edt);
        i.putExtra("poblacionA", pobAfec.getText().toString());

        TextView arAfec = (TextView) findViewById(R.id.aA_edt);
        i.putExtra("areaA", arAfec.getText().toString());

        Spinner spinnerTafec = (Spinner) findViewById(R.id.spinnerAfect);
        i.putExtra("tipoAfec",String.valueOf(spinnerTafec.getSelectedItemId()));

        startActivity(i);
    }
}
