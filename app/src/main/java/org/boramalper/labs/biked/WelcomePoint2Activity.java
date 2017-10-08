package org.boramalper.labs.biked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class WelcomePoint2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_point2);

        findViewById(R.id.buttonWelcome2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });
    }

    private void proceed() {
        Intent intent = new Intent(this, WelcomePoint3Activity.class);
        startActivity(intent);
    }
}
