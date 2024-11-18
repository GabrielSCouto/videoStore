package strategy;

import movies.Movie;

public class NormalPrice implements PriceStrategy{
    @Override
    public double calculatePrice(Movie movie){
        return movie.getPrice();
    }
}
