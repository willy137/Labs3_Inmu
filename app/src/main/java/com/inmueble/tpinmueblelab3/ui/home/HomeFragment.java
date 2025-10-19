package com.inmueble.tpinmueblelab3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private static final LatLng inmo=new LatLng(-33.150720,-66.306864);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SupportMapFragment smf=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.flMap);
        smf.getMapAsync(new MapaActual());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            googleMap.addMarker(new MarkerOptions().position(inmo).title("Inmbiliaria ULP"));
            CameraPosition cam=new CameraPosition.Builder()
                    .target(inmo)
                    .zoom(17)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update= CameraUpdateFactory.newCameraPosition(cam);
            googleMap.animateCamera(update);

        }
    }

}