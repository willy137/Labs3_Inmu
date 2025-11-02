package com.inmueble.tpinmueblelab3.ui.inmueble;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;



public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMensaje=new MutableLiveData<>();
    private MutableLiveData<List<Inmueble>> inmus=new MutableLiveData<>();

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        leerInmus();
    }

    public LiveData<String> getMMensaje(){return mMensaje;}
    public LiveData<List<Inmueble>> getInmus(){return inmus;}

    public void leerInmus(){
        String token= ApiClient.leerToken(getApplication());
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Call<List<Inmueble>> llamada=api.obtenerInmus("Bearer "+token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmus.postValue(response.body());
                }else {
                    mMensaje.setValue("Error al Obtener Inmuebles");
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                mMensaje.setValue("Error Servidor");
            }
        });

    }
}