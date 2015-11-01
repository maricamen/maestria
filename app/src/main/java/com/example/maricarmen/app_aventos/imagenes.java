package com.example.maricarmen.app_aventos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import  java.io.ByteArrayOutputStream;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;


public class imagenes extends ActionBarActivity {

    private static final int Tomar_Foto = 2;
    float latitud;
    float longitud;
    float altitud;
    float precision;
    int aperturbador;
    int calamidad;
    int pobafectada;
    int aafectada;
    int tipoa;
    boolean guardado;
    rest servicio;

    Uri imageUri;
    private static File carpetaEventos;
    String rutaArchivo;

    public static ImageView imageView;
    public static ImageView imageView2;
    public static ImageView imageView3;
    public static ImageView imageView4;
    public static byte[] imageByteArray1;
    public static byte[] imageByteArray2;
    public static byte[] imageByteArray3;
    public static byte[] imageByteArray4;
    public static int imageClicked;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        Bundle bundle = getIntent().getExtras();

        latitud = Float.parseFloat(bundle.getString("latitud"));
        longitud = Float.parseFloat(bundle.getString("longitud"));
        altitud = Float.parseFloat(bundle.getString("altitud"));
        precision = Float.parseFloat(bundle.getString("precision"));
        aperturbador = Integer.parseInt(bundle.getString("agenteP"));
        calamidad = Integer.parseInt(bundle.getString("calamidad"));
        pobafectada = Integer.parseInt(bundle.getString("poblacionA"));
        aafectada = Integer.parseInt(bundle.getString("areaA"));
        tipoa = Integer.parseInt(bundle.getString("tipoAfec"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imagenes, menu);
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
    public void btnfinal_Click(View view) {

        servicio = new rest();
        guardado = false;
        guardado = servicio.doInBackground(String.valueOf(latitud), String.valueOf(longitud), String.valueOf(altitud), String.valueOf(precision), String.valueOf(aperturbador), String.valueOf(calamidad), String.valueOf(pobafectada), String.valueOf(aafectada), String.valueOf(tipoa), Base64.encodeToString(imageByteArray1, Base64.DEFAULT), Base64.encodeToString(imageByteArray2, Base64.DEFAULT), Base64.encodeToString(imageByteArray3, Base64.DEFAULT), Base64.encodeToString(imageByteArray4, Base64.DEFAULT));
        if (guardado)
        {
            Intent i = new Intent(this, enviado.class);
            startActivity(i);
        }
        else
        {
            Button botonSig = (Button) findViewById(R.id.btnSigIma);
            botonSig.setText("Reintentar");
        }
    }

    public void btnImagen_Click(View view) {
        imageClicked = 1;
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        String carpeta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/eventos";
        carpetaEventos = new File(carpeta);
        if (!carpetaEventos.exists())
        {
            carpetaEventos.mkdir();
        }
        rutaArchivo = carpeta + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File archivo = new File(rutaArchivo);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(archivo));
        startActivityForResult(intent,Tomar_Foto);
    }

    public void btnImagen2_Click(View view) {
        imageClicked = 2;
        this.imageView2 = (ImageView)this.findViewById(R.id.imageView2);
        String carpeta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/eventos";
        carpetaEventos = new File(carpeta);
        if (!carpetaEventos.exists())
        {
            carpetaEventos.mkdir();
        }
        rutaArchivo = carpeta + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File archivo = new File(rutaArchivo);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(archivo));
        startActivityForResult(intent,Tomar_Foto);
    }

    public void btnImagen3_Click(View view) {
        imageClicked = 3;
        this.imageView3 = (ImageView)this.findViewById(R.id.imageView3);
        String carpeta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/eventos";
        carpetaEventos = new File(carpeta);
        if (!carpetaEventos.exists())
        {
            carpetaEventos.mkdir();
        }
        rutaArchivo = carpeta + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File archivo = new File(rutaArchivo);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(archivo));
        startActivityForResult(intent,Tomar_Foto);
    }

    public void btnImagen4_Click(View view) {
        imageClicked = 4;
        this.imageView4 = (ImageView)this.findViewById(R.id.imageView4);
        String carpeta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/eventos";
        carpetaEventos = new File(carpeta);
        if (!carpetaEventos.exists())
        {
            carpetaEventos.mkdir();
        }
        rutaArchivo = carpeta + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File archivo = new File(rutaArchivo);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(archivo));
        startActivityForResult(intent,Tomar_Foto);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Tomar_Foto) {

            if (rutaArchivo != null) {
                Bitmap faceView = ( new_decode(new File(rutaArchivo))); // ========================> good
                // lines
                switch (imageClicked)
                {
                    case 1:
                        imageView.setImageBitmap(faceView);
                        break;
                    case 2:
                        imageView2.setImageBitmap(faceView);
                        break;
                    case 3:
                        imageView3.setImageBitmap(faceView);
                        break;
                    case 4:
                        imageView4.setImageBitmap(faceView);
                        break;
                }

            } else {
                bitmap = null;
            }
        }
    }

    public static Bitmap new_decode(File f) {

        // decode image size

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
        // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the future
        try {
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Find the correct scale value. It should be the power of 2.
        final int REQUIRED_SIZE = 320;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        if (height_tmp > REQUIRED_SIZE)
        {
            float tmp_height = height_tmp;
            height_tmp = Math.round(height_tmp * ((float)REQUIRED_SIZE/height_tmp));
            width_tmp = Math.round(((float)width_tmp * REQUIRED_SIZE) / tmp_height);
        }
        else if (width_tmp > REQUIRED_SIZE)
        {
            float tmp_width = width_tmp;
            width_tmp = Math.round(width_tmp * ((float)REQUIRED_SIZE/width_tmp));
            height_tmp = Math.round(((float)height_tmp * REQUIRED_SIZE) / tmp_width);
        }

        // decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        // o2.inSampleSize=scale;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
        // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the future
        // return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        try {

//          return BitmapFactory.decodeStream(new FileInputStream(f), null,
//                  null);
            Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(f), null, null);
            System.out.println(" IW " + width_tmp);
            System.out.println("IHH " + height_tmp);
            int iW = width_tmp;
            int iH = height_tmp;

            switch (imageClicked)
            {
                case 1:
                    //ByteBuffer buffer1 = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
                    //bitmap.copyPixelsToBuffer(buffer1);
                    //imageByteArray1 = buffer1.array();
                    //ByteArrayOutputStream streamIV1 = new ByteArrayOutputStream();
                    //bitmapImagen.compress(Bitmap.CompressFormat.PNG, 100, streamIV1);
                    //byte[] imageByteArray1 = streamIV1.toByteArray();
                    imageByteArray1 = new byte[(int)f.length()];
                    new FileInputStream(f).read(imageByteArray1);
                    break;
                case 2:
                    imageByteArray2 = new byte[(int)f.length()];
                    new FileInputStream(f).read(imageByteArray2);
                    break;
                case 3:
                    imageByteArray3 = new byte[(int)f.length()];
                    new FileInputStream(f).read(imageByteArray3);
                    break;
                case 4:
                    imageByteArray4 = new byte[(int)f.length()];
                    new FileInputStream(f).read(imageByteArray4);
                    break;
            }

            return Bitmap.createScaledBitmap(bitmap, iW, iH, false);

        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            e.printStackTrace();
            // clearCache();

            // System.out.println("bitmap creating success");
            System.gc();
            return null;
            // System.runFinalization();
            // Runtime.getRuntime().gc();
            // System.gc();
            // decodeFile(f);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}