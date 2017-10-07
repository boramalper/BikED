package org.boramalper.labs.biked;

import android.app.DatePickerDialog;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class RentActivity extends AppCompatActivity {

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

        findViewById(R.id.buttonCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+441315565560"));
                startActivity(intent);
            }
        });

        findViewById(R.id.editTextDateFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextDateFrom = (EditText) findViewById(R.id.editTextDateFrom);
                String dateString = editTextDateFrom.getText().toString();
                // TODO: Adjust according to today
                int year = 2017, month = 10 - 1, day = 8;
                if (dateString.length() == 10) {
                    Scanner scanner = new Scanner(dateString);
                    scanner.useDelimiter("-");
                    year = scanner.nextInt();
                    month = scanner.nextInt() - 1;
                    day = scanner.nextInt();
                }

                new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editTextDateFrom.setText(String.format(Locale.US, "%4d-%02d-%02d", year, month + 1, dayOfMonth));
                            }
                        },
                        year,
                        month,
                        day
                ).show();
            }
        });

        findViewById(R.id.editTextDateTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextDateTo = (EditText) findViewById(R.id.editTextDateTo);
                String dateString = editTextDateTo.getText().toString();
                // TODO: Adjust according to today
                int year = 2017, month = 10 - 1, day = 8;
                if (dateString.length() == 10) {
                    Scanner scanner = new Scanner(dateString);
                    scanner.useDelimiter("-");
                    year = scanner.nextInt();
                    month = scanner.nextInt() - 1;
                    day = scanner.nextInt();
                }

                new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                editTextDateTo.setText(String.format(Locale.US, "%4d-%02d-%02d", year, month + 1, dayOfMonth));
                            }
                        },
                        year,
                        month,
                        day
                ).show();
            }
        });

        findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedTo(RentSearchResultsActivity.class, false);
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
