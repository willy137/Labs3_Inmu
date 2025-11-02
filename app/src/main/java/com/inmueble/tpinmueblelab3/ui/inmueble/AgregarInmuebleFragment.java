package com.inmueble.tpinmueblelab3.ui.inmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentAgregarInmuebleBinding;
import com.inmueble.tpinmueblelab3.databinding.FragmentDetalleInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel mv;

    private FragmentAgregarInmuebleBinding binding;

    private ActivityResultLauncher<Intent> arl;

    private Intent intent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv=new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);
        binding = FragmentAgregarInmuebleBinding.inflate(getLayoutInflater());
        View root=binding.getRoot();
        abrirGaleria();
        binding.btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dire=binding.etDireccion.getText().toString();
                String precio=binding.etPrecio.getText().toString();
                String uso=binding.etUso.getText().toString();
                String tipo=binding.etTipo.getText().toString();
                String superfi=binding.etSuper.getText().toString();
                String ambientes=binding.etAmbiente.getText().toString();
                boolean disponible=binding.cbDisponible.isChecked();
                mv.guardarInmu(dire,precio,uso,tipo,superfi,ambientes,disponible);
            }
        });
        binding.btFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });
        mv.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeAgregar.setText(s);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding != null) {
                        binding.tvMensajeAgregar.setText("");
                    }
                }, 1000);
            }
        });

        mv.getMUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivFoto.setImageURI(uri);
            }
        });
        return root;
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                mv.recibirFoto(result);
            }
        });
    }
}