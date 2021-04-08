package com.example.votingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.votingapp.RacesHolders.Pol;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunnerListAdapter extends ArrayAdapter<Pol> {
    private static final String TAG = "PersonListAdapter";
    private Context mContext;
    int mResource;

    public RunnerListAdapter(Context context, int resource, List<Pol> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String Name = getItem(position).getName();
        String politicalParty = getItem(position).getPoliticalParty();
        String imageURL = getItem(position).getImageURL();
        String description = getItem(position).getDescription();

        Pol runner = new Pol(Name, politicalParty, imageURL, description);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.RunnerImage);
        TextView runnerName = convertView.findViewById(R.id.RunnerName);
        TextView runnerParty = convertView.findViewById(R.id.party);
        TextView descrip = convertView.findViewById(R.id.description);

        Picasso.get()
                .load(imageURL)
                .resize(512,512).into(imageView);
        runnerName.setText(Name);
        runnerParty.setText(politicalParty);
        descrip.setText(description);

        return convertView;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

