package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

//SQL imports
import com.example.votingapp.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class register_page extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirmPassword;
    Button create;
    TextView status;
    TextView backLogin;
    LinearLayout ly;

    Connection con;
    Statement stmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        status = (TextView)findViewById(R.id.status);
        ly = findViewById(R.id.ly);

        //Register user
        create = (Button)findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if user enter all fields
                if (isEmpty(username.getText().toString()) ||
                        isEmpty(password.getText().toString()) ||
                        isEmpty(confirmPassword.getText().toString()))
                    ShowSnackBar("Please enter all fields");
                //Check if user password match
                else if (!password.getText().toString().equals(confirmPassword.getText().toString()))
                    ShowSnackBar("Password does not match");
                //Pass above then create account
                else {
                    new register_page.registerUser().execute("");
                }
            }
        });

        //Back to Login page
        backLogin = (TextView)findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
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

    //Function of go back to login page
    public void openLoginPage(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }

    //Function check if empty
    public Boolean isEmpty(String strValue) {
        if (strValue == null || strValue.trim().equals(("")))
            return true;
        else
            return false;
    }

    //SQL connection
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


    //Class of register user
    public class registerUser extends AsyncTask<String, String, String>{

        String message = "";
        boolean isSuccess = false;

        //random id
        UUID uuid = UUID.randomUUID();

        @Override
        protected void onPreExecute() {
            status.setText("Sending data");
        }

        @Override
        protected void onPostExecute(String s) {
            ShowSnackBar(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                con = connectionClass(ConnectionClass.user.toString(),ConnectionClass.pass.toString(),ConnectionClass.database.toString(),ConnectionClass.server.toString());
                if(con == null){
                    message = "Connection Error";
                }
                else {                                                                                                                                                                                                                                                                  //id                        //username                  //NormalizedUserName                                    //Email                //EmailConfirmed     //Password            //Phone  //TwoFactor   //Lockout   //AccessFailed
                    String sql = "Insert into AspNetUsers (Id,UserName,NormalizedUserName,Email,NormalizedEmail,EmailConfirmed,PasswordHash,SecurityStamp,ConcurrencyStamp,PhoneNumber,PhoneNumberConfirmed,TwoFactorEnabled,LockoutEnabled,AccessFailedCount) values ('" + uuid.toString() + "','" + username.getText() + "','" + username.getText().toString().toUpperCase() + "','" + username.getText() + "','" + username.getText().toString().toUpperCase() + "','" + 0 + "','" + password.getText() + "','" + null + "','" + null + "','" + null + "','" + 0 + "','" + 0 + "','" + 1 + "','" + 0 + "')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }
            } catch (Exception e) {
                isSuccess = false;
                message = e.getMessage();
            }
            return message;
        }
    }
}

