package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.Connection.ConnectionClass;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login_page extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    EditText username;
    EditText password;
    CheckBox captchaBox;
    Button login;
    TextView register, status;
    LinearLayout ly;

    GoogleApiClient googleApiClient;
    Connection con;
    String siteKey = "6Lez-GcaAAAAAJqDyB-rhEN23w_Uh90yAfvASwmA";

    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        captchaBox = (CheckBox)findViewById(R.id.captchaBox);
        ly = findViewById(R.id.ly);
        status = (TextView)findViewById(R.id.status);

        //Google Api
        googleApiClient = new GoogleApiClient.Builder(this).addApi(SafetyNet.API).addConnectionCallbacks(login_page.this).build();
        googleApiClient.connect();

        //Captcha Checkbox
        captchaBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(captchaBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,siteKey).setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        @Override
                        public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                            Status status = recaptchaTokenResult.getStatus();
                            if((status != null) && status.isSuccess()){
                                //Successful
                                Toast.makeText(getApplicationContext(),"Successful!",Toast.LENGTH_LONG).show();
                                login.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }else {

                }
            }
        });

        //Register page
        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });

        //Login
        login = (Button)findViewById(R.id.login);
        //login.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new login_page.loginUser().execute("");
            }
        });
    }

    //Function of snackbar
    public void ShowSnackBar(String message) {
        Snackbar.make(ly, message, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    //Go to register page
    public void openRegisterPage(){
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }

    //User name put to information username field function
    public void putInformationToHomePage(){
        String name = username.getText().toString();
        Intent intent = new Intent(this, home_page.class);
        intent.putExtra("keyname", name);
        startActivity(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    //Class for login
    public class loginUser extends AsyncTask<String, String, String>{

        String message = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            con = connectionClass(ConnectionClass.user,ConnectionClass.pass,ConnectionClass.database,ConnectionClass.server);
            if(con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(login_page.this, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });
                message = "Connecting";
            }
            else {
                try {
                    String sql = "Select * from AspNetUsers where UserName = '" + username.getText() + "' and passwordhash = '" + password.getText() + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(login_page.this, "Login Success", Toast.LENGTH_LONG).show();
                            }
                        });
                        message = "Success";
                        Intent intent = new Intent(login_page.this, home_page.class);
                        startActivity(intent);
                        putInformationToHomePage();
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(login_page.this, "Login failed! Check your email and password", Toast.LENGTH_LONG).show();
                                counter--;
                                status.setText("Number of attempts remaining: " + String.valueOf(counter));
                                if(counter == 0){
                                    login.setEnabled(false);
                                }
                            }
                        });
                        message = "Login Failed";
                    }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error: ", e.getMessage());
                }
            }
            return message;
        }
    }


    //SQL
    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String pass, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String url = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            url = "jdbc:jtds:sqlserver://" + server + "/" + database + ";user=" + user + ";password=" + pass + ";";
            connection = DriverManager.getConnection(url);
        }catch(Exception e){
            Log.e("Error: ", e.getMessage());
        }
        return connection;
    }
}
