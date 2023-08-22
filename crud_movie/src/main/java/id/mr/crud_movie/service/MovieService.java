package id.mr.crud_movie.service;

import java.util.List;

import id.mr.crud_movie.Entity.Movie;

public interface MovieService {
    Movie saveMovie(Movie movie);
    List<Movie> fetchAllMovie();
    Movie getMovieById(Integer movieId);
    Movie UpdateMovieById(Integer movieId, Movie movie);
    Boolean deleteMovieById(Integer movieId);
}
