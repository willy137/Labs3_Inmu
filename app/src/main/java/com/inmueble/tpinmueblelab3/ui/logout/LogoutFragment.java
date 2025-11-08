package com.inmueble.tpinmueblelab3.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    private FragmentLogoutBinding binding;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=FragmentLogoutBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        AlertaSalida.mostrarDialogoBoton(getContext());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

}