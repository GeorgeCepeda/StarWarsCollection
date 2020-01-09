package com.example.starwarscollection;

import java.util.ArrayList;

public class Character {
    private String name;
    private int height;
    private int mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;//Just a url from the API
    private ArrayList<String> films;
    private ArrayList<String> species;
    private ArrayList<String> vehicles;
    private ArrayList<String> starships;
    //Not that major tbh
    private String created;
    private String edited;
    private String url;

    public Character(String name, int height, int mass, String hair_color, String skin_color, String eye_color, String birth_year, String gender, String homeworld, ArrayList<String> films, ArrayList<String> species, ArrayList<String> vehicles, ArrayList<String> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getMass() {
        return mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public ArrayList<String> getStarships() {
        return starships;
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
}
