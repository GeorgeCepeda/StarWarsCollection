package com.example.starwarscollection;

import java.util.ArrayList;


/*This class encompasses a film object. The URL I received was a query for all the films meaning it is an object
* that has a list of films in it and not the list of films. FilmQuery should fix the confusion.*/
public class Film {

    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;

    //These need to be an array. Also Note they are just the URL's
    private ArrayList<String> characters;
    private ArrayList<String> planets;
    private ArrayList<String> starships;
    private ArrayList<String> vehicles;
    private ArrayList<String> species;

    private String created;
    private String edited;
    private String url;


    public Film(String title, int episode_id, String opening_crawl, String director, String producer, String release_date, ArrayList<String> characters, ArrayList<String> planets, ArrayList<String> starships, ArrayList<String> vehicles, ArrayList<String> species, String created, String edited, String url) {
        this.title = title;
        this.episode_id = episode_id;
        this.opening_crawl = opening_crawl;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public ArrayList<String> getPlanets() {
        return planets;
    }

    public ArrayList<String> getStarships() {
        return starships;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }

    public Film clone(){
        Film tmp = new Film(this.title,
        this.episode_id,
        this.opening_crawl,
        this.director,
        this.producer,
        this.release_date,
        this.characters,
        this.planets,
        this.starships,
        this.vehicles,
        this.species,
        this.created,
        this.edited,
        this.url);
        return tmp;
    }
}
