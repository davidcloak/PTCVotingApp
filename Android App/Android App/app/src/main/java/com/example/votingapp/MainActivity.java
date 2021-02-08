package com.example.votingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
        Toast.makeText(MainActivity.this, "method called :)", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void BioID(View view) {
        Executor executor = Executors.newSingleThreadExecutor();
        MainActivity activity = this;
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this).setTitle("Fingerprint Authentication").setSubtitle("Complete to login.").setDescription("You must confirm your identity with your thumb print.").setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Authentication Cancelled ", Toast.LENGTH_LONG).show();
//                            if(captchaBox.isChecked()){
//                                captchaBox.setChecked(false);
//                            }
                    }
                });
            }
        }).build();


        biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
//                            if(captchaBox.isChecked()){
//                                captchaBox.setChecked(false);
//                            }
                        }
                    });
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
//                            if(captchaBox.isChecked()){
//                                captchaBox.setChecked(false);
//                            }
                        }
                    });
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Authentication Succeeded", Toast.LENGTH_LONG).show();
//                            if(!captchaBox.isChecked()){
//                                captchaBox.setChecked(true);
//                            }
                    }
                });
            }

            @Override
            public void onAuthenticationFailed() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
//                            if(captchaBox.isChecked()){
//                                captchaBox.setChecked(false);
//                            }
                        }
                    });
            }
        });
    }

}