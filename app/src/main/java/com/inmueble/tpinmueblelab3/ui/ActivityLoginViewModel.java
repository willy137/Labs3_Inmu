package com.inmueble.tpinmueblelab3.ui;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inmueble.tpinmueblelab3.MenuActivity;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLoginViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    public ActivityLoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMMensaje(){return mMensaje;}

    public void inicio(String mail,String password) {
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Call<String> llamada=api.login(mail,password);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token=response.body();
                    ApiClient.guardarToken(getApplication(),token);
                    Intent intent=new Intent(getApplication(), MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                }else{
                    mMensaje.setValue("Usuario o Contraseña incorrectos");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mMensaje.setValue("Error De Servidor");
            }
        });

    }
}
