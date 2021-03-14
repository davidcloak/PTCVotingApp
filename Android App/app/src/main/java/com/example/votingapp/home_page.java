package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class home_page extends AppCompatActivity {

    private Button button;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcome = (TextView)findViewById(R.id.welcome);
        //get from login page
        String username = getIntent().getStringExtra("keyname");
        welcome.setText("Welcome! " + username);

        button = findViewById(R.id.homeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_page.this, Information_page.class);
                intent.putExtra("keyname", username);
                startActivity(intent);
            }
        });
    }
}