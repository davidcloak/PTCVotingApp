package com.example.votingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeoutException;

public class vote_page extends AppCompatActivity {

    ImageView image0, image1, image2, image3;
    TextView name0, name1, name2, name3;
    TextView party0, party1, party2, party3;
    TextView des0, des1, des2, des3;
    CardView card1, card2, card3, card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);

        this.setTitle("Vote");

        //First runner info
        name0 = findViewById(R.id.name0);
        String c1 = getIntent().getStringExtra("c1");
        name0.setText(c1);

        party0 = findViewById(R.id.party0);
        String p1 = getIntent().getStringExtra("p1");
        party0.setText(p1);

        des0 = findViewById(R.id.des0);
        String d1 = getIntent().getStringExtra("d1");
        des0.setText(d1);
        card1 = findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });

        //Second runner info
        name1 = findViewById(R.id.name1);
        String c2 = getIntent().getStringExtra("c2");
        name1.setText(c2);

        party1 = findViewById(R.id.party1);
        String p2 = getIntent().getStringExtra("p2");
        party1.setText(p2);

        des1 = findViewById(R.id.des1);
        String d2 = getIntent().getStringExtra("d2");
        des1.setText(d2);

        card2 = findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });
        //If don't have this user info then card inVisibility
        if(c2 == null || p2 == null || d2 == null){
            card2.setVisibility(View.GONE);
        }

        //Third runner info
        name2 = findViewById(R.id.name2);
        String c3 = getIntent().getStringExtra("c3");
        name2.setText(c3);

        party2 = findViewById(R.id.party2);
        String p3 = getIntent().getStringExtra("p3");
        party2.setText(p3);

        des2 = findViewById(R.id.des2);
        String d3 = getIntent().getStringExtra("d3");
        des2.setText(d3);

        card3 = findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });
        //If don't have this user info then card inVisibility
        if(c3 == null || p3 == null || d3 == null){
            card3.setVisibility(View.GONE);
        }

        //Fourth runner info
        name3 = findViewById(R.id.name3);
        String c4 = getIntent().getStringExtra("c4");
        name3.setText(c4);

        party3 = findViewById(R.id.party3);
        String p4 = getIntent().getStringExtra("p4");
        party3.setText(p4);

        des3 = findViewById(R.id.des3);
        String d4 = getIntent().getStringExtra("d4");
        des3.setText(d4);

        card4 = findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });
        //If don't have this user info then card inVisibility
        if(c4 == null || p4 == null || d4 == null){
            card4.setVisibility(View.GONE);
        }


        image0 = findViewById(R.id.image0);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        //URL from API
        String i1 = getIntent().getStringExtra("i1");
        String i2 = getIntent().getStringExtra("i2");
        String i3 = getIntent().getStringExtra("i3");
        String i4 = getIntent().getStringExtra("i4");

        //Load image
        Picasso.get()
                .load(i1)
                //.resize(350, 350)
                .into(image0);

        Picasso.get()
                .load(i2)
                //.resize(350, 350)
                .into(image1);

        Picasso.get()
                .load(i3)
                //.resize(350, 350)
                .into(image2);

        Picasso.get()
                .load(i4)
                //.resize(350, 350)
                .into(image3);

    }

    //If click the card, then confirm windows shows up
    public void openDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(vote_page.this);
        //Show confirmation alert
        builder.setMessage("Please confirm your choice!");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            //Click Yes show poll page results
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(vote_page.this, poll_page.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            //Click cancel then return back to the vote
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}