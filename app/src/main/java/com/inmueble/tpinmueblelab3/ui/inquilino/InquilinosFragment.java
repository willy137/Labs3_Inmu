package com.inmueble.tpinmueblelab3.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inmueble.tpinmueblelab3.databinding.FragmentInquilinoBinding;
import com.inmueble.tpinmueblelab3.ui.alquiler.ContratoAdapter;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinoBinding binding;

    private InquilinosViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv=new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.leerInmus();
        mv.getMInmus().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InquilinoAdapter adapter=new InquilinoAdapter(inmuebles,getContext());
                GridLayoutManager glm=new GridLayoutManager(getContext(),2);
                RecyclerView rv=binding.rvInmusAlquilados;
                rv.setAdapter(adapter);
                rv.setLayoutManager(glm);
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