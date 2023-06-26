package movies.data;

import java.util.Collection;

import movies.model.Movie;

@AllArgsConstructor
public class MovieRepositoryJdbc implements iMovieRepository {

    JdbcTemplate JdbcTemplate;

    @Override
    public Movie findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Collection<Movie> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
    }

}
