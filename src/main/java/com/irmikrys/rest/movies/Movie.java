package com.irmikrys.rest.movies;

public class Movie {

    public static int counter = -2;
    private int id;
    private String uri;
    private String title;

    public Movie() {

    }

    public Movie(String title) {
        counter++;
        id = counter;
        this.uri = "http://localhost:8080/rest/movies/" + this.id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
