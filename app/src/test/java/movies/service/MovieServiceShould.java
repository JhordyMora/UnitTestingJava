package movies.service;

import static org.hamcrest.MatcherAssert.assertThat;
// import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

import movies.data.iMovieRepository;
import movies.model.Genre;
import movies.model.Movie;

public class MovieServiceShould {

    @Test
    public void returnMoviesByGenre() {
        iMovieRepository movieRepository = Mockito.mock(iMovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                        Arrays.asList(
                            new Movie(1, "Dark Knight", 152, Genre.ACTION),
                            new Movie(2, "Memento", 113, Genre.THRILLER),
                            new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                            new Movie(4, "Super 8", 112, Genre.THRILLER),
                            new Movie(5, "Scream", 111, Genre.HORROR),
                            new Movie(6, "Home Alone", 103, Genre.COMEDY),
                            new Movie(7, "Matrix", 136, Genre.ACTION)
                        ));
        MovieService movieService = new MovieService(movieRepository);

        Collection<Movie> comedyMovies = movieService.findMoviesByGenre(Genre.COMEDY);

        List<Integer> comedyMoviesId = comedyMovies.stream().map(movie -> movie.getId()).collect(Collectors.toList());

        assertThat(comedyMoviesId, CoreMatchers.is(Arrays.asList(3, 6)));
        // Posible way to do it 
        //List<Integer> comedyMoviesIdExpected = Arrays.asList(3, 6);
        //assertEquals(comedyMoviesId,comedyMoviesIdExpected);

    }
    
}
