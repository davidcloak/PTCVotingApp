package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class race_page extends AppCompatActivity {

    private Button presidentButton;
    private Button mayorButton;
    private Button senatorButton;
    private Button dogCatcherButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_page);

        presidentButton = findViewById(R.id.presidentButton);
        mayorButton = findViewById(R.id.mayorButton);
        senatorButton = findViewById(R.id.senatorButton);
        dogCatcherButton = findViewById(R.id.dogCatcherButton);

        presidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPresidentPage();
            }
        });

        mayorButton.setOnClickListener(new View.OnClickListener() {
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