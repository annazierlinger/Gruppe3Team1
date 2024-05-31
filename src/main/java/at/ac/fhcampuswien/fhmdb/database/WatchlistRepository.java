package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Observable;
import at.ac.fhcampuswien.fhmdb.models.Observer;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {

    Dao<WatchlistMovieEntity, Long> dao;
    private static WatchlistRepository watchlistRepository_instance = null;
    private List<Observer> observers = new ArrayList<>();

    private WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public static WatchlistRepository getInstance() throws DataBaseException {
        if(watchlistRepository_instance == null){
            watchlistRepository_instance = new WatchlistRepository();
        }
        return watchlistRepository_instance;
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public int addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                notifyObservers(); //Notify observers when a movie is added to watchlist
                return dao.create(movie);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public int removeFromWatchlist(String apiId) throws DataBaseException {
        try {
            return dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
