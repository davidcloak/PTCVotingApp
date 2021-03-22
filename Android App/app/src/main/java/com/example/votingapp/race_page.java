package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import com.example.votingapp.RacesHolders.Pol;
import com.example.votingapp.RacesHolders.Races;
import com.example.votingapp.RacesHolders.RacesHolder;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class race_page extends AppCompatActivity {

    private Button raceButton0;
    private Button raceButton1;
    private Button raceButton2;
    private Button raceButton3;
    private Button raceButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_page);

        raceButton0 = findViewById(R.id.race0Button);
        raceButton1 = findViewById(R.id.race1Button);
        raceButton2 = findViewById(R.id.race2Button);
        raceButton3 = findViewById(R.id.race3Button);
        raceButton4 = findViewById(R.id.race4Button);

        String race0 = getIntent().getStringExtra("race0");
        raceButton0.setText(race0);
        raceButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiConnect();
            }
        });

        String race1 = getIntent().getStringExtra("race1");
        raceButton1.setText(race1);
        raceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(race_page.this, vote_page.class);
                startActivity(intent);
            }
        });

        String race2 = getIntent().getStringExtra("race2");
        raceButton2.setText(race2);
        raceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(race_page.this, president_page.class);
                startActivity(intent);
            }
        });

        String race3 = getIntent().getStringExtra("race3");
        raceButton3.setText(race3);
        raceButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(race_page.this, vote_page.class);
                startActivity(intent);
            }
        });

        String race4 = getIntent().getStringExtra("race4");
        raceButton4.setText(race4);
        raceButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(race_page.this, vote_page.class);
                startActivity(intent);
            }
        });
    }

    //Builds the Auth header for the API
    public static String buildAuth(String username, String password) {
        String temp = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encoded);
        return authHeader;
    }

    public void apiConnect() {
        RequestQueue queue = Volley.newRequestQueue(race_page.this);
        String url = "https://ptcvotingapi.azurewebsites.net/getRaces";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (!response.equals(null)) {

                    //Variables for holders
                    String d1 = "";
                    String c1 = "";
                    String p1 = "";
                    String d2 = "";
                    String c2 = "";
                    String p2 = "";

                    try {
                        JSONObject first = response.getJSONObject(0);
                        JSONArray runners = first.getJSONArray("runners");

                        //Party
                        JSONObject party1 = runners.getJSONObject(0);
                        p1 = party1.getString("politicalParty");
                        JSONObject party2 = runners.getJSONObject(1);
                        p2 = party2.getString("politicalParty");

                        //Description
                        JSONObject description1 = runners.getJSONObject(0);
                        d1 = description1.getString("description");
                        JSONObject description2 = runners.getJSONObject(1);
                        d2 = description2.getString("description");

                        //Name
                        JSONObject name1 = runners.getJSONObject(0);
                        c1 = name1.getString("name");
                        JSONObject name2 = runners.getJSONObject(1);
                        c2 = name2.getString("name");

                    } catch (JSONException e) {
                        Log.e("Json object error: ", e.toString());
                    }
                    Intent intent = new Intent(race_page.this, vote_page.class);
                    intent.putExtra("d1", d1);
                    intent.putExtra("c1", c1);
                    intent.putExtra("p1", p1);
                    intent.putExtra("d2", d2);
                    intent.putExtra("c2", c2);
                    intent.putExtra("p2", p2);
                    startActivity(intent);

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
        queue.add(request);
    }
//    public void apiConnect() {
//        StringRequest request = new StringRequest(Request.Method.GET, "https://ptcvotingapi.azurewebsites.net/getRaces", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (!response.equals(null)) {
//                    response = "{ \"runners\": " + response + "}";
//                    Log.e("Your Array Response", response);
//                    JSONtoClassRace(response);
//
//                    //Get race from API
//                    String politicalParty1 = polInfo.getPoliticalParty();
//                    String imageURL1 = polInfo.getImageURL();
//                    String description1 = polInfo.getDescription();
//                    String name1 = polInfo.getName();
//
//                    String politicalParty2 = polInfo.getPoliticalParty();
//                    String imageURL2 = polInfo.getImageURL();
//                    String description2 = polInfo.getDescription();
//                    String name2 = polInfo.getName();
//
//                    //Open race page and put race name in the button
//                    Intent intent = new Intent(race_page.this, vote_page.class);
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
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        queue.add(request);
//    }
}