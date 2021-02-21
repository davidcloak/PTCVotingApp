package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register_page extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirmPassword;
    Button create;
    TextView backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        create = (Button)findViewById(R.id.create);
        backLogin = (TextView)findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginPage = new Intent(register_page.this, login_page.class);
            }
        });
    }
}