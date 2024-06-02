package at.ac.fhcampuswien.fhmdb.controllers;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    private static MovieListController movieListController;
    private static MainController mainController;
    private static WatchlistController watchlistController;

    @Override
    public Object call(Class<?> type) {
        if (type == MovieListController.class) {
            if (movieListController == null) {
                movieListController = new MovieListController();
            }
            return movieListController;
        } else if (type == MainController.class) {
            if (mainController == null) {
                mainController = new MainController();
            }
            return mainController;
        } else if (type == WatchlistController.class) {
            if (watchlistController == null) {
                watchlistController = new WatchlistController();
            }
            return watchlistController;
        }

        // Wenn der Typ nicht unterstützt wird, geben Sie null zurück oder werfen Sie eine Ausnahme.
        return null;
    }
}
