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
            System.out.println("BEM-VINDO(A) À LOCADORA VIDEO_RENT!");
            System.out.println("----------------------------------");
            System.out.println();

            System.out.println("-----MENU-----\n1 - CATÁLOGO\n2 - CLIENTE\n3 - OPERAÇÃO\n0 - SAIR");
            while (true){
                try {
                    option = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e){
                    System.err.println("Erro: " + e);
                    System.err.print("Tente novamente: ");
                    sc.next();
                }
            }
            switch (option){
                case 1:
                    System.out.println("catalogo");

                    MovieFactoryConcrete fc = new MovieFactoryConcrete();
                    Database db = Database.getInstance();

                    int optionMovie;
                    do {
                        System.out.println("---MENU DO CATÁLOGO---");
                        System.out.println("\n1 - INSERIR FILME\n2 - VER CATÁLOGO\n3 - ATUALIZAR CATÁLOGO\n4 - REMOVER FILME\n5 - LIMPAR TUDO\n0 - SAIR");
                        while (true){
                            try {
                                optionMovie = sc.nextInt();
                                sc.nextLine();
                                break;
                            } catch (Exception e){
                                System.err.println("Erro: " + e);
                                System.err.print("Tente novamente: ");
                                sc.next();
                            }
                        }
                        switch (optionMovie){
                            case 1:
                                System.out.println(" registrar filme");

                                System.out.println("TÍTULO: ");
                                String title = sc.nextLine();
                                System.out.println("DESCRIÇÃO: ");
                                String description = sc.nextLine();
                                System.out.println("GÊNERO: ");
                                String genre = sc.nextLine();
                                System.out.println("ANO DE LANÇAMENTO: ");
                                int year = sc.nextInt();
                                //sc.nextLine();
                                System.out.println("PREÇO");
                                double price = sc.nextDouble();

                                Movie movie = fc.createMovie(title,description,genre,year,price);
                                db.addMovie(movie);

                                System.out.println("FILME ADICIONADO AO CATÁLOGO!");
                                break;
                            case 2:
                                System.out.println(" ver filmes");

                                db.loadMovie().forEach(f -> System.out.println(f.getTitle() + " - " + f.getDescription() + " - " + f.getGenre() + " - " + f.getYear() + " - " + f.getPrice()));
                                break;
                            case 3:
                                System.out.println(" atualizar filmes");

                                System.out.print("NOME DO FILME A SE ALTERAR: ");
                                String oldTitle = sc.nextLine();
                                System.out.print("NOVO TÍTULO: ");
                                String newTitle = sc.nextLine();
                                System.out.print("NOVA DESCRIÇÃO: ");
                                String newDescription = sc.nextLine();
                                System.out.print("NOVO GÊNERO: ");
                                String newGenre = sc.nextLine();
                                System.out.print("NOVO ANO: ");
                                int newYear = sc.nextInt();
                                sc.nextLine();
                                System.out.print("NOVO PREÇO: R$");
                                double newPrice = sc.nextDouble();

                                db.updateMovie(oldTitle, newTitle, newGenre, newDescription, newYear, newPrice);
                                System.out.println("\nFILME ATUALIZADO COM SUCESSO!");
                                break;
                            case 4:
                                System.out.println("deletar filmes");
                                break;
                            case 5:
                                System.out.println("Limpar tudo");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                                break;
                        }
                    } while (optionMovie != 0);
                    break;
                case 2:
                    int optionClient;
                    do {
                        System.out.println("---MENU DE CLIENTES---");
                        System.out.println("\n1 - REGISTRAR CLIENTE\n2 - VER CLIENTES\n3 - ATUALIZAR INFO.\n4 - REMOVER CLIENTE\n5 - LIMPAR TUDO\n0 - SAIR");
                        while (true){
                            try {
                                optionClient = sc.nextInt();
                                break;
                            } catch (Exception e){
                                System.err.println("Erro: " + e);
                                System.err.print("Tente novamente: ");
                                sc.next();
                            }
                        }
                        switch (optionClient){
                            case 1:
                                System.out.println(" registrar cliente");
                                break;
                            case 2:
                                System.out.println(" ver cliente");
                                break;
                            case 3:
                                System.out.println(" atualizar cliente");
                                break;
                            case 4:
                                System.out.println("deletar cliente");
                                break;
                            case 5:
                                System.out.println("Limpar tudo");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                                break;
                        }
                    } while (optionClient != 0);
                    break;

                // PARA ALUGAR
                case 3:
                    Database db2 = Database.getInstance();
                    String movieName;
                    String clientID = "a";
                    int optionRental = 1;
                    do {
                        System.out.println("---MENU DE ALUGUEL---");
                        System.out.print("\nDIGITE O NOME DO FILME: ");
                        while (true) {
                            try {
                                movieName = sc.nextLine();
                                break;
                            } catch (Exception e) {
                                System.err.println("Erro: " + e);
                                System.err.print("Tente novamente: ");
                                sc.nextLine();
                            }
                        }
                        for (Movie movie : db2.loadMovie()) {
                            if (movie.getTitle().equals(movieName)) {
                                System.out.print("DIGITE O CPF DO CLIENTE: ");
                                clientID = sc.nextLine();
                                for (Client client : db2.loadClients()) {
                                    if (client.getId().equals(clientID)) {
                                        PriceStrategy normal = new NormalPrice();
                                        PriceStrategy blackFriday = new BFPrice();

                                        System.out.println("\n1 - PREÇO NORMAL\n2 - BLACK FRIDAY");
                                        System.out.print("\nESCOLHA A ESTRATÉGIA DE PREÇO: ");

                                        optionRental = sc.nextInt();
                                        sc.nextLine();
                                        switch (optionRental){
                                            case 1:
                                                Rental rental = new Rental(movie, normal);
                                                System.out.println("FILME ALUGADO COM SUCESSO! PREÇO NORMAL APLICADO.\n---INFO---");
                                                rental.showInfo();
                                                break;
                                            case 2:
                                                Rental rental1 = new Rental(movie, blackFriday);
                                                System.out.println("FILME ALUGADO COM SUCESSO! PREÇO BLACK FRIDAY APLICADO.\n---INFO---");
                                                rental1.showInfo();
                                                break;
                                            default:
                                                System.out.println("OPÇÃO DE PREÇO INVÁLIDA, TENTE NOVAMENTE.");
                                                break;
                                        }
                                    } else {
                                        System.out.println("FILME NÃO ENCONTRADO.");
                                    }
                                }
                            } else {
                                System.out.println("NOME INVÁLIDO OU NÃO ENCONTRADO.");
                            }
                        }

                    } while (!movieName.equals("") || !clientID.equals("") || optionRental != 0);
                default:
                    System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                    break;
            }
        } while (option != 0);
    sc.close();
    }
}
