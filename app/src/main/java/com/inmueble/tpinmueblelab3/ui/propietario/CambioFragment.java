package com.inmueble.tpinmueblelab3.ui.propietario;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentCambioBinding;
import com.inmueble.tpinmueblelab3.databinding.FragmentPerfilBinding;

public class CambioFragment extends Fragment {

    private CambioViewModel mv;

    private FragmentCambioBinding binding;

    public static CambioFragment newInstance() {
        return new CambioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCambioBinding.inflate(inflater, container, false);
        mv=new ViewModelProvider(this).get(CambioViewModel.class);
        View root = binding.getRoot();

        mv.getmMensaje1().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensaje.setText(s);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding != null) {
                        binding.tvMensaje.setText("");
                    }
                }, 1000);
            }
        });
        binding.btCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String actual=binding.etActual.getText().toString();
                String nueva=binding.etNueva.getText().toString();
                String nueva2=binding.etNueva2.getText().toString();

                mv.guardarContra(actual,nueva,nueva2);
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