package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.widget.ProgressBar;

/**
 * Created by santosh on 9/9/2015.
 */
public class AsyncTaskTests extends AndroidTestCase {

    //Test will check that the string returned by the JokeAsyncTask is not null

    public void taskTest(){

        String taskResult = null;

        JokeAsyncTask task = new JokeAsyncTask(getContext(), null );
        task.execute();

        try {
            taskResult = task.get();

        } catch (Exception e){
            e.printStackTrace();
        }
        assertNotNull(taskResult);
    }

}
