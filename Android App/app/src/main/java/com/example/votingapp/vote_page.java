package com.example.votingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.votingapp.RacesHolders.Pol;
import com.example.votingapp.RacesHolders.Races;
import com.example.votingapp.RacesHolders.RacesHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class vote_page extends AppCompatActivity {

    //static public RacesHolder races = new RacesHolder();
    static public ArrayList<Races> raceList = new ArrayList<Races>();
    ListView listView;
    int p;
    int position;
    String dID;
    TextView PartyTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);
        this.setTitle("Vote");

        //clears in case of double call
        raceList = new ArrayList<Races>();

        //races = race_page.races;
        raceList = race_page.raceList;
        listView = findViewById(R.id.listView);

        getUserID(Information_page.username);

        //BuildRunners();
        position = getIntent().getIntExtra("pos", 0);
        Log.e("raceList", "test" + raceList.get(position).getRunners().toString());

        PartyTitle = findViewById(R.id.RaceName);
        PartyTitle.setText(raceList.get(position).getRace());

        raceList.get(position).shuffle();

        RunnerListAdapter adapter = new RunnerListAdapter(this, R.layout.runner_adapter_view, (List<Pol>) raceList.get(position).getRunners());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(vote_page.this, i+"~ Voted for: "+raceList.get(position).getRunners().get(i).getName(), Toast.LENGTH_SHORT).show();
                p = i;
                openDialog();
            }
        });
    }

    //If click the card, then confirm windows shows up
    private void openDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(vote_page.this);
        //Show confirmation alert
        builder.setMessage(String.format("You want to vote for %s correct?",raceList.get(position).getRunners().get(p).getName()));
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            //Click Yes show poll page results
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(vote_page.this, dID+"~ Voted for: "+raceList.get(position).getRunners().get(p).getName(), Toast.LENGTH_SHORT).show();
                apiConnect(raceList.get(position).getRunners().get(p).getName(), dID, raceList.get(position).getRace(), raceList.get(position).getState(), raceList.get(position).getCity());
                Log.e("test",raceList.get(position).getRace()+" "+ raceList.get(position).getState()+" "+ raceList.get(position).getCity());
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            //Click cancel then return back to the vote
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }


    //Builds the Auth header for the API
    public static String buildAuth(String username, String password) {
        String temp = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encoded);
        return authHeader;
    }
    //USerID
    private void getUserID(String email){
        StringRequest request = new StringRequest(Request.Method.POST, "https://ptcvotingapi.azurewebsites.net/getUser", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    response = response.replace("[","");
                    response = response.replace("]","");

                    try {
                        //reads what was gathured
                        ObjectMapper mapper = new ObjectMapper();
                        Log.e("response:", response);
                        User temp = mapper.readValue(response, User.class);
                        dID = temp.getId()+"";
                    } catch (Exception ex) {
                        Log.e("Error", ex.toString());
                    }
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
                params.put("email", email);
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



    //votes
    public void apiConnect(String pol, String userID, String race, String state, String city) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://ptcvotingapi.azurewebsites.net/vote", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    //response

                    Toast.makeText(vote_page.this, response, Toast.LENGTH_SHORT).show();
                    Log.e("response", response);

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
                params.put("pol", pol);
                params.put("userID", userID);
                params.put("race", race);
                params.put("state", state);
                params.put("city", city);
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
}