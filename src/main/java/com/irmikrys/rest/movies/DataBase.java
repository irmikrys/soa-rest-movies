package com.irmikrys.rest.movies;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
public class DataBase {

    private List<Movie> movies = new ArrayList<>();

    public DataBase() {
        movies.add(new Movie("Avengers"));
        movies.add(new Movie("Harry Potter"));
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Optional<Movie> getMovie(int id) {
        return movies
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Movie> getMovieByTitle(String title) {
        return movies
                .stream()
                .filter(m -> m.getTitle().equals(title))
                .findFirst();
    }

    public void addMovie(MovieDTO movieDTO) {
        movies.add(new Movie(movieDTO.getTitle()));
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public List<String> getMoviesUri() {
        return movies
                .stream()
                .map(Movie::getUri)
                .collect(Collectors.toList());
    }
}
