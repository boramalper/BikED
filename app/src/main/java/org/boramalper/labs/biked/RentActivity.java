package org.boramalper.labs.biked;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class RentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        ((BottomNavigationView) findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                if (item.getTitle().equals("Explore")) {
                    proceedTo(MainActivity.class, true);
                } else if (item.getTitle().equals("Account")) {
                    ; // TODO empty
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
}
