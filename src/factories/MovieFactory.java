package factories;

import movies.Movie;

public abstract class MovieFactory {

    abstract Movie createMovie(String title, String description, String genre, int year, double price);

    public Movie openMovie(String title, String description, String genre, int year, double price){
        return createMovie(title, description, genre, year, price);
    }
}



