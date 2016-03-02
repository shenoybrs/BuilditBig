package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.JokeTeller;
import com.example.santosh.jokelibrary.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Button mJokebutton;
    private ProgressBar mProgressbar;
    private final JokeTeller jokeTeller = new JokeTeller();
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mJokebutton = (Button)root.findViewById(R.id.jokeButton);
        mProgressbar = (ProgressBar)root.findViewById(R.id.progressBar);
        mProgressbar.setVisibility(View.INVISIBLE);

        mJokebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String joke = jokeTeller.randomJoke();

                Toast.makeText(getActivity(), joke, Toast.LENGTH_SHORT).show();

                new JokeAsyncTask(getActivity().getApplicationContext(), mProgressbar).execute();

            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }
}
