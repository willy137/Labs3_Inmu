package com.inmueble.tpinmueblelab3.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentInmuebleBinding;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel mv;

    private FragmentInmuebleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentInmuebleBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        mv = new ViewModelProvider(this).get(InmuebleViewModel.class);
        mv.leerInmus();
        mv.getInmus().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleAdapter adapter=new InmuebleAdapter(inmuebles,getContext());
                GridLayoutManager glm=new GridLayoutManager(getContext(),2);
                RecyclerView rv=binding.rvLista;
                rv.setAdapter(adapter);
                rv.setLayoutManager(glm);
            }
        });
        binding.fbAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.nav_agregarinmu);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}