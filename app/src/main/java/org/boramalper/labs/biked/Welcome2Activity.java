package org.boramalper.labs.biked;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Welcome2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("firstTime", 0);

        if (!settings.getBoolean("isFirstTime", true)) {
            proceed();
        } else {
            settings.edit().putBoolean("isFirstTime", false).apply();
        }

        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome2);

        findViewById(R.id.buttonWelcome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });
    }

    private void proceed() {
        Intent intent = new Intent(this, WelcomePoint1Activity.class);
        startActivity(intent);
    }
}
