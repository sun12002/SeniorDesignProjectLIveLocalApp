package com.example.sufian.livelocal;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DiscoverFragment extends Fragment {
    private List<String> menu;
    TextView STTtxtView;
    private String token;
    private String topten;
    private TextView seasonTopTenTitle;
    private TextView seasonTopTen;
    private TextView event;
    private TextView eventTitle;
    private TextView trails;
    private TextView trailsTitle;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        seasonTopTenTitle = (TextView) rootView.findViewById((R.id.textViewSeasonTopTenTitle));
        seasonTopTen = (TextView) rootView.findViewById((R.id.textViewSeasonTopTen));
        seasonTopTen.setOnClickListener(seasonTopTenButtonClickListener);

        eventTitle = (TextView) rootView.findViewById((R.id.textViewEventTitle));
        event = (TextView) rootView.findViewById((R.id.textViewEvent));
        event.setOnClickListener(eventButtonClickListener);
        eventTitle.setText(Html.fromHtml("<font color='#e64a19'>Events</font>"));
        event.setText(Html.fromHtml("<font color='#795548'>Upcoming Events</font>"));

        trailsTitle = (TextView) rootView.findViewById((R.id.textViewTrailsTitle));
        trails = (TextView) rootView.findViewById((R.id.textViewTrails));
        trails.setOnClickListener(trailsButtonClickListener);
        trailsTitle.setText(Html.fromHtml("<font color='#e64a19'>Trails</font>"));
        String trailsStr = "Connecticut is brimming with amazing places to experience local food and agriculture. For all of you who seek adventure, here is a compiled list of trails based on Connecticut's diverse and exciting food culture and agricultural history.";
        trails.setText(Html.fromHtml("<font color='#795548'>" + trailsStr + "</font>"));

        HashMap map = WebAPICommunication.getseasonTopTenIntroHashMap();
        String temp = Html.fromHtml(map.get("seasonsTop10Intro").toString()).toString();
        topten = "\n" + temp;
        seasonTopTenTitle.setText(Html.fromHtml("<font color='#e64a19'>Season's Top Ten</font>"));
        seasonTopTen.setText(Html.fromHtml("<font color='#795548'>" + topten + "</font>"));


        return rootView;
    }

    private View.OnClickListener seasonTopTenButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), SeasonsTop10.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener eventButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), EventActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener trailsButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), TrailsActivity.class);
            startActivity(intent);
        }
    };

}
