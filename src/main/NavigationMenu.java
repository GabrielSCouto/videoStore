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
                        System.out.println("\n---MENU DO CATÁLOGO---");
                        System.out.println("\n1 - INSERIR FILME\n2 - VER CATÁLOGO\n3 - ATUALIZAR CATÁLOGO\n4 - REMOVER FILME\n5 - LIMPAR TUDO\n0 - SAIR");
                        System.out.print("\nSELECIONE UMA OPÇÃO: ");
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
                                System.out.println("\n---REGISTRAR FILME---\n");

                                System.out.print("TÍTULO: ");
                                String title = sc.nextLine();
                                System.out.print("DESCRIÇÃO: ");
                                String description = sc.nextLine();
                                System.out.print("GÊNERO: ");
                                String genre = sc.nextLine();
                                System.out.print("ANO DE LANÇAMENTO: ");
                                int year = sc.nextInt();
                                System.out.print("PREÇO: R$");
                                double price = sc.nextDouble();

                                Movie movie = fc.openMovie(title,description,genre,year,price);
                                db.addMovie(movie);

                                System.out.println("\nFILME ADICIONADO AO CATÁLOGO!\n");
                                break;
                            case 2:
                                System.out.println("\n---FILMES REGISTRADOS---\n");

                                db.loadMovie().forEach(f -> System.out.println("FILME: "+ f.getTitle() + " |DESCRIÇÃO: " + f.getDescription() + " |GÊNERO: " + f.getGenre() + " |LANÇAMENTO: " + f.getYear() + " |PREÇO: R$" + f.getPrice()));
                                break;
                            case 3:
                                System.out.println("\n---ATUALIZAR FILME---\n");

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

                                db.updateMovie(oldTitle, newTitle, newDescription, newGenre, newYear, newPrice);
                                System.out.println("\nFILME ATUALIZADO COM SUCESSO!\n");
                                break;
                            case 4:
                                System.out.println("\n---REMOVER FILME---\n");
                                System.out.println("Digite o título do filme a remover:");
                                String titleRemove = sc.nextLine();
                                db.removeMovie(titleRemove);
                                System.out.println("Filme removido com sucesso!");
                                break;
                            case 5:
                                System.out.println("\n---(!!!)LIMPAR TUDO(!!!)---");
                                sc.nextLine();
                                System.out.print("Tem certeza que disso? (S/N): ");
                                String confirm = sc.nextLine();
                                if (confirm.equalsIgnoreCase("s")){
                                    db.eraseAllMovies();
                                    System.out.println("Arquivo CSV filmes limpo com sucesso!");
                                    break;
                                }

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
                    Database db1 = Database.getInstance();
                    int optionClient;
                    do {
                        System.out.println("\n---MENU DE CLIENTES---");
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

                                System.out.println("\n---REGISTRAR CLIENTE---\n");

                                sc.nextLine();
                                System.out.print("NOME: ");
                                String name = sc.nextLine();
                                System.out.print("CPF: ");
                                String id = sc.nextLine();

                                Client client = new Client(name, id);
                                db1.addClients(client);

                                System.out.println("\nCLIENTE REGISTRADO COM SUCESSO!\n");
                                break;
                            case 2:
                                System.out.println(" ver cliente");
                                System.out.println("\n---CLIENTES REGISTRADOS---\n");

                                db1.loadClients().forEach(f -> System.out.println("NOME: "+ f.getName() + " |CPF: " + f.getId()));

                                break;
                            case 3:
                                System.out.println("\n---ATUALIZAR CLIENTE---");
                                sc.nextLine();
                                System.out.print("\nDigite o CPF do cliente a atualizar: ");
                                String idUpdate = sc.nextLine();
                                System.out.print("Digite o novo nome: ");
                                String newName = sc.nextLine();
                                System.out.print("Digite o novo CPF: ");
                                String newId = sc.nextLine();
                                db1.updateClient(idUpdate, newName, newId);
                                System.out.println("\nCliente atualizado com sucesso!");
                                break;
                            case 4:
                                System.out.println("\n---DELETAR CLIENTE---");
                                sc.nextLine();
                                System.out.print("Digite o CPF do cliente a remover: ");
                                String idRemove = sc.nextLine();
                                db1.removeClient(idRemove);
                                System.out.println("\nCliente removido com sucesso!");
                                break;
                            case 5:
                                System.out.println("\n---(!!!)LIMPAR TUDO(!!!)---");
                                sc.nextLine();
                                System.out.print("Tem certeza que disso? (S/N): ");
                                String confirm = sc.nextLine();
                                if (confirm.equalsIgnoreCase("s")){
                                    db1.eraseAllClients();
                                    System.out.println("Arquivo CSV clientes limpo com sucesso!");
                                    break;
                                }

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
                    String clientID;
                    int optionRental;
                    do {
                        System.out.println("---MENU DE ALUGUEL---");

                        System.out.print("DIGITE O NOME DO FILME (ou 0 para sair): ");
                        movieName = sc.nextLine();

                        if (movieName.equals("0")) break;

                        // Verificar se o filme existe no catálogo
                        Movie selectedMovie = null;
                        for (Movie movie : db2.loadMovie()) {
                            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                                selectedMovie = movie;
                                break;
                            }
                        }

                        if (selectedMovie == null) {
                            System.out.println("FILME NÃO ENCONTRADO! TENTE NOVAMENTE.");
                            continue;
                        }

                        System.out.print("DIGITE O CPF DO CLIENTE: ");
                        clientID = sc.nextLine();

                        Client selectedClient = null;
                        for (Client client : db2.loadClients()) {
                            if (client.getId().equals(clientID)) {
                                selectedClient = client;
                                break;
                            }
                        }

                        if (selectedClient == null) {
                            System.out.println("CLIENTE NÃO ENCONTRADO! TENTE NOVAMENTE.");
                            continue; // Retorna ao início do loop
                        }

                        System.out.println("\n1 - PREÇO NORMAL\n2 - BLACK FRIDAY");
                        System.out.print("\nESCOLHA A ESTRATÉGIA DE PREÇO: ");

                        while (true) {
                            try {
                                optionRental = sc.nextInt();
                                sc.nextLine(); // Consumir o caractere de nova linha
                                if (optionRental == 1 || optionRental == 2) break;
                                else System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
                            } catch (Exception e) {
                                System.err.println("Entrada inválida! Digite um número.");
                                sc.next(); // Limpar entrada inválida
                            }
                        }

                        PriceStrategy priceStrategy = optionRental == 1 ? new NormalPrice() : new BFPrice();
                        Rental rental = new Rental(selectedMovie, priceStrategy);
                        System.out.println("FILME ALUGADO COM SUCESSO!");
                        rental.showInfo();

                    } while (true);
                    break;
                default:
                    System.out.println("");
                    break;
            }
        } while (option != 0);
    sc.close();
    }
}
