package main;

import factories.MovieFactory;
import factories.MovieFactoryConcrete;
import movies.*;
import rental.Rental;
import repository.Database;
import clients.Client;
import strategy.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("BEM-VINDO(A) À LOCADORA!");
        Database database = Database.getInstance();

        //TESTANDO CLIENTES//
        Client client1 = new Client("João Silva", "12345678911");
        Client client2 = new Client("Maria Oliveira", "19876543211");

        database.addClients(client1);
        database.addClients(client2);

        System.out.println("\nCLIENTES CADASTRADOS: ");
        database.loadClients().forEach(c -> System.out.println(c.getName() + " - " + c.getId()));

        //TESTANDO FILMES//
        MovieFactory factory = new MovieFactoryConcrete();

        Movie movie1 = factory.openMovie("velozes e furiosos", "dom e seus amigos aventura", "action", 2002, 15.0);
        Movie movie2 = factory.openMovie("avatar","uma aventura em outro planeta","comedy",2009,20.0);

        System.out.println("-----------------------");
        System.out.println("\nFILMES CADASTRADOS: ");
        database.addMovie(movie1);
        database.addMovie(movie2);

        database.loadMovie().forEach(f -> System.out.println(f.getTitle() + " - " + f.getDescription() + " - " + f.getGenre() + " - " + f.getYear() + " - " + f.getPrice()));


        // STRATEGY PRECOS
        PriceStrategy normal = new NormalPrice();
        PriceStrategy blackFriday = new BFPrice();

        Rental rent1 = new Rental(movie1, normal);
        Rental rent2 = new Rental(movie1, blackFriday);

        System.out.println("\n-------------------------------------");

        System.out.println("-------ALUGUEL PRECO NORMAL: -------");
        rent1.showInfo();

        System.out.println("--------BLACK FRIDAY: ---------");
        rent2.showInfo();


    }
}