package movies.data;

import java.util.Collection;

import movies.model.Movie;

// Esta interfas deberia ser implementada dentro de alguna clase de la capa de persistencia para que sirva ser injectada en el servicio
public interface iMovieRepository {
    Movie findById(long id);

    Collection<Movie> findAll();

    void saveOrUpdate(Movie movie);
}
