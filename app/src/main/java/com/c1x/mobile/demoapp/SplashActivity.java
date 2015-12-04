package com.c1x.mobile.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import c1x.lib.adhive.AdHiveApplication;

/**
 * Created by arindamnath on 25/03/15.
 */
public class SplashActivity extends Activity {

    private Animation animFast, animSlow, animVerySlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AdHiveApplication.setKey("");
        AdHiveApplication.setBackfillID("ca-app-pub-9523065396963099/5180095963");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        animFast = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.float_anim);
        animSlow = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.float_anim_slow);
        animVerySlow = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.float_anim_very_slow);
        findViewById(R.id.splash_image_1).startAnimation(animFast);
        findViewById(R.id.splash_image_2).startAnimation(animSlow);
        findViewById(R.id.splash_text_container).startAnimation(animVerySlow);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3500);
    }
}
