package com.example.alumno.clase5;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by alumno on 28/04/2016.
 */
public class MiHilo implements Runnable {
    private Handler handler;
    private HttpManager httpManager;
    private Boolean vf;

    public MiHilo(Handler h, String url, Boolean b){
        handler= h;
        httpManager= new HttpManager(url);
        vf= b;
    }

    @Override
    public void run() {
        Message msg= new Message();
        if (vf){
            try {
                msg.obj= httpManager.getStrDataByGET();
                msg.arg1= MainActivity.TXTTEXTO;
                handler.sendMessage(msg);
            } catch (Exception e){

            }
        } else{
            try{
                msg.obj= httpManager.getBytesDataByGET();
                msg.arg1= MainActivity.IMGIMAGEN;
                handler.sendMessage(msg);
            } catch (Exception e){

            }
        }
    }
}
