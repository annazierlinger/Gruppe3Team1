package at.ac.fhcampuswien.fhmdb.models;

import java.util.Comparator;
import java.util.List;

public class DescendingState implements State{

    private SortingContext context;

    public DescendingState(SortingContext context) {
        this.context = context;
    }

    @Override
    public void sortMovies(List<Movie> movieList) {
        movieList.sort(Comparator.comparing(Movie::getTitle));
        context.changeState(new AscendingState(context));
    }
}
