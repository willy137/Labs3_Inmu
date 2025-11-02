package com.inmueble.tpinmueblelab3.ui.pagos;

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
import com.inmueble.tpinmueblelab3.databinding.FragmentPagoBinding;
import com.inmueble.tpinmueblelab3.ui.modelos.Pago;

import java.util.List;

public class PagoFragment extends Fragment {

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    private PagoViewModel mv;

    private FragmentPagoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentPagoBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        mv=new ViewModelProvider(this).get(PagoViewModel.class);
        mv.leerPagos(getArguments());
        mv.getMPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagosAdapter adapter=new PagosAdapter(pagos,getContext());
                GridLayoutManager mg=new GridLayoutManager(getContext(),1);
                RecyclerView rv=binding.rvListaPagos;
                rv.setAdapter(adapter);
                rv.setLayoutManager(mg);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mv = new ViewModelProvider(this).get(PagoViewModel.class);
        // TODO: Use the ViewModel
    }

}