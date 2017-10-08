package org.boramalper.labs.biked;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Locale;
import java.util.Scanner;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

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

        findViewById(R.id.buttonProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedTo(ReservationDoneActivity.class, false);
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
