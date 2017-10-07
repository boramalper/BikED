package org.boramalper.labs.biked;

import android.content.Intent;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


public class RentActivity extends AppCompatActivity {
    // private List<BikeRental> bikeRentals = new LinkedList<>();
    private BikeRental[] bikeRentals = {
        // 8 min -- 650 m
        new BikeRental("Biketrax",             "11 - 13 Lochrin Place",         "EH3 9QX",  "+44 131 228 6633", "info@biketrax.co.uk",        "https://www.biketrax.co.uk/info/bike_hire.php"),

        // 15 min -- 1.2 km
        new BikeRental("Cycle Scotland",       "29 Blackfriars St",             "EH1 1NB",  "+44 131 556 5560", null,                         "https://www.cyclescotland.co.uk"),

        // 25 min -- 2.1 km
        new BikeRental("Grease Monkey Cycles", "9C Craigmillar Castle Gardens", "EH16 4BP", "+44 758 846 1452", null,                         "http://greasemonkeycycles.com/"),

        // 37 min -- 3km
        new BikeRental("Leith Cycle CO",       "276 Leith Walk",                "EH6 5BX",  "+44 131 467 7775", "leith@leithcycleco.com",     "http://www.leithcycleco.com/hire"),

        // 56 min -- 4.6 km
        new BikeRental("Pedal Forth",          "17 East Cromwell St, Leith",    "EH6 6HD",  "+44 131 554 9990", null,                         "http://www.pedalforth.co.uk/?page_id=809"),

        // 1hr 10 min -- 5.6 km
        new BikeRental("Hart’s Cyclery",       "249A St. John's Road",          "EH12 7XD", "+44 131 334 1441", "graeme@harts-cyclery.co.uk", "http://www.harts-cyclery.co.uk/index.html"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.action_rent);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                if (item.getTitle().equals("Explore")) {
                    proceedTo(MainActivity.class, true);
                } else if (item.getTitle().equals("Account")) {
                    proceedTo(AccountActivity.class, true);
                }

                return true;
            }
        });

        findViewById(R.id.buttonBookNowCycleScotland).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedTo(ReservationActivity.class, false);
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
}
