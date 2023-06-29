package movies.service;

import static org.hamcrest.MatcherAssert.assertThat;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import movies.data.iMovieRepository;
import movies.model.Genre;
import movies.model.Movie;

public class MovieServiceShould {
    
    iMovieRepository movieRepository; 
    MovieService movieService;
    @Before
    public void setUp() {
        movieRepository = Mockito.mock(iMovieRepository.class);
        movieService = new MovieService(movieRepository);

        Mockito.when(movieRepository.findAll()).thenReturn(
                        Arrays.asList(
                            new Movie(1, "Dark Knight", 152, Genre.ACTION, "Dark Knight Director"),
                            new Movie(2, "Memento", 113, Genre.THRILLER, "Memento Director"),
                            new Movie(3, "There's Something About Mary", 119, Genre.COMEDY, "There's Something About Mary Director"),
                            new Movie(4, "Super 8", 112, Genre.THRILLER, "Super 8 Director"),
                            new Movie(5, "Scream", 111, Genre.HORROR, "Scream Director"),
                            new Movie(6, "Home Alone", 103, Genre.COMEDY, "Home Alone Director"),
                            new Movie(7, "Matrix", 136, Genre.ACTION, "Matrix Director")
                        ));
    }
    
    @Test
    public void returnMoviesByGenre() {

        Collection<Movie> comedyMovies = movieService.findMoviesByGenre(Genre.COMEDY);

        List<Integer> comedyMoviesId = getMoviesIds(comedyMovies);

        assertThat(comedyMoviesId, CoreMatchers.is(Arrays.asList(3, 6)));
        // Posible way to do it 
        //List<Integer> comedyMoviesIdExpected = Arrays.asList(3, 6);
        //assertEquals(comedyMoviesId,comedyMoviesIdExpected);

    }

    @Test
    public void returnMoviesByLength() {
        Collection<Movie> moviesLength = movieService.findMoviesByLength(110);
        List<Integer> moviesLengthIds = moviesLength.stream().map(movie -> movie.getId()).collect(Collectors.toList());
        Collection<Movie> expectedMoviesByLenght = Arrays.asList(
                    new Movie(6, "Home Alone", 103, Genre.COMEDY, "Home Alone Director")
        );
        
        List<Integer> expecteMoviesIds = getMoviesIds(expectedMoviesByLenght);

        assertEquals(moviesLengthIds,expecteMoviesIds);
    }

    private List<Integer> getMoviesIds(Collection<Movie> movies) {
        // Alternativa
        // return movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }

    
    
}
