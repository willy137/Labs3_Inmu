package com.inmueble.tpinmueblelab3.ui;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inmueble.tpinmueblelab3.databinding.ActivityLoginBinding;
import com.inmueble.tpinmueblelab3.ui.propietario.CambioViewModel;

import android.hardware.SensorEventListener;


public class LoginActivity extends AppCompatActivity implements  SensorEventListener{

    private ActivityLoginViewModel mv;

    private ActivityLoginBinding binding;

    //Variables para el evento de llamar al momento de agitar el celu
    private SensorManager sg;
    private Sensor sensor;
    private long lastUpdate = 0;
    private static final int velocidad = 2000;
    public  boolean detectar=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv=new ViewModelProvider(this).get(ActivityLoginViewModel.class);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        solicitarPermisos();
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=binding.etUsuario.getText().toString();
                String password=binding.etPassword.getText().toString();
                mv.inicio(user,password);
            }
        });
        mv.getMMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeLogin.setText(s);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding != null) {
                        binding.tvMensajeLogin.setText("");
                    }
                }, 1000);
            }
        });
        sg = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sg.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sg.registerListener(this, sensor , SensorManager.SENSOR_DELAY_NORMAL);

        setContentView(binding.getRoot());
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            long hora = System.currentTimeMillis();
            if ((hora - lastUpdate) > 100) {
                long diferencia = (hora - lastUpdate);
                lastUpdate = hora;
                float velocidadMov = Math.abs(x + y + z)/ diferencia * 10000;
                if (velocidadMov > velocidad && detectar) {
                    detectar = false;
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        detectar=true;
                    }, 2000);
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:0266 445-2000"));
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void solicitarPermisos(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1000);
        }
    }


}