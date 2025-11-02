package com.inmueble.tpinmueblelab3.ui.inquilino;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.inmueble.tpinmueblelab3.ui.modelos.Contrato;
import com.inmueble.tpinmueblelab3.ui.modelos.Inquilino;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino=new MutableLiveData<>();

    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public void leerInqui(Bundle bundle){
        int id=bundle.getInt("inmueble");
        String token= ApiClient.leerToken(getApplication());
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Log.d("Salida", id+"");
        Call<Contrato> llamada=api.obtenerContrato("Bearer "+token,id);
        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    Log.d("Salida", response.body().getInquilino().getIdInquilino()+"");
                    mInquilino.setValue(response.body().getInquilino());
                }else {
                    mMensaje.setValue("Error al momento de Obtener Contrato en Inquilino Detalle");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                mMensaje.setValue("Error Servidor");
            }
        });



    }

    public LiveData<Inquilino> getMInquilino(){return mInquilino;}
    public LiveData<String> getMMensaje(){return mMensaje;}

}