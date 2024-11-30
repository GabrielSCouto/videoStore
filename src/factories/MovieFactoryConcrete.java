package factories;

import movies.*;
import main.NavigationMenu;

public class MovieFactoryConcrete extends MovieFactory {

    @Override
    public Movie createMovie(String title, String description, String genre, int year, double price){
        switch (genre.toLowerCase()){
            case "action":
                return new Action(title, description, genre, year, price);
            case "horror":
                return new Horror(title, description, genre, year, price);
            case "comedy":
                return new Comedy(title, description, genre, year, price);
            case "drama":
                return new Drama(title, description, genre, year, price);
            default:
                throw new IllegalArgumentException("Non-existent genre: " + genre);

        }
    }
}
