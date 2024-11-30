package main;

import clients.Client;
import factories.MovieFactoryConcrete;
import rental.Rental;
import repository.Database;
import movies.Movie;
import strategy.BFPrice;
import strategy.NormalPrice;
import strategy.PriceStrategy;

import java.util.Scanner;

public class NavigationMenu {

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("----------------------------------");
            System.out.println("WELCOME TO VIDEO_RENT!");
            System.out.println("----------------------------------");
            System.out.println();

            System.out.println("-----MENU-----\n1 - CATALOG\n2 - CLIENTS\n3 - OPERATIONS\n0 - CLOSE");
            while (true){
                try {
                    option = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e){
                    System.err.println("Error: " + e);
                    System.err.print("Try again: ");
                    sc.next();
                }
            }
            switch (option){
                case 1:
                    MovieFactoryConcrete fc = new MovieFactoryConcrete();
                    Database db = Database.getInstance();

                    int optionMovie;
                    do {
                        System.out.println("\n---CATALOG MENU---");
                        System.out.println("\n1 - INSERT MOVIE\n2 - SEE CATALOG\n3 - UPDATE CATALOG\n4 - REMOVE MOVIE\n5 - RESET ALL\n0 - CLOSE");
                        System.out.print("\nSELECT AN OPTION: ");
                        while (true){
                            try {
                                optionMovie = sc.nextInt();
                                sc.nextLine();
                                break;
                            } catch (Exception e){
                                System.err.println("Error: " + e);
                                System.err.print("Try again: ");
                                sc.next();
                            }
                        }
                        switch (optionMovie){
                            case 1:
                                System.out.println("\n---REGISTER MOVIE---\n");

                                System.out.print("TITLE: ");
                                String title = sc.nextLine();
                                System.out.print("DESCRIPTION: ");
                                String description = sc.nextLine();
                                System.out.print("GENRE: ");
                                String genre = sc.nextLine();
                                System.out.print("LAUNCH YEAR: ");
                                int year = sc.nextInt();
                                System.out.print("PRICE: $");
                                double price = sc.nextDouble();

                                Movie movie = fc.openMovie(title,description,genre,year,price);
                                db.addMovie(movie);

                                System.out.println("\nSUCCESSFULLY ADDED MOVIE TO CATALOG!\n");
                                break;
                            case 2:
                                System.out.println("\n---REGISTERED MOVIES---\n");

                                db.loadMovie().forEach(f -> System.out.println("MOVIE: "+ f.getTitle() + " |DESCRIPTION: " + f.getDescription() + " |GENRE: " + f.getGenre() + " |LAUNCH: " + f.getYear() + " |PRICE: $" + f.getPrice()));
                                break;
                            case 3:
                                System.out.println("\n---UPDATE MOVIE---\n");

                                System.out.print("NAME OF MOVIE TO BE ALTERED: ");
                                String oldTitle = sc.nextLine();
                                System.out.print("NEW TITLE: ");
                                String newTitle = sc.nextLine();
                                System.out.print("NEW DESCRIPTION: ");
                                String newDescription = sc.nextLine();
                                System.out.print("NEW GENRE: ");
                                String newGenre = sc.nextLine();
                                System.out.print("NEW LAUNCH YEAR: ");
                                int newYear = sc.nextInt();
                                sc.nextLine();
                                System.out.print("NEW PRICE: $");
                                double newPrice = sc.nextDouble();

                                db.updateMovie(oldTitle, newTitle, newDescription, newGenre, newYear, newPrice);
                                System.out.println("\nSUCCESSFULLY UPDATED MOVIE!\n");
                                break;
                            case 4:
                                System.out.println("\n---DELETE MOVIE---\n");
                                System.out.println("INSERT TITLE TO DELETE:");
                                String titleRemove = sc.nextLine();
                                db.removeMovie(titleRemove);
                                System.out.println("SUCCESSFULLY DELETED MOVIE!");
                                break;
                            case 5:
                                System.out.println("\n---(!!!)RESET ALL(!!!)---");
                                sc.nextLine();
                                System.out.print("ARE YOU REALLY SURE? (Y/N): ");
                                String confirm = sc.nextLine();
                                if (confirm.equalsIgnoreCase("y")){
                                    db.eraseAllMovies();
                                    System.out.println("SUCCESSFULLY RESET MOVIE CSV FILE!");
                                    break;
                                }

                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("INVALID OPTION! TRY AGAIN.");
                                break;
                        }
                    } while (optionMovie != 0);
                    break;
                case 2:
                    Database db1 = Database.getInstance();
                    int optionClient;
                    do {
                        System.out.println("\n---CLIENT'S MENU---");
                        System.out.println("\n1 - REGISTER CLIENT\n2 - SHOW CLIENTS\n3 - UPDATE INFO.\n4 - REMOVE CLIENT\n5 - RESET ALL \n0 - CLOSE");
                        while (true){
                            try {
                                optionClient = sc.nextInt();
                                break;
                            } catch (Exception e){
                                System.err.println("Error: " + e);
                                System.err.print("Try again: ");
                                sc.next();
                            }
                        }
                        switch (optionClient){
                            case 1:

                                System.out.println("\n---REGISTER CLIENT---\n");

                                sc.nextLine();
                                System.out.print("NAME: ");
                                String name = sc.nextLine();
                                System.out.print("ID: ");
                                String id = sc.nextLine();

                                Client client = new Client(name, id);
                                db1.addClients(client);

                                System.out.println("\nSUCCESSFULLY ADDED CLIENT!\n");
                                break;
                            case 2:
                                System.out.println("\n---REGISTERED CLIENTS---\n");

                                db1.loadClients().forEach(f -> System.out.println("NAME: "+ f.getName() + " |ID: " + f.getId()));

                                break;
                            case 3:
                                System.out.println("\n---UPDATE CLIENT---");
                                sc.nextLine();
                                System.out.print("\nINSERT CLIENT ID: ");
                                String idUpdate = sc.nextLine();
                                System.out.print("NEW NAME: ");
                                String newName = sc.nextLine();
                                System.out.print("NEW ID: ");
                                String newId = sc.nextLine();
                                db1.updateClient(idUpdate, newName, newId);
                                System.out.println("\nSUCCESSFULLY UPDATED CLIENT!");
                                break;
                            case 4:
                                System.out.println("\n---DELETE CLIENT---");
                                sc.nextLine();
                                System.out.print("INSERT CLIENT ID (Permanent action!): ");
                                String idRemove = sc.nextLine();
                                db1.removeClient(idRemove);
                                System.out.println("\nSUCCESSFULLY DELETED CLIENT!");
                                break;
                            case 5:
                                System.out.println("\n---(!!!)RESET ALL(!!!)---");
                                sc.nextLine();
                                System.out.print("ARE YOU REALLY SURE? (Y/N): ");
                                String confirm = sc.nextLine();
                                if (confirm.equalsIgnoreCase("y")){
                                    db1.eraseAllClients();
                                    System.out.println("SUCCESSFULLY RESET CLIENT CSV FILE!");
                                    break;
                                }

                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("INVALID OPTION! TRY AGAIN.");
                                break;
                        }
                    } while (optionClient != 0);
                    break;


                case 3:
                    Database db2 = Database.getInstance();
                    String movieName;
                    String clientID;
                    int optionRental;
                    do {
                        System.out.println("---RENTAL MENU---");

                        System.out.print("INSERT MOVIE NAME (or 0 to close tab): ");
                        movieName = sc.nextLine();

                        if (movieName.equals("0")) break;

                        Movie selectedMovie = null;
                        for (Movie movie : db2.loadMovie()) {
                            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                                selectedMovie = movie;
                                break;
                            }
                        }

                        if (selectedMovie == null) {
                            System.out.println("MOVIE NOT FOUND! TRY AGAIN.");
                            continue;
                        }

                        System.out.print("CLIENT ID: ");
                        clientID = sc.nextLine();

                        Client selectedClient = null;
                        for (Client client : db2.loadClients()) {
                            if (client.getId().equals(clientID)) {
                                selectedClient = client;
                                break;
                            }
                        }

                        if (selectedClient == null) {
                            System.out.println("CLIENT NOT FOUND! TRY AGAIN.");
                            continue;
                        }

                        System.out.println("\n1 - NORMAL PRICE\n2 - BLACK FRIDAY PRICE");
                        System.out.print("\nCHOOSE PRICING STRATEGY: ");

                        while (true) {
                            try {
                                optionRental = sc.nextInt();
                                sc.nextLine();
                                if (optionRental == 1 || optionRental == 2) break;
                                else System.out.println("INVALID OPTION! TRY AGAIN.");
                            } catch (Exception e) {
                                System.err.println("INVALID INPUT! INSERT A NUMBER.");
                                sc.next();
                            }
                        }

                        PriceStrategy priceStrategy = optionRental == 1 ? new NormalPrice() : new BFPrice();
                        Rental rental = new Rental(selectedMovie, priceStrategy);
                        System.out.println("SUCCESSFULLY RENTED MOVIE!");
                        rental.showInfo();

                    } while (true);
                    break;
                default:
                    System.out.println();
                    break;
            }
        } while (option != 0);
    sc.close();
    }
}
