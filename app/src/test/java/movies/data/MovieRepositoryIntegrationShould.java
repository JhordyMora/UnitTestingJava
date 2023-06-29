package movies.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.mockito.Mockito.timeout;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import movies.model.Genre;
import movies.model.Movie;

import static org.hamcrest.CoreMatchers.*;

public class MovieRepositoryIntegrationShould {
    private MovieRepositoryJdbc movieRepositoryJdbc;
    DataSource dataSource;
    
    @Before
    public void beforeClass() throws ScriptException, SQLException {
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepositoryJdbc = new MovieRepositoryJdbc(jdbcTemplate);


    }    
    
    @Test
    public void loadAllMovies() {

        Collection<Movie> movies = movieRepositoryJdbc.findAll();


        Collection<Movie> moviesExpected = Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION, "Dark Knight Director"),
                new Movie(2, "Memento", 113, Genre.THRILLER, "Memento Director"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Matrix Director"),
                new Movie(4,"Super Loco", 139, Genre.COMEDY, "Super Loco Director"),
                new Movie (5,"Superman", 200, Genre.ACTION, "Superman Director")
        );

        assertEquals(movies, moviesExpected);
        /*
        falta agregar todas las peliculas
        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )) );
        */
    }

    @Test
    public void findById() {
        Movie movie = movieRepositoryJdbc.findById(2);
        Movie expectedMovie =new Movie(2, "Memento", 113, Genre.THRILLER, "Memento Director");
        assertEquals(movie, expectedMovie);
        //Collection<Movie> movies = movieRepositoryJdbc.
    }

    @Test
    public void saveOrUpdate() {
        Movie movieDB = new Movie("Super 8", 112, Genre.THRILLER, "Super 8 Director");
        movieRepositoryJdbc.saveOrUpdate(movieDB);
        Collection<Movie> movieCollection = movieRepositoryJdbc.findByName("Super 8");
        List<Movie> movies = movieCollection.stream().collect(Collectors.toList());
        Movie movieWithId = movies.get(0);
        Movie movie = new Movie(movieWithId.getName(), movieWithId.getMinutes(), movieWithId.getGenre(), movieWithId.getDirector());
        Movie movieExpected  = new Movie("Super 8", 112, Genre.THRILLER, "Super 8 Director");

        assertEquals(movieExpected, movie);
    }

    

    @Test
    public void returnMoviesWithNameSuperCapital() {
        Collection<Movie> moviesExpected = Arrays.asList(
                new Movie ("Super Loco", 139, Genre.COMEDY, "Super Loco Director"),
                new Movie ("Superman", 200, Genre.ACTION, "Superman Director")
        );

        Collection<Movie> moviesDB = movieRepositoryJdbc.findByName("Super");
        Collection<Movie> moviesFromDb = moviesDB.stream().map(movie -> new Movie(movie.getName(), movie.getMinutes(), movie.getGenre(), movie.getDirector())).collect(Collectors.toList());
        
        assertEquals(moviesExpected, moviesFromDb);
    }

    @Test
    public void returnMoviesWithNameSuperNoCapital() {
        Collection<Movie> moviesExpected = Arrays.asList(
                new Movie ("Super Loco", 139, Genre.COMEDY, "Super Loco Director"),
                new Movie ("Superman", 200, Genre.ACTION, "Superman Director")
        );

        Collection<Movie> moviesDB = movieRepositoryJdbc.findByName("super");
        Collection<Movie> moviesFromDb = moviesDB.stream().map(movie -> new Movie(movie.getName(), movie.getMinutes(), movie.getGenre(), movie.getDirector())).collect(Collectors.toList());
        
        assertEquals(moviesExpected, moviesFromDb);
    }

    @After
    public void tearDown() throws SQLException {
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files"); // "Shutdown" is also enough for mem db
    }

    
}
