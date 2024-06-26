package at.ac.fhcampuswien.fhmdb.models;

import java.util.Comparator;
import java.util.List;

public class NoneState implements State{
    private SortingContext context;

    public NoneState(SortingContext context) {
        this.context = context;
    }


    @Override
    public void sortMovies(List<Movie> movieList) {
    }

    @Override
    public void sortButtonClicked() {
        context.changeState(new AscendingState(context));
    }
}
