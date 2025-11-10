package com.inmueble.tpinmueblelab3.ui.ubicacion;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionViewModel extends ViewModel {
    private static final LatLng inmo=new LatLng(-33.150720,-66.306864);

    private final MutableLiveData<String> mText;
    private final MapaActual mapaActualCallback = new MapaActual();

    public UbicacionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public MapaActual getMapaActualCallback() {
        return mapaActualCallback;
    }

    public class MapaActual implements OnMapReadyCallback {
        private GoogleMap mapa;
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            this.mapa=googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.addMarker(new MarkerOptions().position(inmo).title("Inmbiliaria ULP"));
            CameraPosition cam=new CameraPosition.Builder()
                    .target(inmo)
                    .zoom(15)
                    .bearing(0)
                    .tilt(0)
                    .build();
            CameraUpdate update= CameraUpdateFactory.newCameraPosition(cam);
            googleMap.animateCamera(update);

        }
    }
}