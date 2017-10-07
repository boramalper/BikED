package org.boramalper.labs.biked;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView button1;
    private TextView button2;
    private TextView button3;
    private TextView button4;
    private TextView button5;

    int[] images = {R.drawable.route1, R.drawable.route2, R.drawable.route3, R.drawable.route4, R.drawable.route5 };

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
        DiscreteScrollView discreteScrollView = (DiscreteScrollView) findViewById(R.id.discreteScrollView);
        // TODO: discreteScrollView.addOnItemChangedListener();
        discreteScrollView.setAdapter(InfiniteScrollAdapter.wrap(new RoutesAdapter(routes)));
        discreteScrollView.setItemTransitionTimeMillis(200);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());

        // </BORA ADDED THIS>

        image = (ImageView)findViewById(R.id.imageView1);

        /*
        button1= (TextView) findViewById(R.id.route1Text);
        button1.setOnClickListener(button1Listener);

        button2= (TextView) findViewById(R.id.route2Text);
        button2.setOnClickListener(button2Listener);

        button3= (TextView) findViewById(R.id.route3Text);
        button3.setOnClickListener(button3Listener);

        button4= (TextView) findViewById(R.id.route4Text);
        button4.setOnClickListener(button4Listener);

        button5= (TextView) findViewById(R.id.route5Text);
        button5.setOnClickListener(button5Listener);
        */

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

    View.OnClickListener button1Listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            image.setImageResource(images[0]);
        }
    };

    View.OnClickListener button2Listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            image.setImageResource(images[1]);
        }
    };

    View.OnClickListener button3Listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            image.setImageResource(images[2]);
        }
    };

    View.OnClickListener button4Listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            image.setImageResource(images[3]);
        }
    };

    View.OnClickListener button5Listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            image.setImageResource(images[4]);
        }
    };

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
