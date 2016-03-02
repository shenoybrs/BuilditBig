package com.example.santosh.myapplication.backend;

import com.example.JokeTeller;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {


    private JokeTeller joke;

    public MyBean() {
        joke = new JokeTeller();
    }

    public String getJoke() {
        return joke.randomJoke();
    }

}