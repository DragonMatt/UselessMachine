package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch useless;
    private Button self_destruct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void wireWidgets() {
        useless = findViewById(R.id.switch_main_useless);
        self_destruct = findViewById(R.id.button_main_self_destruct);
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
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {
                        self_destruct.setText(String.valueOf((int) l/1000));
                    }
                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });
    }
}

