package movies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private int minutes;
    private Genre genre;

    public Movie(String name, int minutes, Genre genre){
        this(null, name, minutes, genre);
    }

}
