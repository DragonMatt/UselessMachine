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
        self_destruct.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }
}

