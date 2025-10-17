package com.inmueble.tpinmueblelab3.ui.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inmueble.tpinmueblelab3.ui.modelos.Propietario;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public class ApiClient {

    private static final String UrlBase="https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";

    public static InmoServices getApiImobiliaria(){
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UrlBase)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(InmoServices.class);
    }

    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    public  interface InmoServices{
        @FormUrlEncoded
        @POST("api/propietarios/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String password);
        @GET("api/Propietarios")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String token);
        @PUT("api/Propietarios/actualizar")
        Call<Propietario> actualizarPropietario(@Header("Authorization") String token, @Body Propietario propietario);
        @FormUrlEncoded
        @PUT("api/Propietarios/changePassword")
        Call<Void> actualizarContraseña(@Header("Authorization") String token,@Field("currentPassword") String actual,@Field("newPassword") String nueva);
    }
}
