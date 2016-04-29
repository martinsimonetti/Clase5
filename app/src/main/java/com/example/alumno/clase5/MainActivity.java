package com.example.alumno.clase5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private Thread h;
    private Thread otroH;
    private Handler handler;
    public static final int TXTTEXTO= 1;
    public static final int IMGIMAGEN= 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler= new Handler(this);
        h= new Thread(new MiHilo(handler, "http://192.168.2.130:8080/android/clase6.xml", true));
        otroH= new Thread(new MiHilo(handler, "http://192.168.2.130:8080/android/koala.png", false));
        h.start();
        otroH.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        h.interrupt();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1==TXTTEXTO){
            String s= msg.obj.toString();
            TextView txtTexto= (TextView) findViewById(R.id.txtTexto);
            txtTexto.setText(s);
        }

        if (msg.arg1==IMGIMAGEN){
            byte[] byteArray = (byte[]) msg.obj;
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ImageView image = (ImageView) findViewById(R.id.imgImagen);

            image.setImageBitmap(bmp);
        }
        return true;
    }
}
