package com.inmueble.tpinmueblelab3.ui.alquiler;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentContratoBinding;
import com.inmueble.tpinmueblelab3.databinding.FragmentInmuebleBinding;
import com.inmueble.tpinmueblelab3.ui.inmueble.InmuebleAdapter;
import com.inmueble.tpinmueblelab3.ui.inmueble.InmuebleViewModel;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;

import java.util.List;

public class ContratoFragment extends Fragment {

    private ContratoViewModel mv;

    private FragmentContratoBinding binding;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentContratoBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        mv = new ViewModelProvider(this).get(ContratoViewModel.class);
        mv.leerInmus();
        mv.getMInmus().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                ContratoAdapter adapter=new ContratoAdapter(inmuebles,getContext());
                GridLayoutManager glm=new GridLayoutManager(getContext(),2);
                RecyclerView rv=binding.rvLista;
                rv.setAdapter(adapter);
                rv.setLayoutManager(glm);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}