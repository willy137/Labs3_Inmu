package com.inmueble.tpinmueblelab3.ui.logout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

public class AlertaSalida {
    public static void  mostrarDialogoBoton(Context context){

        new AlertDialog.Builder(context)
                .setTitle("Cerrar Sesion")
                .setMessage("Desea cerrar la aplicacion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((AppCompatActivity)context).finishAndRemoveTask();
                        ApiClient.cerrarSesion(context);
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Continuamos",Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
