package factories;

import movies.*;
public class MovieFactoryConcrete extends MovieFactory {

    @Override
    public Movie createMovie(String title, String description, String genre, int year, double price){
        switch (genre.toLowerCase()){
            case "action":
                return new Action(title, description, year, price);
            case "horror":
                return new Horror(title, description, year, price);
            case "comedy":
                return new Comedy(title, description, year, price);
            case "drama":
                return new Drama(title, description, year, price);
            default:
                throw new IllegalArgumentException("Genero não existente: " + genre);
        }
    }
}
