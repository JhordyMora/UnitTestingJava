package movies.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;
import movies.model.Genre;
import movies.model.Movie;

@AllArgsConstructor
public class MovieRepositoryJdbc implements iMovieRepository {

    JdbcTemplate jdbcTemplate;

    private static RowMapper<Movie> movieMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("minutes"),
            Genre.valueOf(rs.getString("genre")));

    @Override
    public Movie findById(long id) {
        Object[] args = { id };
        return jdbcTemplate.queryForObject("select * from movies where id=?", movieMapper, args);

    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("insert into movies (name, minutes, genre) values(?,?,?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }

    @Override
    public Collection<Movie> findByName(String name) {
        /*
         * String nameModified = "%" + name + "%";
         * Object[] args = { nameModified };
         * Collection<Movie> result
         * =jdbcTemplate.query("select * from movies where name like ?", movieMapper,
         * args);
         */
        Collection<Movie> allMovies = findAll();
        Collection<Movie> result = allMovies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}
