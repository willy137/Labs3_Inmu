package com.inmueble.tpinmueblelab3.ui.pagos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.modelos.Pago;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pago>> mPagos=new MutableLiveData<>();
    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    public PagoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Pago>> getMPagos(){return mPagos;}
    public LiveData<String> getMMensaje(){return  mMensaje;}

    public void leerPagos(Bundle bundle){
        int id=bundle.getInt("idPago");
        String token= ApiClient.leerToken(getApplication());
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Log.d("Salida", "id:"+id);
        Call<List<Pago>> llamada=api.obtenerPagos("Bearer "+token,id);
        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    Log.d("Salida", "onResponse: "+id+" aca:"+response.body());
                    mPagos.setValue(response.body());
                }else{
                    mMensaje.setValue("Error al traer los pagos");
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                mMensaje.setValue("Error Servidor");
            }
        });


    }

}