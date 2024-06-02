package at.ac.fhcampuswien.fhmdb.models;

import java.util.Comparator;
import java.util.List;

public class AscendingState implements State{

    private SortingContext context;

    public AscendingState(SortingContext context) {
        this.context = context;
    }

    @Override
    public void sortMovies(List<Movie> movieList) {
        movieList.sort(Comparator.comparing(Movie::getTitle));
    }
    @Override
    public void sortButtonClicked() {
        context.changeState(new DescendingState(context));
    }

}
