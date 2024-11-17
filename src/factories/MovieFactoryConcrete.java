package factories;

import movies.*;
public class MovieFactoryConcrete {

    public Movie createMovie(String title, String description, String gender, int year, double price){
        switch (gender.toLowerCase()){
            case "action":
                return new Action(title, description, gender, year, price);
            case "horror":
                return new Action(title, description, gender, year, price);
            case "comedy":
                return new Action(title, description, gender, year, price);
            case "drama":
                return new Action(title, description, gender, year, price);
            default:
                throw new IllegalArgumentException("Genero não existente: " + gender);
        }
    }
}
