package movies.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import movies.model.Genre;
import movies.model.Movie;
import movies.service.MovieService;

import static org.hamcrest.CoreMatchers.*;

public class MovieRepositoryIntegrationShould {
    @Test
    public void loadAllMovies() throws ScriptException, SQLException {

        DataSource dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        MovieRepositoryJdbc movieRepositoryJdbc = new MovieRepositoryJdbc(jdbcTemplate);

        Collection<Movie> movies = movieRepositoryJdbc.findAll();


        Collection<Movie> moviesExpected = Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        );

        assertEquals(movies, moviesExpected);
        /*assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )) );
        */
    }

    @Test
    public void testFindById() {

    }

    @Test
    public void testSaveOrUpdate() {

    }
}
