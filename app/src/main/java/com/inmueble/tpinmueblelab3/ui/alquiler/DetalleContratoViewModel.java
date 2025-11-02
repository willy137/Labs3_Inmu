package com.inmueble.tpinmueblelab3.ui.alquiler;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inmueble.tpinmueblelab3.ui.modelos.Contrato;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> mContrato=new MutableLiveData<>();

    private MutableLiveData<String> mMensaje=new MutableLiveData<>();


    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public void obtenerContrato(Bundle bun){
        int idInmu = bun.getInt("inmueble");
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Call<Contrato> llamada=api.obtenerContrato("Bearer "+token,idInmu);
        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                Log.d("Salida", "Paso1"+response.isSuccessful());
                if(response.isSuccessful()){
                    mContrato.setValue(response.body());
                }else{
                    mMensaje.setValue("Error al momento de Obtener Contrato");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                mMensaje.setValue("Error Servidor");
            }
        });
    }

    public LiveData<Contrato> getMContrato(){return mContrato;}
    public LiveData<String> getMMensaje(){return mMensaje;}


}