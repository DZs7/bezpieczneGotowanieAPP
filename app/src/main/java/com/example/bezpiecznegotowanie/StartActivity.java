package com.example.bezpiecznegotowanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.bezpiecznegotowanie.login.LoginController;

public class StartActivity extends AppCompatActivity {

    TextView strona, strona2, strSplash, strSubSplash, startSplash;
    Button startButton;
    Animation atg, btgone, btgtwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        strSubSplash = findViewById(R.id.strSubSplash);
        strSplash = findViewById(R.id.strSplash);
        strona = findViewById(R.id.strona);
        strona2 = findViewById(R.id.strona2);
        startSplash = findViewById(R.id.startSplash);
        startButton = findViewById(R.id.startBtn);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this, R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this, R.anim.btgtwo);

        startButton.startAnimation(btgtwo);
        startSplash.startAnimation(btgtwo);
        strSplash.startAnimation(atg);
        strSubSplash.startAnimation(atg);
        strona.startAnimation(btgone);
        strona2.startAnimation(btgone);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this, LoginController.class);
                startActivity(intent);

            }
        });

    }
}
