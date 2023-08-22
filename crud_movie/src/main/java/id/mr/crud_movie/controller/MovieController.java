package id.mr.crud_movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.mr.crud_movie.Entity.Movie;
import id.mr.crud_movie.service.MovieService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping(value="/create-movie")
    public Boolean saveMovie(@ModelAttribute("Movie-Form") Movie movie){
        try{
            movieService.saveMovie(movie);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value="/movie")
    public List<Movie> getAllMovies(){
        return movieService.fetchAllMovie();
    }

    @GetMapping(value="/movie/{id}")
    public Movie getMovieById(@PathVariable("id") Integer id){
        return movieService.getMovieById(id);
    }

    @PutMapping(value="/update-movie/{id}")
    public Boolean updateMovie(@PathVariable("id") Integer id, @ModelAttribute("Movie-Form") Movie movie) {
        try{
            movieService.UpdateMovieById(id, movie);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping(value="/delete-movie/{id}")
    public Boolean DeleteMovie(@PathVariable("id") Integer id){

        try{
            movieService.deleteMovieById(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
