package strategy;

import movies.Movie;

public class BFPrice implements PriceStrategy{

    private static final double BLACK_FRIDAY_DISCOUNT = 0.30;

    @Override
    public double calculatePrice(Movie movie){
        return movie.getPrice() * (1 - BLACK_FRIDAY_DISCOUNT);
    }

}
