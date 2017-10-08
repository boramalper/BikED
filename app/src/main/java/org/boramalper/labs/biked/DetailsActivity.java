package org.boramalper.labs.biked;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.action_explore);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().equals("Rent")) {
                    proceedTo(RentActivity.class, true);
                } else if (item.getTitle().equals("Explore")) {
                    proceedTo(MainActivity.class, true);
                } else if (item.getTitle().equals("Account")) {
                    proceedTo(AccountActivity.class, true);
                }

                return true;
            }
        });

        findViewById(R.id.buttonGetBiked).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedTo(RentActivity.class, false);
            }
        });

        final DetailsActivity detailsActivity = this;
        findViewById(R.id.buttonNavigate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(detailsActivity).create();
                alertDialog.setTitle("Incomplete");
                alertDialog.setMessage("We'll navigate you instead to a bike rental for demonstration purposes.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                proceedTo(Navigation1Activity.class, false);
                            }
                        });
                alertDialog.show();
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
}
