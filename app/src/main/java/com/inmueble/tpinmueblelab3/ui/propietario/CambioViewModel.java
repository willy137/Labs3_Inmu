package com.inmueble.tpinmueblelab3.ui.propietario;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inmueble.tpinmueblelab3.ui.modelos.Propietario;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioViewModel extends AndroidViewModel {
    public MutableLiveData<String> mMensaje1= new MutableLiveData<>();

    public CambioViewModel(@NonNull Application application) {
        super(application);
    }

    public void guardarContra(String actual, String nueva, String nueva2){
        if(nueva!=null && !nueva.isEmpty() && nueva.equals(nueva2)){
            String token=ApiClient.leerToken(getApplication());
            Call<Void> llamada=ApiClient.getApiImobiliaria().actualizarContraseña("bearer "+token,actual,nueva);
            llamada.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        mMensaje1.setValue("Cambio de Contraseña exitoso");
                    }else {
                        mMensaje1.setValue("Error Contraseña Actual Incorrecta");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    mMensaje1.setValue("Error Servidor");
                }
            });
        }else {
            mMensaje1.setValue("Contraseña Vacia/ No coinciden las contraseñas");
        }
    }
    public LiveData<String> getmMensaje1() {
        return mMensaje1;
    }

}