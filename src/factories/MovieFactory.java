package factories;

import movies.Movie;

public interface MovieFactory {

    Movie createMovie(String title, String description, String gender, int year);
}
