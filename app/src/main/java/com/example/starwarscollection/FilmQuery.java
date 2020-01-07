package com.example.starwarscollection;

import java.util.ArrayList;

//This class is meant to be the query. The URL I received from email is to be taken in as this and parsed from Json into something usable.

public class FilmQuery {

    private int count;

    //Idk what these are
    private Object next;
    private Object previous;

    //This is the important part I want.
    private ArrayList<Film> results = null;

    public FilmQuery(int count, Object next, Object previous, ArrayList<Film> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    //This will have all our films. Note this is an Array
    public ArrayList<Film> getResults() {
        return results;
    }
}
