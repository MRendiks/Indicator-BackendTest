package id.mr.crud_movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.mr.crud_movie.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
}
