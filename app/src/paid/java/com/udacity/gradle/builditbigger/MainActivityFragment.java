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


        return root;
    }
}
