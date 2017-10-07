package org.boramalper.labs.biked;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.TextView;

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

        image = (ImageView)findViewById(R.id.imageView1);

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
