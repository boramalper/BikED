package org.boramalper.labs.biked;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.kml.KmlLayer;

public class Navigation1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation1);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        final Navigation1Activity nav1Act = this;
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                try {
                    KmlLayer kmlLayer = new KmlLayer(googleMap, R.raw.navigation, getApplicationContext());
                    kmlLayer.addLayerToMap();
                } catch (org.xmlpull.v1.XmlPullParserException | java.io.IOException exc) {
                    /* IGNORE */
                }

                try {
                    Thread.sleep(1000);
                } catch (java.lang.InterruptedException exc) {
                    /* IGNORE */
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.947181, -3.201047), 18));
                if (ActivityCompat.checkSelfPermission(nav1Act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(nav1Act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(nav1Act, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 31);

                    return;
                }
                googleMap.setMyLocationEnabled(true);

            }
        });
    }

    private void proceedTo(Class<?> cls, boolean noAnimation) {
        Intent intent = new Intent(this, cls);
        if (noAnimation) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        startActivity(intent);
        if (noAnimation) {
            overridePendingTransition(0, 0);
        }
    }


    @Override
    public void onBackPressed() {
        proceedTo(MainActivity.class, true);
    }

    protected MapView mMapView;

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mMapView != null) {
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mMapView != null) {
            try {
                mMapView.onDestroy();
            } catch (NullPointerException e) {
                Log.e("BIKED", "Error while attempting MapView.onDestroy(), ignoring exception", e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null) {
            mMapView.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }
}
