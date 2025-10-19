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

public class PerfilViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mGuardar=new MutableLiveData<>();

    private MutableLiveData<Boolean> mActivo=new MutableLiveData<>();

    private MutableLiveData<Propietario> mPropietario=new MutableLiveData<>();

    private MutableLiveData<String> mMensaje= new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }


    public void obtenerProp(){
        String token= ApiClient.leerToken(getApplication());
        Call<Propietario> llamada=ApiClient.getApiImobiliaria().obtenerPropietario("Bearer "+token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mPropietario.postValue(response.body());
                }else {
                    mMensaje.setValue("Error En el Perfil");
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                mMensaje.setValue("Error En el Servidor");
            }
        });
    }

    public void guardar(String edi, String nom,String ape,String dni, String telefono, String mail){
        if(edi.equals("Editar")){
            mActivo.setValue(true);
            mGuardar.setValue("Guardar");
        }else{
            //validar campos
            if(validarCampos(nom,ape,dni,telefono,mail)){
                mActivo.setValue(false);
                mGuardar.setValue("Editar");
                Propietario prop=new Propietario(mPropietario.getValue().getIdPropietario(),dni,ape,nom,telefono,mail,null);
                String token=ApiClient.leerToken(getApplication());
                Call <Propietario> llamada=ApiClient.getApiImobiliaria().actualizarPropietario("bearer "+token,prop);
                llamada.enqueue(new Callback<Propietario>() {
                    @Override
                    public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                        if (response.isSuccessful()){
                            mMensaje.setValue("Usuario Actualizado");
                        }else {
                            mMensaje.setValue("Error al Actualizar");
                        }
                    }

                    @Override
                    public void onFailure(Call<Propietario> call, Throwable t) {
                        mMensaje.setValue("Error de Servidor");
                    }
                });
            }else {
                mMensaje.setValue("Error Campos Vacios");
            }
        }
    }

    public Boolean validarCampos(String nom,String ape,String dni, String telefono, String mail){
        Boolean valido=false;
        if(nom != null && !nom.isEmpty() && ape != null && !ape.isEmpty() && dni != null && !dni.isEmpty() && telefono != null && !telefono.isEmpty() && mail != null && !mail.isEmpty()){
            valido=true;
        }
        return valido;
    }

    public void borrarMensaje(){
        mMensaje.setValue(null);
    }
    public LiveData<String> getMGuardar() {
        return mGuardar;
    }
    public LiveData<Boolean> getMActivo(){return mActivo;}
    public LiveData<Propietario> getMPropietario(){return mPropietario;}
    public LiveData<String> getMMensaje(){return mMensaje;}
}
