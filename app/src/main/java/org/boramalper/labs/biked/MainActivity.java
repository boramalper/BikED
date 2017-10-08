package org.boramalper.labs.biked;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.maps.android.data.kml.KmlLayer;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Route[] routes = {
                new Route(
                    "Botanic Gardens & Royal Yacht Britannia",
                    "Botanic Gardens\n&\nRoyal Yacht Britannia",
                    Color.parseColor("#e41a1c"),
                    "Easy",
                    15f,
                    (new String[] {"Historic", "Sea", "Green"}),
                    "Start your trip and cycle to the Royal Botanic Gardens. Take a stroll around before heading to Ocean Terminal along the waters of Leith. You’ll arrive at The Royal Yacht Britannia, one of Scotland’s best visitor attractions. Your trip further takes you to the Centre of Leith, along Leith Links and Carlton Hill and back into the city Centre."
                ),
                new Route(
                    "Portobello Beach & Holyrood Park",
                    "Portobello Beach\n&\nHolyrood Park",
                    Color.parseColor("#377eb7"),
                    "Intermediate",
                    15.4f,
                    (new String[] {"Beach", "Green", "Tunnel"}),
                    "Pick up a bike and head East towards the coast. Your passing the parliament, the northern side of Holyrood Park and find yourself in Portobello Beach which is perfect to spend a few hours by the sea not far from the city centre of Edinburgh. Cycle along the nice promenade and enjoy a coffee in a beachside café. The way back brings you again to Holyrood Park, and through the old railway tunnel."
                ),
                new Route(
                    "Blackford Hill & the Meadows",
                    "Blackford Hill\n&\nthe Meadows",
                    Color.parseColor("#4daf4a"),
                    "Intermediate",
                    12.7f,
                    (new String[] {"Green", "Historic", "Nature"}),
                    ""
                ),
                new Route(
                    "Inverleith Park & Stockbridge",
                    "Inverleith Park\n&\nStockbridge",
                    Color.parseColor("#9a4ea3"),
                    "Easy",
                    11.5f,
                    (new String[] {"Green", "Food", "Drinks"}),
                    ""
                ),
                new Route(
                    "Crammond Island",
                    "\nCrammond Island\n",
                    Color.parseColor("#ff7f00"),
                    "Easy",
                    20.5f,
                    (new String[] {"Sea", "Island", "Historic"}),
                    ""
                )
        };

        // <BORA ADDED THIS>
        DiscreteScrollView discreteScrollView = findViewById(R.id.discreteScrollView);
        // TODO: discreteScrollView.addOnItemChangedListener();
        discreteScrollView.setAdapter(InfiniteScrollAdapter.wrap(new RoutesAdapter(routes)));
        discreteScrollView.setItemTransitionTimeMillis(200);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());
        // </BORA ADDED THIS>

        // <BORA ADDED THAT>
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                try {
                    KmlLayer layer = new KmlLayer(googleMap, R.raw.blackford_meadows, getApplicationContext());
                    layer.addLayerToMap();
                } catch (org.xmlpull.v1.XmlPullParserException | java.io.IOException exc) {
                    /* IGNORE */
                }
            }
        });
        // </BORA ADDED THAT>


        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.action_explore);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                if (item.getTitle().equals("Rent")) {
                    proceedTo(RentActivity.class, true);
                } else if (item.getTitle().equals("Account")) {
                    proceedTo(AccountActivity.class, true);
                }

                return true;
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
        // Disable back button travelling between Rent, Explore, and Account activities
        // (maintain the illusion!)
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
