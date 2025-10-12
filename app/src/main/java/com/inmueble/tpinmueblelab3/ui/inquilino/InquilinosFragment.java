package com.inmueble.tpinmueblelab3.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inmueble.tpinmueblelab3.databinding.FragmentInquilinoBinding;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinosViewModel inquilinosViewModel =
                new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        inquilinosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}