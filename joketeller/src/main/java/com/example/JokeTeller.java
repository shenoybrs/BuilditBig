package com.example;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {
    public ArrayList<String> jokeList = new ArrayList<>();

    public JokeTeller(){
        jokeList.add("When Rajinikanth does push-ups, he isn't lifting himself up. He is pushing the earth down.");
        jokeList.add("There is no such thing as evolution, it's just a list of creatures that Rajinikanth allowed to live.");
        jokeList.add("Rajinikanth gave Mona Lisa that smile. ");
        jokeList.add("Rajnikanth can divide by zero.");
        jokeList.add("Rajinikanth can judge a book by it's cover");
        jokeList.add("Rajinikanth can drown a fish");
        jokeList.add("Rajinikanth can delete the Recycle Bin.");
    }

    public String randomJoke(){

        Random rand = new Random();
        int randomNum = rand.nextInt((jokeList.size()- 1) + 1);
        String joke = jokeList.get(randomNum);
        return joke;
    }
}
