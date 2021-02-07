package com.example.touchtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHeadingLabel;
    private ImageView mFingerprintImage;
    private TextView mParaLabel;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeadingLabel = (TextView) findViewById(R.id.headinglable);
        mFingerprintImage = (ImageView) findViewById(R.id.FingerPrintImage);
        mParaLabel = (TextView) findViewById(R.id.paraLabel);

        // Check 1: Android version should be greater or equal to Marshmallow
        // Check 2: Device has Fingerprint Scanner
        // Check 3: Have permission to use figerprint scanner in the app
        // Check 4: Lock screen is secured with atleast 1 type of lock
        // Check 5: Atleast 1 Fingerprint is registered

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if(!fingerprintManager.isHardwareDetected()){
                mParaLabel.setText("Fingerprint Scanner not detected in device.");
            }
            else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                mParaLabel.setText("Permission not granted to use Fingerprint Scanner");
            }else if(!keyguardManager.isKeyguardSecure()){
                mParaLabel.setText("Add lock to your Phone in Settings");
            }
            else if(!fingerprintManager.hasEnrolledFingerprints()){
                mParaLabel.setText("You should add atleast 1 Fingerprint to use this Feature");
            }else{
                mParaLabel.setText("Place your Finger on Scanner to Access the App");
            }
        }else{
            mParaLabel.setText("No");
        }
    }
}