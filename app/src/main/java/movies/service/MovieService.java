package movies.service;

import java.util.Collection;
import java.util.stream.Collectors;

import movies.data.iMovieRepository;
import movies.model.Genre;
import movies.model.Movie;

public class MovieService {
    private iMovieRepository movieRepository;

    public MovieService(iMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre){
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }
    
    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
    }
    
}
