package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtMsg = findViewById(R.id.textMessage);
        Button loginAuth = findViewById(R.id.touchButton);

        BiometricManager bm = BiometricManager.from(this);
        //Check user if has bio sensor ready to auth
        switch (bm.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                txtMsg.setText("Bio sensor detected");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                txtMsg.setText("Bio sensor not detected");
                loginAuth.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                txtMsg.setText("Bio sensor unavailable");
                loginAuth.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                txtMsg.setText("You don't have any fingerprint, go to create one!");
                loginAuth.setVisibility(View.GONE);
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        //Check if auth success or not
        BiometricPrompt bp = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            //Auth error
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            //Auth success and go to login page
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Auth Success!", Toast.LENGTH_SHORT).show();
                openLoginPage();
            }
            //Auth fail
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Auth Failed, try again!", Toast.LENGTH_SHORT).show();
            }
        });

        //Bio sensor check screen
        BiometricPrompt.PromptInfo pi = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login")
                .setNegativeButtonText("Cancel")
                .build();

        //touch button onClick event
        loginAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.authenticate(pi);
            }
        });


    }

    //open login page
    public void openLoginPage(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }


}