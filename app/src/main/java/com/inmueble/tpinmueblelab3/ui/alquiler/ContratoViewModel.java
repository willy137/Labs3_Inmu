package com.inmueble.tpinmueblelab3.ui.alquiler;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    private MutableLiveData<List<Inmueble>> mInmus=new MutableLiveData<>();

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public void leerInmus(){
        String token= ApiClient.leerToken(getApplication());
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Call<List<Inmueble>> llamada=api.obtenerContratosVigentes("Bearer "+token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    mInmus.postValue(response.body());
                }else {
                    mMensaje.setValue("Error al Obtener Inmuebles Contratados");
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                mMensaje.setValue("Error Servidor");
            }
        });
    }
    public LiveData<List<Inmueble>> getMInmus(){return  mInmus;}
}