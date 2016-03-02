
package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.santosh.jokelibrary.JokeActivity;
import com.example.santosh.myapplication.backend.myApi.MyApi;
import com.example.santosh.myapplication.backend.myApi.model.MyBean;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


/**
 * Created by santosh on 9/3/2015.
 */

public class JokeAsyncTask extends AsyncTask<Void, Void, String>{
    private static MyApi myApiService = null;

    private Context mContext;
    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;

    public JokeAsyncTask(Context context, ProgressBar progressBar){

        this.mContext = context;
        this.mProgressBar = progressBar;
    }


    //un-hide the progressbar while we are loading the joke
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }



        try {
            return myApiService.putJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Try and hide the progessbar once the task is executed

        final String mResult = result;
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }


        //Create teh new IntertransitionalAd
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            //When the Ad is loaded hide the progress bar and show the add
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                mInterstitialAd.show();
            }


            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                Intent intent = new Intent(mContext, JokeActivity.class);
                intent.putExtra("JOKE", mResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }

            //When the ad is closed start the new joke activity
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(mContext, JokeActivity.class);
                intent.putExtra("JOKE", mResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        //Create new add request to fetch the ad
        AdRequest addRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice(mContext.getString())
                .build();
        mInterstitialAd.loadAd(addRequest);

    }
}



