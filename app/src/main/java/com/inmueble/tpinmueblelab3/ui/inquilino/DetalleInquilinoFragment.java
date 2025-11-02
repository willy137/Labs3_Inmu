package com.inmueble.tpinmueblelab3.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentDetalleContratoBinding;
import com.inmueble.tpinmueblelab3.databinding.FragmentDetalleInquilinoBinding;
import com.inmueble.tpinmueblelab3.ui.modelos.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mv;

    public FragmentDetalleInquilinoBinding binding;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentDetalleInquilinoBinding.inflate(getLayoutInflater(),container,false);
        View root=binding.getRoot();
        mv=new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        mv.leerInqui(getArguments());
        mv.getMInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigoInqui.setText(inquilino.getIdInquilino()+"");
                binding.tvNombreInqui.setText(inquilino.getNombre());
                binding.tvApellidoInqui.setText(inquilino.getApellido());
                binding.tvDniInqui.setText(inquilino.getDni()+"");
                binding.tvMailInqui.setText(inquilino.getEmail());
                binding.tvTelefonoInqui.setText(inquilino.getTelefono());
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}