package org.boramalper.labs.biked;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.kml.KmlLayer;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Route routeBlackfordMeadows = new Route(
                "Blackford Hill & the Meadows",
                "Blackford Hill\n&\nthe Meadows",
                Color.parseColor("#4daf4a"),
                "Intermediate",
                12.7f,
                (new String[] {"Green", "Historic", "Nature"}),
                ""
        ), routeBotanicBritannia = new Route(
                "Botanic Gardens & Royal Yacht Britannia",
                "Botanic Gardens\n&\nRoyal Yacht Britannia",
                Color.parseColor("#e41a1c"),
                "Easy",
                15f,
                (new String[] {"Historic", "Sea", "Green"}),
                "Start your trip and cycle to the Royal Botanic Gardens. Take a stroll around before heading to Ocean Terminal along the waters of Leith. You’ll arrive at The Royal Yacht Britannia, one of Scotland’s best visitor attractions. Your trip further takes you to the Centre of Leith, along Leith Links and Carlton Hill and back into the city Centre."
        ), routePortobelloHolyrood = new Route(
                "Portobello Beach & Holyrood Park",
                "Portobello Beach\n&\nHolyrood Park",
                Color.parseColor("#377eb7"),
                "Intermediate",
                15.4f,
                (new String[] {"Beach", "Green", "Tunnel"}),
                "Pick up a bike and head East towards the coast. Your passing the parliament, the northern side of Holyrood Park and find yourself in Portobello Beach which is perfect to spend a few hours by the sea not far from the city centre of Edinburgh. Cycle along the nice promenade and enjoy a coffee in a beachside café. The way back brings you again to Holyrood Park, and through the old railway tunnel."
        ), routeInverleithStockbridge = new Route(
                "Inverleith Park & Stockbridge",
                "Inverleith Park\n&\nStockbridge",
                Color.parseColor("#9a4ea3"),
                "Easy",
                11.5f,
                (new String[] {"Green", "Food", "Drinks"}),
                ""
        ), routeCrammond = new Route(
                "Crammond Island",
                "\nCrammond Island\n",
                Color.parseColor("#ff7f00"),
                "Easy",
                20.5f,
                (new String[] {"Sea", "Island", "Historic"}),
                ""
        );

        final Route[] routes = {
                routeBlackfordMeadows,
                routeBotanicBritannia,
                routePortobelloHolyrood,
                routeInverleithStockbridge,
                routeCrammond
        };

        // ====================================================================================== \\
        //                                D O   N O T   T O U C H
        //                                                                      full of little hacks
        DiscreteScrollView discreteScrollView = findViewById(R.id.discreteScrollView);
        discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RoutesAdapter.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RoutesAdapter.ViewHolder viewHolder, int adapterPosition) {
                for (Route route: routes) {
                    if (route.kmlLayer != null) {
                        try {
                            route.kmlLayer.removeLayerFromMap();
                        } catch (NullPointerException exc) {
                    /* IGNORE */
                        }
                    }
                }

                if (routes[(adapterPosition - 3) % routes.length].kmlLayer != null) {
                    try {
                        routes[(adapterPosition - 3) % routes.length].kmlLayer.addLayerToMap();
                    } catch (org.xmlpull.v1.XmlPullParserException | java.io.IOException exc) {
                        /* IGNORE */
                    }
                }
            }
        });
        discreteScrollView.setAdapter(InfiniteScrollAdapter.wrap(new RoutesAdapter(this, routes)));
        discreteScrollView.setItemTransitionTimeMillis(200);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                try {
                    routeBlackfordMeadows.kmlLayer = new KmlLayer(googleMap, R.raw.blackford_meadows, getApplicationContext());
                    routeBotanicBritannia.kmlLayer = new KmlLayer(googleMap, R.raw.botanic_britannia, getApplicationContext());
                    routePortobelloHolyrood.kmlLayer = new KmlLayer(googleMap, R.raw.portobello_holyrood, getApplicationContext());
                    routeInverleithStockbridge.kmlLayer = new KmlLayer(googleMap, R.raw.inverleith_stockbridge, getApplicationContext());
                    routeCrammond.kmlLayer = new KmlLayer(googleMap, R.raw.crammond, getApplicationContext());
                } catch (org.xmlpull.v1.XmlPullParserException | java.io.IOException exc) {
                    /* IGNORE */
                }

                try {
                    Thread.sleep(1000);
                } catch (java.lang.InterruptedException exc) {
                        /* IGNORE */
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(55.919581, -3.314071), new LatLng(55.992527, -3.097852)), 0));
            }
        });

        // ====================================================================================== \\


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
