package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.votingapp.Connection.ConnectionClass;
import com.example.votingapp.RacesHolders.RacesHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.internal.Constants;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class Information_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button button;
    private TextView name;

    //Variable for the Races
    RacesHolder races = new RacesHolder();


    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        button = findViewById(R.id.informationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiConnect();
            }
        });

        name = findViewById(R.id.editName);
        //from home page, use user name to put here. Will use it as a key to connect to database
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);

        //State spinner
        Spinner spinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //Builds the Auth header for the API
    public static String buildAuth(String username, String password){
        String temp = username+":"+password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic "+ new String(encoded);
        return authHeader;
    }

    //converts JSON to Races
    public void JSONtoClassRace(String Json){
        try{
            //reads what was gathured
            ObjectMapper mapper = new ObjectMapper();
            races = mapper.readValue(Json, RacesHolder.class);
        }catch(Exception ex){
            Log.e("Error", ex.toString());
        }
    }

    public void apiConnect() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://ptcvotingapi.azurewebsites.net/getRaces", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    response = "{ \"races\": " + response + "}";
                    Log.e("Your Array Response", response);
                    JSONtoClassRace(response);
                    Log.e("called converter", races.getRaces().get(0).getRace());
                } else {
                    Log.e("Your Array Response", "Data Null");
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Log.e("getHeaders", "Called :)");
                String auth = buildAuth("ShhhImASecret", "ShhhImABiggerSecret123@");
                HashMap<String, String> headers = new HashMap<>();
                headers.put(HttpHeaders.AUTHORIZATION, auth);
                return headers;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //No Parameters for this one..
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
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