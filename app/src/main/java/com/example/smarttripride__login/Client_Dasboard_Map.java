package com.example.smarttripride__login;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smarttripride__login.databinding.ActivityClientDasboardMapBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

public class Client_Dasboard_Map extends FragmentActivity implements OnMapReadyCallback {

    FloatingActionButton floatAdd, floatSend, floatSearch;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;

    boolean isOpen = false;

    private GoogleMap mMap;
  ///  private ActivityClientDasboardMapBinding binding;
    private LocationRequest locationRequest;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    private LatLng latLng;

    Button Scan;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dasboard_map);


        floatAdd = (FloatingActionButton) findViewById(R.id.float_add);
        floatSend = (FloatingActionButton) findViewById(R.id.float_send);
        floatSearch = (FloatingActionButton) findViewById(R.id.float_search);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, getPackageManager().PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, getPackageManager().PERMISSION_GRANTED);

        FirebaseMessaging.getInstance().subscribeToTopic("all");
        //Scan = (Button) findViewById(R.id.Scan_drivers);
        floatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Onscan();
            }
        });

        floatSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchDriver();
            }
        });

    }


   //  private void OpenScan() {
   //      startActivity(new Intent(Client_Dasboard_Map.this, Scan.class));
   //  }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       /* LatLng sydney = new LatLng(-34,151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        // For Map Location.................................
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                try {
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                } catch (SecurityException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
        // For Map Location.................................

    }

  public void Onscan(){
      String title = "Smart Ride Trip";
      String message = "A client request for a pickup";

      FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all", title, message, getApplicationContext(), Client_Dasboard_Map.this);
      notificationsSender.SendNotifications();
      Toast.makeText(this, "REQUEST SENT", Toast.LENGTH_SHORT).show();
  }



   public void SearchDriver(){
       //Loading loading = new Loading(Client_Dasboard_Map.this);
       //loading.startLoadingDialog();
       startActivity(new Intent(Client_Dasboard_Map.this, Client_Scan.class));
   }


    private void animateFab(){
        if (isOpen){
            floatAdd.startAnimation(rotateForward);
            floatSend.startAnimation(fabClose);
            floatSearch.startAnimation(fabClose);
            floatSend.setClickable(false);
            floatSearch.setClickable(false);
            isOpen=false;
        }else {
            floatAdd.startAnimation(rotateBackward);
            floatSend.startAnimation(fabOpen);
            floatSearch.startAnimation(fabOpen);
            floatSend.setClickable(true);
            floatSearch.setClickable(true);
            isOpen=true;
        }
    }


}