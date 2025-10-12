package com.inmueble.tpinmueblelab3.ui;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.inmueble.tpinmueblelab3.MenuActivity;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLoginViewModel extends AndroidViewModel {
    public ActivityLoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void inicio(String mail,String password) {
        ApiClient.InmoServices api=ApiClient.getApiImobiliaria();
        Call<String> llamada=api.login(mail,password);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //se ejecuta cuando hay una respuesta 200 o 400 el token
                if(response.isSuccessful()){
                    String token=response.body();
                    ApiClient.guardarToken(getApplication(),token);
                    Intent intent=new Intent(getApplication(), MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                }else{
                    Toast.makeText(getApplication(), "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplication(), "Error Servidor", Toast.LENGTH_LONG).show();
            }
        });

    }
}
