package rental;

import movies.Movie;
import strategy.PriceStrategy;

public class Rental {
    private Movie movie;
    private PriceStrategy priceStrategy;

    public Rental(Movie movie, PriceStrategy priceStrategy) {
        this.movie = movie;
        this.priceStrategy = priceStrategy;
    }

    public double finalPrice(){
        return  priceStrategy.calculatePrice(movie);
    }

    public void showInfo(){
        System.out.println("Movie: " + movie.getTitle() + " | Genre: " + movie.getGenre() +
                " | Final price: $" + finalPrice());
    }
}
