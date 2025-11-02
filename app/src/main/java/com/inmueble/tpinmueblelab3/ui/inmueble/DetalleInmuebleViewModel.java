package com.inmueble.tpinmueblelab3.ui.inmueble;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmueble = new MutableLiveData<>();

    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble(){
        return inmueble;
    }

    public LiveData<String> getMMensaje(){
        return mMensaje;
    }


    public void obtenerInmueble(Bundle inmuebleBundle){
        Inmueble inmueble = (Inmueble) inmuebleBundle.getSerializable("inmueble");
        if(inmueble != null){
            this.inmueble.setValue(inmueble);
        }

    }

    public void actualizarInmueble(Boolean disponible){
        Inmueble inmueble = new Inmueble();
        inmueble.setDisponible(disponible);
        inmueble.setIdInmueble(this.inmueble.getValue().getIdInmueble());
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> llamada = ApiClient.getApiImobiliaria().actualizarInmueble("Bearer "+token, inmueble);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    mMensaje.setValue("Inmueble Actualizado");
                } else {
                    mMensaje.setValue("Error al Actualizar Inmueble");
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                mMensaje.setValue("Error servidor");            }
        });

    }
}