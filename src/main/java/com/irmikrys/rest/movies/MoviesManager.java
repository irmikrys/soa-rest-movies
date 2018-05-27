package com.irmikrys.rest.movies;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("")
public class MoviesManager {

    @Inject
    private DataBase moviesDB;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/art")
    public Response redirectToMovies() {
        return Response
                .status(Response.Status.MOVED_PERMANENTLY)
                .location(URI.create("http://localhost:8080/rest/movies"))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies")
    public List<Movie> getAllMovies() {
        return moviesDB.getMovies();
    }

    @GET
//    @Produces("text/uri-list")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies/uris")
    public List<String> getAllUris() {
        return moviesDB.getMoviesUri();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies/{id}")
    public Response getMovieById(@PathParam("id") int id) {
        Optional<Movie> movie = moviesDB.getMovie(id);
        if (movie.isPresent()) {
            return Response.ok(movie.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movies")
    public Response getMovieByTitle(@QueryParam("title") String title) {
        Optional<Movie> movie = moviesDB.getMovieByTitle(title);
        if (movie.isPresent()) {
            return Response.ok(movie.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/movies")
    public Response addMovie(MovieDTO movieDTO) {
        moviesDB.addMovie(movieDTO);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/movies/{id}")
    public Response deleteMovie(@PathParam("id") int id) {
        Optional<Movie> movie = moviesDB.getMovie(id);
        if (movie.isPresent()) {
            moviesDB.removeMovie(movie.get());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

//d) - lista filmow jako text/plain --> HTTP/1.1 406 Not Acceptable
//   - aktualizacja calej kolekcji --> ??
//   - przekierowanie --> HTTP 301 Moved Permanently lub 307 Moved Temporary (.temporaryRedirect(url))