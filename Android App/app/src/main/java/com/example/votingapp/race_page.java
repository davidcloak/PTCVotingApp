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

        this.setTitle("Choose The Race to Vote");

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
                apiConnect0();
            }
        });

        String race1 = getIntent().getStringExtra("race1");
        raceButton1.setText(race1);
        raceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiConnect1();
            }
        });

        String race2 = getIntent().getStringExtra("race2");
        raceButton2.setText(race2);
        raceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiConnect2();
            }
        });

        String race3 = getIntent().getStringExtra("race3");
        raceButton3.setText(race3);
        raceButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need function
            }
        });

        String race4 = getIntent().getStringExtra("race4");
        raceButton4.setText(race4);
        raceButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need function
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

    //First button function
    public void apiConnect0() {
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
                    String i1 = "";

                    String d2 = "";
                    String c2 = "";
                    String p2 = "";
                    String i2 = "";

                    String d3 = "";
                    String c3 = "";
                    String p3 = "";
                    String i3 = "";

                    try {
                        JSONObject first = response.getJSONObject(0);
                        JSONArray runners = first.getJSONArray("runners");

                        //For loop get all runners info. But I can't pull each one info.
//                        for (int i = 0; i < runners.length(); i++) {
//
//                            JSONObject runner = runners.getJSONObject(i);
//
//                            String politicalParty = runner.getString("politicalParty");
//                            String imageURL = runner.getString("imageURL");
//                            String description = runner.getString("description");
//                            String name = runner.getString("name");
//
//                            Log.e("All results: ", politicalParty + imageURL + description + name);
//                          }

                            //Party
                            JSONObject party1 = runners.getJSONObject(0);
                            p1 = party1.getString("politicalParty");
                            JSONObject party2 = runners.getJSONObject(1);
                            p2 = party2.getString("politicalParty");
                            JSONObject party3 = runners.getJSONObject(2);
                            p3 = party3.getString("politicalParty");

                            //Image
                            JSONObject image1 = runners.getJSONObject(0);
                            i1 = image1.getString("imageURL");
                            JSONObject image2 = runners.getJSONObject(1);
                            i2 = image2.getString("imageURL");
                            JSONObject image3 = runners.getJSONObject(2);
                            i3 = image3.getString("imageURL");

                            //Description
                            JSONObject description1 = runners.getJSONObject(0);
                            d1 = description1.getString("description");
                            JSONObject description2 = runners.getJSONObject(1);
                            d2 = description2.getString("description");
                            JSONObject description3 = runners.getJSONObject(2);
                            d3 = description3.getString("description");

                            //Name
                            JSONObject name1 = runners.getJSONObject(0);
                            c1 = name1.getString("name");
                            JSONObject name2 = runners.getJSONObject(1);
                            c2 = name2.getString("name");
                            JSONObject name3 = runners.getJSONObject(2);
                            c3 = name3.getString("name");


                        Intent intent = new Intent(race_page.this, vote_page.class);
                        intent.putExtra("d1", d1);
                        intent.putExtra("c1", c1);
                        intent.putExtra("i1", i1);
                        intent.putExtra("p1", p1);
                        intent.putExtra("d2", d2);
                        intent.putExtra("i2", i2);
                        intent.putExtra("c2", c2);
                        intent.putExtra("p2", p2);
                        intent.putExtra("d3", d3);
                        intent.putExtra("i3", i3);
                        intent.putExtra("c3", c3);
                        intent.putExtra("p3", p3);
                        startActivity(intent);

                    } catch (JSONException e) {
                        Log.e("Json object error: ", e.toString());
                    }

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

    //Second button function
    public void apiConnect1() {
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
                    String i1 = "";

                    String d2 = "";
                    String c2 = "";
                    String p2 = "";
                    String i2 = "";

                    String d3 = "";
                    String c3 = "";
                    String p3 = "";
                    String i3 = "";

                    String d4 = "";
                    String c4 = "";
                    String p4 = "";
                    String i4 = "";

                    try {
                        JSONObject first = response.getJSONObject(1);
                        JSONArray runners = first.getJSONArray("runners");

                        //Party
                        JSONObject party1 = runners.getJSONObject(0);
                        p1 = party1.getString("politicalParty");
                        JSONObject party2 = runners.getJSONObject(1);
                        p2 = party2.getString("politicalParty");
                        JSONObject party3 = runners.getJSONObject(2);
                        p3 = party3.getString("politicalParty");

                        //Description
                        JSONObject description1 = runners.getJSONObject(0);
                        d1 = description1.getString("description");
                        JSONObject description2 = runners.getJSONObject(1);
                        d2 = description2.getString("description");
                        JSONObject description3 = runners.getJSONObject(2);
                        d3 = description3.getString("description");

                        //Name
                        JSONObject name1 = runners.getJSONObject(0);
                        c1 = name1.getString("name");
                        JSONObject name2 = runners.getJSONObject(1);
                        c2 = name2.getString("name");
                        JSONObject name3 = runners.getJSONObject(2);
                        c3 = name3.getString("name");

                        //Image
                        JSONObject image1 = runners.getJSONObject(0);
                        i1 = image1.getString("imageURL");
                        JSONObject image2 = runners.getJSONObject(1);
                        i2 = image2.getString("imageURL");
                        JSONObject image3 = runners.getJSONObject(2);
                        i3 = image3.getString("imageURL");

                        Intent intent = new Intent(race_page.this, vote_page.class);
                        intent.putExtra("d1", d1);
                        intent.putExtra("c1", c1);
                        intent.putExtra("p1", p1);
                        intent.putExtra("i1", i1);

                        intent.putExtra("d2", d2);
                        intent.putExtra("c2", c2);
                        intent.putExtra("p2", p2);
                        intent.putExtra("i2", i2);

                        intent.putExtra("d3", d3);
                        intent.putExtra("c3", c3);
                        intent.putExtra("p3", p3);
                        intent.putExtra("i3", i3);

                        startActivity(intent);

                    } catch (JSONException e) {
                        Log.e("Json object error: ", e.toString());
                    }

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

    //Third button function
    public void apiConnect2() {
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
                    String i1 = "";

                    String d2 = "";
                    String c2 = "";
                    String p2 = "";
                    String i2 = "";

                    String d3 = "";
                    String c3 = "";
                    String p3 = "";
                    String d4 = "";
                    String c4 = "";
                    String p4 = "";

                    try {
                        JSONObject first = response.getJSONObject(2);
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

                        //Image
                        JSONObject image1 = runners.getJSONObject(0);
                        i1 = image1.getString("imageURL");
                        JSONObject image2 = runners.getJSONObject(1);
                        i2 = image2.getString("imageURL");

                        Intent intent = new Intent(race_page.this, vote_page.class);
                        intent.putExtra("d1", d1);
                        intent.putExtra("c1", c1);
                        intent.putExtra("p1", p1);
                        intent.putExtra("i1", i1);

                        intent.putExtra("d2", d2);
                        intent.putExtra("c2", c2);
                        intent.putExtra("p2", p2);
                        intent.putExtra("i2", i2);

                        startActivity(intent);

                    } catch (JSONException e) {
                        Log.e("Json object error: ", e.toString());
                    }

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

}