package com.inmueble.tpinmueblelab3.ui;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.ActivityLoginBinding;
import com.inmueble.tpinmueblelab3.databinding.ActivityMenuBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginViewModel mv;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ActivityLoginViewModel.class);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=binding.etUsuario.getText().toString();
                String password=binding.etPassword.getText().toString();
                mv.inicio(binding.etUsuario.getText().toString(),binding.etPassword.getText().toString());
            }
        });

    }
}