package com.example.votingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {

    FingerprintManager fingerprintManager;
    KeyguardManager keyguardManager;
    EditText Email;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public String APICall(String email){
        try{
            //String urlString = "http://localhost:8080/?email="+email;

            //URL url=new URL(urlString);
            URL url = new URL("http://jservice.io/api/random");

            //Connect
            URLConnection sock =  url.openConnection();

            //parses through
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String nextRecord = null;
            String JSONString = "";
            while((nextRecord = reader.readLine()) != null){
                JSONString += nextRecord;
            }
            reader.close();

            //removes unwanted []
            JSONString = JSONString.replace("[", "");
            JSONString = JSONString.replace("]", "");
            return JSONString;
        }catch(MalformedURLException e){return e.toString();}
        catch(IOException e){return e.toString();}
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void checks(View view){
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if(!fingerprintManager.isHardwareDetected()){
                Toast.makeText(Login.this, "Will have to call another why to authorize.", Toast.LENGTH_LONG).show();
                //mParaLabel.setText("Fingerprint Scanner not detected in device.");
            }
            else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Login.this, "Permission not granted to use Fingerprint Scanner.", Toast.LENGTH_LONG).show();
                //mParaLabel.setText("Permission not granted to use Fingerprint Scanner");
            }else if(!keyguardManager.isKeyguardSecure()){
                Toast.makeText(Login.this, "Need a lock on your phone.", Toast.LENGTH_LONG).show();
                //mParaLabel.setText("Add lock to your Phone in Settings");
            }
            else if(!fingerprintManager.hasEnrolledFingerprints()){
                Toast.makeText(Login.this, "Need 1 Fingerprint registered.", Toast.LENGTH_LONG).show();
                //mParaLabel.setText("You should add atleast 1 Fingerprint to use this Feature");
            }else{
                //mParaLabel.setText("Place your Finger on Scanner to Access the App");
                BioID(view);
            }
        }else{
            //mParaLabel.setText("No");
            Toast.makeText(Login.this, "Will have to call another why to authorize.", Toast.LENGTH_LONG).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void BioID(View view) {
        Executor executor = Executors.newSingleThreadExecutor();
        Login activity = this;
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this).setTitle("Fingerprint Authentication").setSubtitle("Complete to login.").setDescription("Confirms you are a person.").setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this, "Authentication Cancelled ", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Login.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String JSON = APICall(Email.getText().toString());
                        Toast.makeText(Login.this, "Fingure Authentication Succeeded: "+JSON, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onAuthenticationFailed() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this,"Authentication Cancelled ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}