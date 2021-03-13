package com.example.votingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class president_page extends AppCompatActivity{

    ImageButton imageButton1;
    ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_page);

        imageButton1 = (ImageButton)findViewById(R.id.president1Img);
        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });

        imageButton2 = (ImageButton)findViewById(R.id.president2Img);
        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });
    }

    public void openDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(president_page.this);
        //Show confirmation alert
        builder.setMessage("Please confirm your choice!");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            //Click Yes show poll page results
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(president_page.this, poll_page.class);
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