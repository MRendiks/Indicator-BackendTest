package id.mr.crud_movie.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.mr.crud_movie.Entity.Movie;
import id.mr.crud_movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> fetchAllMovie() {
        List<Movie> allMovie = movieRepository.findAll();
        return allMovie;
    }

    @Override
    public Movie getMovieById(Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()){
            return movie.get();
        }
        return null;
    }

    @Override
    public Movie UpdateMovieById(Integer movieId, Movie movie) {
        Optional<Movie> movie1 = movieRepository.findById(movieId);

        if(movie1.isPresent()){
            Movie orginalMovie = movie1.get();

            if (Objects.nonNull(movie.getMovie_name()) && !"".equalsIgnoreCase(movie.getMovie_name())) {
                orginalMovie.setMovie_name(movie.getMovie_name());
            }
            if (Objects.nonNull(movie.getMovie_year()) && movie.getMovie_year() != 0) {
                orginalMovie.setMovie_year(movie.getMovie_year());
            }
            if (Objects.nonNull(movie.getMovie_runtime()) && movie.getMovie_runtime() != 0) {
                orginalMovie.setMovie_runtime(movie.getMovie_runtime());
            }
            if (Objects.nonNull(movie.getImdb_ratings()) && movie.getImdb_ratings() != 0) {
                orginalMovie.setImdb_ratings(movie.getImdb_ratings());
            }
            if (Objects.nonNull(movie.getMetascore()) && movie.getMetascore() != 0) {
                orginalMovie.setMetascore(movie.getMetascore());
            }
            if (Objects.nonNull(movie.getNumber_votes()) && movie.getNumber_votes() != 0) {
                orginalMovie.setMetascore(movie.getNumber_votes());
            }
            if (Objects.nonNull(movie.getImdb_ratings()) && movie.getImdb_ratings() != 0) {
                orginalMovie.setImdb_ratings(movie.getImdb_ratings());
            }

            return movieRepository.save(orginalMovie);
        }
        return null;
    }
    @Override
    public Boolean deleteMovieById(Integer movieId) {
        if (movieRepository.findById(movieId).isPresent()) {
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    }
}
