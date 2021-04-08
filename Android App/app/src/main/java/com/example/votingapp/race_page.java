package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.votingapp.RacesHolders.Races;
import com.example.votingapp.RacesHolders.RacesHolder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class race_page extends AppCompatActivity {

    static public ListView listView;
    static public RacesHolder races = new RacesHolder();
    static public ArrayList<Races> raceList = new ArrayList<Races>();

    static public String state;
    static public String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_page);

        this.setTitle("Choose The Race to Vote");
        //clears in case of double call
        raceList = new ArrayList<Races>();

        races = Information_page.races;
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");

        listView = (ListView) findViewById(R.id.Poles);

        ArrayList<String> arrayList = new ArrayList<>();
        //arrayList = BuildRunners2(arrayList);
        BuildRunners();
        for(int i = 0; i < raceList.size(); i++){
            arrayList.add(raceList.get(i).getRace());
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(race_page.this, position+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(race_page.this, vote_page.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    public void BuildRunners(){
        ArrayList<Races> race = (ArrayList<Races>) races.getRaces();

        //adds only needed poles
        for(int i = 0; i < races.getRaces().size(); i++){
            if(race.get(i).getState().equalsIgnoreCase("National") && race.get(i).getCity().equalsIgnoreCase("NoState")){
                raceList.add(races.getRaces().get(i));
            }else if(race.get(i).getState().equalsIgnoreCase(state) && race.get(i).getCity().equalsIgnoreCase("StateWide")){
                raceList.add(races.getRaces().get(i));
            }else if(race.get(i).getState().equalsIgnoreCase(state) && race.get(i).getCity().equalsIgnoreCase(city)){
                raceList.add(races.getRaces().get(i));
            }
        }
    }

    public ArrayList<String> BuildRunners2(ArrayList<String> list){
        ArrayList<Races> race = (ArrayList<Races>) races.getRaces();

        //adds only needed poles
        for(int i = 0; i < races.getRaces().size(); i++){
            if(race.get(i).getState().equalsIgnoreCase("National") && race.get(i).getCity().equalsIgnoreCase("NoState")){
                list.add(races.getRaces().get(i).getRace());
            }else if(race.get(i).getState().equalsIgnoreCase(state) && race.get(i).getCity().equalsIgnoreCase("StateWide")){
                list.add(races.getRaces().get(i).getRace());
            }else if(race.get(i).getState().equalsIgnoreCase(state) && race.get(i).getCity().equalsIgnoreCase(city)){
                list.add(races.getRaces().get(i).getRace());
            }
        }
        return list;
    }

    //Builds the Auth header for the API
    public static String buildAuth(String username, String password) {
        String temp = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encoded);
        return authHeader;
    }

}