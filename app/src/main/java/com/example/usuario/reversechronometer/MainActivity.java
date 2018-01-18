package com.example.usuario.reversechronometer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ReverseChronometer reverseChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reverseChronometer = findViewById(R.id.rvcTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reverseChronometer.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        reverseChronometer.stop();
    }
}
