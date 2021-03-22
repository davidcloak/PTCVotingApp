package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeoutException;

public class vote_page extends AppCompatActivity {

    TextView description1, candidates1, party1, description2, candidates2, party2;
    ImageView image1, image2;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);

        description1.findViewById(R.id.description1);
        String d1 = getIntent().getStringExtra("d1");
        description1.setText(d1);

        candidates1.findViewById(R.id.candidates1);
        String c1 = getIntent().getStringExtra("c1");
        description1.setText(c1);

        party1.findViewById(R.id.party1);
        String p1 = getIntent().getStringExtra("p1");
        description1.setText(p1);

        description2.findViewById(R.id.description2);
        String d2 = getIntent().getStringExtra("d2");
        description1.setText(d2);

        candidates2.findViewById(R.id.candidates2);
        String c2 = getIntent().getStringExtra("c2");
        description1.setText(c2);

        party2.findViewById(R.id.party2);
        String p2 = getIntent().getStringExtra("p2");
        description1.setText(p2);


        //Need fix image later
        image1.findViewById(R.id.image1);
        image2.findViewById(R.id.image2);

        //Need submit vote later
        submit.findViewById(R.id.voteSubmit);

    }
}