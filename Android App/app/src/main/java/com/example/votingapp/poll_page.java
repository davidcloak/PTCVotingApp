package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class poll_page extends AppCompatActivity {

    //Initialize variables
    SeekBar seekBar1, seekBar2;
    TextView option1, option2;
    TextView percent1, percent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_page);

        //Assign variables
        seekBar1 = findViewById(R.id.seek_bar1);
        seekBar2 = findViewById(R.id.seek_bar2);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);

        percent1 = findViewById(R.id.percent1);
        percent2 = findViewById(R.id.percent2);

        

    }
}