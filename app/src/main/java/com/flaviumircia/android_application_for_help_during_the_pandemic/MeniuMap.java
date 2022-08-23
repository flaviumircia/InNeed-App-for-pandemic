package com.flaviumircia.android_application_for_help_during_the_pandemic;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;

import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appscss.R;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MeniuMap extends FragmentActivity  {
    private static final String TAG = "";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private TextView coordinates;
    private MapView mapView;

    private final int duration = Toast.LENGTH_SHORT;
    private final CharSequence texts="No other user found";

    MyLocationNewOverlay locationOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_map);

        //getting the context and shared prefs
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        //instantiate the views
        Button shareLoc=(Button) findViewById(R.id.shareLoc);
        Button  call=(Button) findViewById(R.id.callBut);
        Button sendMsg=(Button) findViewById(R.id.sendMsgBut);
        Button ajutor=(Button) findViewById(R.id.helpBut);
        Context conext = getApplicationContext();
        //mapview
        mapView=findViewById(R.id.frameMapLayout);
        GeoPoint startPoint = new GeoPoint(44.439663, 26.096306);
        MapController mapController=new MapController(mapView);

        //map customizations (like startPoint, get the current location, tile source etc)
        mapCustom(mapView,mapController,startPoint);

        View.OnClickListener meniuMapListener= v -> {
            Toast toast = Toast.makeText(conext, texts, duration);
            toast.show();
        };

        shareLoc.setOnClickListener(meniuMapListener);
        call.setOnClickListener(meniuMapListener);
        ajutor.setOnClickListener(meniuMapListener);
        sendMsg.setOnClickListener(meniuMapListener);

    }

    private void mapCustom(MapView mapView,MapController mapController,GeoPoint startPoint) {
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        mapController.setCenter(startPoint);
        mapController.setZoom(11);

        GpsMyLocationProvider provider = new GpsMyLocationProvider(this);
        provider.addLocationSource(LocationManager.NETWORK_PROVIDER);

        locationOverlay= new MyLocationNewOverlay(mapView);
        locationOverlay.enableFollowLocation();
        locationOverlay.runOnFirstFix(() -> Log.d("MyTag", String.format("First location fix: %s", locationOverlay.getLastFix())));

        mapView.getOverlayManager().add(locationOverlay);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

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
