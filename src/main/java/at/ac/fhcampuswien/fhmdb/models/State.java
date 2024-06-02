package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;

public interface State {

    void sortMovies(List<Movie> movieList);
    void sortButtonClicked();
}
