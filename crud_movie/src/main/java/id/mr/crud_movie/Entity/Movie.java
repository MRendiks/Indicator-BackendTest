package id.mr.crud_movie.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movie_id;

    private String movie_name;
    private Integer movie_year;
    private Integer movie_runtime;
    private Float imdb_ratings;
    private Integer metascore;
    private Integer number_votes;
    private Float us_gross_millions;

    

    public Movie() {
    }

    public Movie(Integer movie_id, String movie_name, Integer movie_year, Integer movie_runtime,Float imdb_ratings, Integer metascore, Integer number_votes, Float us_gross_millions){
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_year = movie_year;
        this.movie_runtime = movie_runtime;
        this.imdb_ratings = imdb_ratings;
        this.metascore = metascore;
        this.number_votes = number_votes;
        this.us_gross_millions = us_gross_millions;
    }

    // Setter
    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }
    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }
    public void setMovie_year(Integer movie_year) {
        this.movie_year = movie_year;
    }
    public void setMovie_runtime(Integer movie_runtime) {
        this.movie_runtime = movie_runtime;
    }
    public void setImdb_ratings(Float imdb_ratings) {
        this.imdb_ratings = imdb_ratings;
    }
    public void setMetascore(Integer metascore) {
        this.metascore = metascore;
    }
    public void setNumber_votes(Integer number_votes) {
        this.number_votes = number_votes;
    }
    public void setUs_gross_millions(Float us_gross_millions) {
        this.us_gross_millions = us_gross_millions;
    }

    // GETTER
    public Integer getMovie_id() {
        return movie_id;
    }
    public String getMovie_name() {
        return movie_name;
    }
    public Integer getMovie_year() {
        return movie_year;
    }
    public Integer getMovie_runtime() {
        return movie_runtime;
    }
    public Float getImdb_ratings() {
        return imdb_ratings;
    }
    public Integer getMetascore() {
        return metascore;
    }
    public Integer getNumber_votes() {
        return number_votes;
    }
    public Float getUs_gross_millions() {
        return us_gross_millions;
    }
}
