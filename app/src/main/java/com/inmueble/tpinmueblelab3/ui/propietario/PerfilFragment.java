package com.inmueble.tpinmueblelab3.ui.propietario;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentPerfilBinding;
import com.inmueble.tpinmueblelab3.ui.ActivityLoginViewModel;
import com.inmueble.tpinmueblelab3.ui.modelos.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    private PerfilViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv= new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.obtenerProp();
        mv.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etDni.setText(propietario.getDni());
                binding.etApellido.setText(propietario.getApellido());
                binding.etNombre.setText(propietario.getNombre());
                binding.etTelefono.setText(propietario.getTelefono());
                binding.etEmail.setText(propietario.getEmail());
            }
        });
        mv.getMActivo().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDni.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etEmail.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });
        mv.getMGuardar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btEditar.setText(s);
            }
        });
        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom=binding.etNombre.getText().toString();
                String ape=binding.etApellido.getText().toString();
                String dni=binding.etDni.getText().toString();
                String tel=binding.etTelefono.getText().toString();
                String mail=binding.etEmail.getText().toString();
                mv.guardar(binding.btEditar.getText().toString(),nom,ape,dni,tel,mail);
            }
        });
        binding.btContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_cambio);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}