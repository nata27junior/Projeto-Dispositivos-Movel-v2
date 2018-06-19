package com.example.natanael.fatec_saude;

import android.annotation.SuppressLint;
import android.os.Bundle;

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

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {


    private ConnectionHosp con = new ConnectionHosp();
    private ConnectionHosp2 con0 = new ConnectionHosp2();
    private ConnectionUBS con1 = new ConnectionUBS();
    private ConnectionClin con2 = new ConnectionClin();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

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
    @Override
    public void onMapReady(GoogleMap  map) {
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


        try {
            List<Marker> markers3 = con.getData();


            for (Marker marker3 : markers3) {
                String concatena0 = marker3.getMorada1() + marker3.getCodPostal();
                map.addMarker(new MarkerOptions().position(new LatLng(marker3.getLat(),
                        marker3.getLon())).title(marker3.getNome()).snippet(concatena0).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

            List<Marker> markers0 = con0.getData();


            for (final Marker marker0 : markers0) {
                final String concatena1 = marker0.getMorada1() + marker0.getCodPostal();
                map.addMarker(new MarkerOptions().position(new LatLng(marker0.getLat(),
                        marker0.getLon())).title(marker0.getNome()).snippet(concatena1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                //Toast.makeText(Activity,marker0.getMorada2(),Toast.LENGTH_LONG).show();


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {


            List<Marker> markers1 = con1.getData();


            for (Marker marker1 : markers1) {
                String concatena2 = marker1.getMorada1() + marker1.getCodPostal();
                map.addMarker(new MarkerOptions().position(new LatLng(marker1.getLat(),
                        marker1.getLon())).title(marker1.getNome()).snippet(concatena2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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