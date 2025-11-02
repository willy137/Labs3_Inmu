package com.inmueble.tpinmueblelab3.ui.alquiler;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentDetalleContratoBinding;
import com.inmueble.tpinmueblelab3.ui.inmueble.DetalleInmuebleViewModel;
import com.inmueble.tpinmueblelab3.ui.modelos.Contrato;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel mv;

    private FragmentDetalleContratoBinding binding;

    public static DetalleContratoFragment newInstance() {
        return new DetalleContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentDetalleContratoBinding.bind(getLayoutInflater().inflate(R.layout.fragment_detalle_contrato,container,false));
        mv=new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        View root=binding.getRoot();
        mv.obtenerContrato(getArguments());

        mv.getMContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.tvCodigo.setText(contrato.getIdContrato()+"");
                binding.tvFechaInicio.setText(contrato.getFechaInicio()+"");
                binding.tvFechaFin.setText(contrato.getFechaFinalizacion()+"");
                binding.tvMonto.setText(contrato.getMontoAlquiler()+"");
                binding.tvInquilino.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
                binding.tvInmueble.setText(contrato.getInmueble().getDireccion());
            }
        });

        binding.btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                int id=mv.getMContrato().getValue().getIdContrato();
                bundle.putInt("idPago",id);
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment_content_main).navigate(R.id.nav_pago,bundle);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}