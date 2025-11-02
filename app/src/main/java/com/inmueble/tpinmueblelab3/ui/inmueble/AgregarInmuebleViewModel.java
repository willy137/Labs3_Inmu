package com.inmueble.tpinmueblelab3.ui.inmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;
import com.inmueble.tpinmueblelab3.ui.request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje=new MutableLiveData<>();

    private MutableLiveData<Uri> mUri=new MutableLiveData<>();


    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public void guardarInmu(String dire, String pre, String uso, String tipo,String superf,String amb, boolean disponible ){
        try {
            boolean campos=!dire.isEmpty() &&!uso.isEmpty() && !tipo.isEmpty();
            double precio=Double.parseDouble(pre);
            int superficie=Integer.parseInt(superf);
            int ambientes=Integer.parseInt(amb);
            boolean numeros=precio>0 && superficie>0 && ambientes>0;
            if(campos && numeros && mUri.getValue()!=null){
                Inmueble inmu=new Inmueble();
                inmu.setDireccion(dire);
                inmu.setAmbientes(ambientes);
                inmu.setSuperficie(superficie);
                inmu.setUso(uso);
                inmu.setTipo(tipo);
                inmu.setValor(precio);
                inmu.setDisponible(disponible);
                byte[] img=transformarImagen();
                String inmuebleJson= new Gson().toJson(inmu);
                RequestBody inmuebleBody=RequestBody.create(MediaType.parse("application/json: charset=utf-8"),inmuebleJson);
                RequestBody requestFile=RequestBody.create(MediaType.parse("image/jpeg"),img);
                //armar multipart
                MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);
                String token = ApiClient.leerToken(getApplication());
                Call<Inmueble> llamada=ApiClient.getApiImobiliaria().agregarInmu("Bearer "+token,imagenPart,inmuebleBody);
                llamada.enqueue(new Callback<Inmueble>() {
                    @Override
                    public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                        if (response.isSuccessful()){
                            mMensaje.setValue("Funciono");
                        }else {
                            mMensaje.setValue("No funciono");
                        }
                    }

                    @Override
                    public void onFailure(Call<Inmueble> call, Throwable t) {
                        mMensaje.setValue("Error Servidor");
                    }
                });
            }else{
                mMensaje.setValue("No se admiten Campos Vacios");
            }
        }catch (Exception ex){
            mMensaje.setValue("Debe Ingresar numeros");
            return;
        }

    }

    public void recibirFoto(ActivityResult result){
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            mUri.setValue(uri);
        }
    }
    private byte[] transformarImagen() {
        try {
            Uri uri = mUri.getValue();  //lo puedo usar porque estoy en viewmodel
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);//crea un canal para conectarse a un archivo
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch(FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }

    public LiveData<String> getMMensaje(){
        return mMensaje;
    }
    public LiveData<Uri> getMUri(){return  mUri;}
}