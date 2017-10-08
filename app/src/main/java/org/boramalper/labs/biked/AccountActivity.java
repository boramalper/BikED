package org.boramalper.labs.biked;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.action_account);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                if (item.getTitle().equals("Rent")) {
                    proceedTo(RentActivity.class, true);
                } else if (item.getTitle().equals("Explore")) {
                    proceedTo(MainActivity.class, true);
                }

                return true;
            }
        });

        List<BarEntry> datasetDistance = new ArrayList<>();
        datasetDistance.add(new BarEntry(2, 9.8f));
        datasetDistance.add(new BarEntry(3, 15.2f));
        datasetDistance.add(new BarEntry(4, 5.1f));
        datasetDistance.add(new BarEntry(5, 6.9f));
        datasetDistance.add(new BarEntry(6, 9.6f));
        datasetDistance.add(new BarEntry(7, 7.3f));
        datasetDistance.add(new BarEntry(8, 3.2f));

        BarChart chartDistance = findViewById(R.id.chartDistance);

        BarDataSet barDataSetDistance = new BarDataSet(datasetDistance, "Distance");
        barDataSetDistance.setColor(Color.parseColor("#3F51B5"));

        BarData barDataDistance = new BarData(barDataSetDistance);
        barDataDistance.setValueTextSize(12);

        Description description = new Description();
        description.setText("");
        chartDistance.setDescription(description);
        chartDistance.setData(barDataDistance);
        chartDistance.invalidate();
        chartDistance.getXAxis().setTextSize(12);
        chartDistance.getAxisLeft().setDrawLabels(false);
        chartDistance.getAxisRight().setDrawLabels(false);
        chartDistance.getLegend().setEnabled(false);

        //////////////////////////////////////////////////////////

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
