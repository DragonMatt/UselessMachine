package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private Switch useless;
    private Button self_destruct;
    private ConstraintLayout constraintLayout;
    private long lastTime = 10000;
    private long duration = 500;
    private boolean red = false;
    private Button look_busy;
    private ProgressBar progress;
    private TextView progressMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void blinkRed() {
        if (!red) {
            constraintLayout.setBackgroundColor(Color.RED);
            red = true;
        } else {
            constraintLayout.setBackgroundColor(Color.TRANSPARENT);
            red = false;
        }
    }

    private void wireWidgets() {
        useless = findViewById(R.id.switch_main_useless);
        self_destruct = findViewById(R.id.button_main_self_destruct);
        constraintLayout = findViewById(R.id.constraint_layout_main);
        look_busy = findViewById(R.id.button_main_look_busy);
        progress = findViewById(R.id.progressBar_main_progress);
        progressMessage = findViewById(R.id.textView_main_progressMessage);

    }

    private void setListeners() {
        useless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    new CountDownTimer(2000, 1000) {
                        @Override
                        // long l is milliseconds until finished
                        public void onTick(long l) {
                            if(!useless.isChecked()) {
                                cancel();
                            }
                        }
                        @Override
                        public void onFinish() {
                            useless.setChecked(false);
                        }
                    }.start();
                }
                if(isChecked) {
                    Toast.makeText(MainActivity.this, "SWITCH GO ON", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "SWITCH GO OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set the onclick listener for the self destruct button
        // make a 10 second countdown timer
        // display how much time is left on the countdown on the button
        // when the timer is complete, call the finish() method to close the activity
        // stretch goal: make the background blink red
        // stretch goal: make the background blink red increasingly faster as time runs out
        self_destruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 500) {
                    @Override
                    public void onTick(long l) {
                        self_destruct.setText(String.valueOf((int) l/1000));
                        blinkRed(); // fix this line
                    }
                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });

        look_busy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(VISIBLE);
                progressMessage.setVisibility(VISIBLE);
                useless.setVisibility(View.INVISIBLE);
                self_destruct.setVisibility(View.INVISIBLE);
                look_busy.setVisibility(View.INVISIBLE);
                new CountDownTimer(10000, 100) {
                    int currentProgress = 1;
                    @Override
                    public void onTick(long l) {
                        progress.setProgress(currentProgress);
                        currentProgress++;
                    }
                    @Override
                    public void onFinish() {
                        progress.setVisibility(View.INVISIBLE);
                        progressMessage.setVisibility(View.INVISIBLE);
                        useless.setVisibility(VISIBLE);
                        self_destruct.setVisibility(VISIBLE);
                        look_busy.setVisibility(VISIBLE);
                    }
                }.start();
            }
        });
    }
}

