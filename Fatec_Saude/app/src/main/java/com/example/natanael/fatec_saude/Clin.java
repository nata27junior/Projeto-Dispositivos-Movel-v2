package com.example.natanael.fatec_saude;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Clin extends SupportMapFragment implements OnMapReadyCallback {


    private ConnectionClin con2 = new ConnectionClin();
    private LocationManager locationManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync((OnMapReadyCallback) this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")

    public void onMapReady(GoogleMap map) {
        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true);
        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true);
        options.mapType(GoogleMap.MAP_TYPE_NORMAL)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true);


        map.getUiSettings().setZoomControlsEnabled(true);
        //map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        //map.setMinZoomPreference(5.0f);

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(-23.17944, -45.88694));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(9);
        map.moveCamera(center);
        map.animateCamera(zoom);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);

        //mostra qual esta ativa
        Toast.makeText(getActivity(), "Provider: " + provider, Toast.LENGTH_LONG);


        try {


            List<Marker> markers2 = con2.getData();

            for (Marker marker2 : markers2) {
                String concatena3 = marker2.getMorada1() + marker2.getCodPostal();
                map.addMarker(new MarkerOptions().position(new LatLng(marker2.getLat(),
                        marker2.getLon())).title(marker2.getNome()).snippet(concatena3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
