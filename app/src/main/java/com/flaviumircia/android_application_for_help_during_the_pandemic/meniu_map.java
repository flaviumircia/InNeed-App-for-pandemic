package com.flaviumircia.android_application_for_help_during_the_pandemic;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appscss.R;

import java.security.Provider;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static androidx.core.location.LocationManagerCompat.isLocationEnabled;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class meniu_map extends FragmentActivity  {
    private static final String TAG = "";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private TextView coordinates;
    LocationManager locationManager;
    private MapView mapView;
    Location arg1 = null;
    public double latitude;
    public double longitude;
    private Object FusedLocationProviderClient;
    MyLocationNewOverlay locationOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_map);
        Button shareLoc=(Button) findViewById(R.id.shareLoc);
        Button  call=(Button) findViewById(R.id.callBut);
        Button sendMsg=(Button) findViewById(R.id.sendMsgBut);
        Button ajutor=(Button) findViewById(R.id.helpBut);
        Context conext = getApplicationContext();
        CharSequence texts="No other user found";
        int duration = Toast.LENGTH_SHORT;
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        mapView=findViewById(R.id.frameMapLayout);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        GeoPoint startPoint = new GeoPoint(44.439663, 26.096306);
        MapController mapController=new MapController(mapView);
        mapController.setCenter(startPoint);
        mapController.setZoom(11);
        GpsMyLocationProvider provider = new GpsMyLocationProvider(this);
        provider.addLocationSource(LocationManager.NETWORK_PROVIDER);
        locationOverlay= new MyLocationNewOverlay(mapView);
        locationOverlay.enableFollowLocation();
        locationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                Log.d("MyTag", String.format("First location fix: %s", locationOverlay.getLastFix()));
            }
        });
        mapView.getOverlayManager().add(locationOverlay);
        View.OnClickListener meniuMapListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(conext, texts, duration);
                toast.show();

            }
        };

        shareLoc.setOnClickListener(meniuMapListener);
        call.setOnClickListener(meniuMapListener);
        ajutor.setOnClickListener(meniuMapListener);
        sendMsg.setOnClickListener(meniuMapListener);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        super.onResume();
        //add
        locationOverlay.disableMyLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        //add
        locationOverlay.enableMyLocation();
    }
}
