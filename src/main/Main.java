package main;

import factories.MovieFactoryConcrete;
import movies.Movie;
import repository.Database;
import clients.Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("BEM-VINDO(A) À LOCADORA!");
        Database database = Database.getInstance();

        //TESTANDO CLIENTES//
        Client client1 = new Client("João Silva", "12345678911");
        Client client2 = new Client("Maria Oliveira", "19876543211");

        database.addClients(client1);
        database.addClients(client2);

        System.out.println("CLIENTES CADASTRADOS: ");
        database.loadClients().forEach(c -> System.out.println(c.getName() + " - " + c.getId()));

        //TESTANDO FILMES//
        MovieFactoryConcrete factory = new MovieFactoryConcrete();

        Movie movie1 = factory.createMovie("velozes e furiosos", "dom e seus amigos partem em uma aventura", "action", 2002, 15.0);
        Movie movie2 = factory.createMovie("avatar","uma aventura em outro planeta","comedy",2009,20.0);
        //movie1.showInfo();

        System.out.println("FILMES CADASTRADOS: ");
        database.addMovie(movie1);

        database.loadMovie().forEach(f -> System.out.println(f.getTitle() + " - " + f.getDescription() + " - " + f.getGenre() + " - " + f.getYear() + " - " + f.getPrice()));
    }
}