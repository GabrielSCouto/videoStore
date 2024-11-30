package repository;

import factories.MovieFactoryConcrete;
import movies.Movie;
import clients.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database uniqueInstance;

    private final String CLIENTS_FILE = "clients.csv";
    private final String MOVIES_FILE = "movies.csv";


    private Database(){
        initializeFiles();
    }

    public static synchronized Database getInstance(){

        if (uniqueInstance == null){
            uniqueInstance = new Database();
        }
        return uniqueInstance;
    }

    // initializes csv files
    private void initializeFiles(){
        try{
            new File(CLIENTS_FILE).createNewFile();
            new File(MOVIES_FILE).createNewFile();
        } catch (IOException e){
            System.out.println("Erro ao gerar arquivos: " + e.getMessage());
        }
    }

    // CLIENTS

    public void addClients(Client client){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE, true))){
            writer.write(client.toCSV());
            writer.newLine();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR CLIENTE: " + e.getMessage());
        }
    }


    public List<Client> loadClients(){
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTS_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                clients.add(Client.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clients;
    }

    public void updateClient(String oldId, String newName, String newId) {
        List<Client> clients = loadClients();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE))) {
            for (Client client : clients) {
                if (client.getId().equalsIgnoreCase(oldId)) {
                    client.setName(newName);
                    client.setId(newId);
                }
                writer.write(client.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void removeClient(String id) {
        List<Client> clients = loadClients();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE))) {
            for (Client client : clients) {
                if (!client.getId().equalsIgnoreCase(id)) {
                    writer.write(client.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    public void eraseAllClients() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE))) {
        } catch (IOException e) {
            System.err.println("Erro ao limpar clientes: " + e.getMessage());
        }
    }

    //MOVIES

    public void addMovie(Movie movie){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE, true))) {
            writer.write(movie.getTitle() + "/" + movie.getDescription() + "/" + movie.getGenre() + "/" + movie.getYear() + "/" + movie.getPrice());
            writer.newLine();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR FILME: " + e.getMessage());
        }
    }


    public List<Movie> loadMovie(){
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split("/");
                movies.add(new MovieFactoryConcrete().createMovie(fields[0],fields[1],fields[2],Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
            }
        } catch (IOException e){
            System.out.println("ERRO AO CARREGAR FILMES: " + e.getMessage());
        }
        return movies;
    }

    public void updateMovie(String oldTitle, String newTitle, String newDescription, String newGenre, int newYear, double newPrice){
        List<Movie> movies = loadMovie();
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(MOVIES_FILE))){
            for (Movie movie : movies){
                if (movie.getTitle().equalsIgnoreCase(oldTitle)) {
                    movie.setTitle(newTitle);
                    movie.setDescription(newDescription);
                    movie.setGenre(newGenre);
                    movie.setYear(newYear);
                    movie.setPrice(newPrice);
                }
                writer.write(movie.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro atualizar cliente " + e.getMessage());
        }
    }

    public void removeMovie(String title) {
        List<Movie> filmes = loadMovie();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE))) {
            for (Movie filme : filmes) {
                if (!filme.getTitle().equalsIgnoreCase(title)) {
                    writer.write(filme.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover filme: " + e.getMessage());
        }
    }

    public void eraseAllMovies() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE))) {
        } catch (IOException e) {
            System.err.println("Erro ao limpar catálogo: " + e.getMessage());
        }
    }
}
