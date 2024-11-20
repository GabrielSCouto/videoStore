package factories;

import movies.Movie;

public abstract class MovieFactory {

    Movie createMovie(String title, String description, String genre, int year, double price) {
        return null;
    } // variavel ou metodo

    public Movie openMovie(String title, String description, String genre, int year, double price){

        return createMovie(title, description, genre, year, price);
    }
}

//implemenacao classe abstrata