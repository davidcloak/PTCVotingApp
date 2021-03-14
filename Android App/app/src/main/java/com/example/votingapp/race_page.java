package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class race_page extends AppCompatActivity {

    private Button raceButton0;
    private Button raceButton1;
    private Button raceButton2;
    private Button raceButton3;
    private Button raceButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_page);

        raceButton0 = findViewById(R.id.race0Button);
        raceButton1 = findViewById(R.id.race1Button);
        raceButton2 = findViewById(R.id.race2Button);
        raceButton3 = findViewById(R.id.race3Button);
        raceButton4 = findViewById(R.id.race4Button);

        String race0 = getIntent().getStringExtra("race0");
        raceButton0.setText(race0);

        String race1 = getIntent().getStringExtra("race1");
        raceButton1.setText(race1);

        String race2 = getIntent().getStringExtra("race2");
        raceButton2.setText(race2);

        String race3 = getIntent().getStringExtra("race3");
        raceButton3.setText(race3);

        String race4 = getIntent().getStringExtra("race4");
        raceButton3.setText(race4);


        raceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPresidentPage();
            }
        });

        raceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMayorPage();
            }
        });


    }

    public void openPresidentPage(){
        Intent intent = new Intent(this, president_page.class);
        startActivity(intent);
    }

    public void openMayorPage(){
        Intent intent = new Intent(this, mayor_page.class);
        startActivity(intent);
    }

    //Open senator page

    //Open dogCatcher page


}