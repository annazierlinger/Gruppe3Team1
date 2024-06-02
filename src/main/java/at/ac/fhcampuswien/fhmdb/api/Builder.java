package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;

import java.util.UUID;

public interface Builder {
     Builder addID(UUID id);
     Builder addQuery(String query);
     Builder addGenre(Genre genre);
     Builder addReleaseYear(String releaseYear);
     Builder addRatingFrom(String ratingFrom);
     String build();
}
