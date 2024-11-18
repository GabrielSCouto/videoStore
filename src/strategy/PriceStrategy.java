package strategy;

import movies.Movie;

public interface PriceStrategy {
    double calculatePrice(Movie movie);
}
