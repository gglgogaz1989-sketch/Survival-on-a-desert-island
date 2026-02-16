package com.surviveonstrange.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class LoadingActivity extends Activity {

    ProgressBar bar;
    Button start;
    RelativeLayout root;
    int progress = 0;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_loading);

        bar = findViewById(R.id.bar);
        start = findViewById(R.id.startBtn);
        root = findViewById(R.id.root);

        start.setEnabled(false);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                progress++;
                bar.setProgress(progress);

                if (progress >= 100) {
                    showButton();
                } else h.postDelayed(this, 30);
            }
        }, 30);

        start.setOnClickListener(v -> exitScreen());
    }

    void showButton() {
        start.setEnabled(true);
        start.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(400)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    void exitScreen() {
        root.animate()
                .translationY(-root.getHeight())
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(() -> {
                    // Переход в следующее Activity (пока пусто)
                    finish();
                })
                .start();
    }
          }
