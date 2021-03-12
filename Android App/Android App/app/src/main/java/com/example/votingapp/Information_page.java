package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Information_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button button;
    private TextView name, id;

    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        button = findViewById(R.id.informationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRacePage();
            }
        });

        name = findViewById(R.id.editName);
        id = findViewById(R.id.editVoterID);

        //from Login page, use user name to put here. Will use it as a key to connect to database
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);

        //State spinner
        Spinner spinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //open race page function
    public void openRacePage(){
        Intent intent = new Intent(this, race_page.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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


    //Class for get userID
    public class getInfo extends AsyncTask<String, String, String> {

        String message = "";
        Boolean isSuccess = false;
        //id
        String voterID = id.toString();

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

            con = connectionClass(ConnectionClass.user.toString(),ConnectionClass.pass.toString(),ConnectionClass.database.toString(),ConnectionClass.server.toString());
            if(con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Information_page.this, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });
                message = "Connecting";
            }
            else {
                try {
                    String sql = "Select Id from AspNetUsers where UserName = '" + name.getText() + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                voterID = rs.toString();
                                id.setText(voterID);
                            }
                        });
                        message = "Success";
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Information_page.this, "Connect to database failed", Toast.LENGTH_LONG).show();
                            }
                        });
                        message = "Connect Failed";
                    }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error: ", e.getMessage());
                }
            }
            return message;
        }
    }

}