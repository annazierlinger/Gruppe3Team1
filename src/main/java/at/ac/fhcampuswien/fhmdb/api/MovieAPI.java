package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import okhttp3.*;
import com.google.gson.Gson;

import java.util.*;

public class MovieAPI implements Builder {
    public static final String DELIMITER = "&";
    private static final String URL = "https://prog2.fh-campuswien.ac.at/movies"; // https if certificates work
    private static final OkHttpClient client = new OkHttpClient();
    private List<String> parameters;

    public static Movie getMovie(String apiId) {
        return null;
    }


    //Builder Pattern
    public MovieAPI(){
        this.parameters = new ArrayList<>();
    }

    @Override
    public Builder addID(UUID id){
        if(id != null){
            parameters.add("/" + id);
        }
        return this;
    }

    @Override
    public Builder addQuery(String query){
        if(query != null && !query.isEmpty()){
            parameters.add("query=" + query);
        }
        return this;
    }

    @Override
    public Builder addGenre(Genre genre){
        if(genre != null){
            parameters.add("genre=" + genre);
        }
        return this;
    }

    @Override
    public Builder addReleaseYear(String releaseYear){
        if(releaseYear != null && !releaseYear.isEmpty()){
            parameters.add("releaseYear=" + releaseYear);
        }
        return this;
    }

    @Override
    public Builder addRatingFrom(String ratingFrom){
        if(ratingFrom != null && !ratingFrom.isEmpty()){
            parameters.add("ratingFrom=" + ratingFrom);
        }
        return this;
    }

    @Override
    public String build(){
        if(parameters.isEmpty()){
            return URL;
        }
        else{
            return URL + "?" + String.join(DELIMITER, parameters);
        }
    }



    public static List<Movie> getAllMovies() throws MovieApiException {
        return getAllMovies(null, null, null, null);
    }

    //Builder Pattern
    public static List<Movie> getAllMovies(String query, Genre genre, String releaseYear, String ratingFrom) throws MovieApiException{
        Builder builder = new MovieAPI()
            .addQuery(query)
            .addGenre(genre)
            .addReleaseYear(releaseYear)
            .addRatingFrom(ratingFrom);
        String url = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")  // needed for the server to accept the request
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);

            return Arrays.asList(movies);
        } catch (Exception e) {
            throw new MovieApiException(e.getMessage());
        }

    }


    public Movie requestMovieById(UUID id) throws MovieApiException {
        Builder builder = new MovieAPI().addID(id);
        String url = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            return gson.fromJson(Objects.requireNonNull(response.body()).string(), Movie.class);
        } catch (Exception e) {
            throw new MovieApiException(e.getMessage());
        }
    }
}
