package at.ac.fhcampuswien.fhmdb.models;
import java.util.List;

public class SortingContext {
    State state;

    public SortingContext(State initialState){
        this.state = initialState;
    }

    public SortingContext() {
        this.state = new NoneState(this);
    }

    public void sorting(List movieList){
        state.sortMovies(movieList);
    }

    public void changeState(State newState){        //TODO
        setState(newState);
    }

    private void setState(State state) {
        this.state = state;
    }

    public void sortButtonClicked(){
        state.sortButtonClicked();
    }
}
