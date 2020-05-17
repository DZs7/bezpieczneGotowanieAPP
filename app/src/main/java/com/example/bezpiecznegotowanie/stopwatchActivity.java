package com.example.bezpiecznegotowanie;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class stopwatchActivity extends AppCompatActivity {

    Button btnstart, btnstop;
    ImageView wskazowka;
    Animation roundingalone;
    Chronometer timeHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        wskazowka = findViewById(R.id.wskazowka);
        timeHere = findViewById(R.id.timeHere);

        btnstop.setAlpha(0);

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wskazowka.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();

                timeHere.setBase(SystemClock.elapsedRealtime());
                timeHere.start();
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                //stop animation

                wskazowka.clearAnimation();

                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();

                btnstop.animate().alpha(0).setDuration(300).start();

                //stop timer

                timeHere.stop();

            }

        });
    }
}
