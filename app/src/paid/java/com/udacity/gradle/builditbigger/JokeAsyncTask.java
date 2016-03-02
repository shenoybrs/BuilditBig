
package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.santosh.jokelibrary.JokeActivity;
import com.example.santosh.myapplication.backend.myApi.MyApi;
import com.example.santosh.myapplication.backend.myApi.model.MyBean;
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
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

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
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra("JOKE", result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}



