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

        apiConnect();
    }

    //Builds the Auth header for the API
    public static String buildAuth(String username, String password) {
        String temp = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encoded);
        return authHeader;
    }

    //New version used JsonArray Request
//    public void apiConnect() {
//        RequestQueue queue = Volley.newRequestQueue(Information_page.this);
//        String url = "https://ptcvotingapi.azurewebsites.net/getRaces";
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (!response.equals(null)) {
//
//                    //Variables for holders
//                    String race0 = "";
//                    String race1 = "";
//                    String race2 = "";
//                    String race3 = "";
//                    String race4 = "";
//
//                    //Get race from API
//                    try {
//                        //first race
//                        JSONObject first = response.getJSONObject(0);
//                        race0 = first.getString("race");
//
//                        //first race
//                        JSONObject second = response.getJSONObject(1);
//                        race1 = second.getString("race");
//
//                        //first race
//                        JSONObject third = response.getJSONObject(2);
//                        race2 = third.getString("race");
//
//                        //first race
//                        JSONObject forth = response.getJSONObject(3);
//                        race3 = forth.getString("race");
//
//                        //first race
//                        JSONObject fifth = response.getJSONObject(4);
//                        race4 = fifth.getString("race");
//
//                    } catch (JSONException e) {
//                        Log.e("Json object error: ", e.toString());
//                    }
//
//                    //Open race page and put race name in the button
//                    Intent intent = new Intent(Information_page.this, race_page.class);
//                    intent.putExtra("race0", race0);
//                    intent.putExtra("race1", race1);
//                    intent.putExtra("race2", race2);
//                    intent.putExtra("race3", race3);
//                    intent.putExtra("race4", race4);
//                    startActivity(intent);
//
//                    //Log.e("called converter", races.getRaces().get(0).getRace());
//                } else {
//                    Log.e("Your Array Response", "Data Null");
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("error is ", "" + error);
//            }
//        }) {
//
//            //This is for Headers If You Needed
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Log.e("getHeaders", "Called :)");
//                String auth = buildAuth("ShhhImASecret", "ShhhImABiggerSecret123@");
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put(HttpHeaders.AUTHORIZATION, auth);
//                return headers;
//            }
//
//            //Pass Your Parameters here
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                //No Parameters for this one..
//                return params;
//            }
//        };
//        request.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(request);
//    }


    //converts JSON to Races
    public void JSONtoClassRace(String Json) {
        try {
            //reads what was gathured
            ObjectMapper mapper = new ObjectMapper();
            races = mapper.readValue(Json, RacesHolder.class);
        } catch (Exception ex) {
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

                    //Get race from API
                    String race0 = races.getRaces().get(0).getRace();
                    String race1 = races.getRaces().get(1).getRace();
                    String race2 = races.getRaces().get(2).getRace();
                    String race3 = races.getRaces().get(3).getRace();
                    String race4 = races.getRaces().get(4).getRace();

                    //Open race page and put race name in the button
                    Intent intent = new Intent(Information_page.this, race_page.class);
                    intent.putExtra("race0", race0);
                    intent.putExtra("race1", race1);
                    intent.putExtra("race2", race2);
                    intent.putExtra("race3", race3);
                    intent.putExtra("race4", race4);
                    startActivity(intent);

                    //Log.e("called converter", races.getRaces().get(0).getRace());
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
}


