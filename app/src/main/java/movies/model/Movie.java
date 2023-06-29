package movies.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private Integer id;
    private String name;
    private int minutes;
    private Genre genre;
    private String director;

    public Movie(String name, int minutes, Genre genre, String director){
        this(null, name, minutes, genre, director);
    }

}
