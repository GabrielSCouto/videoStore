package repository;

import factories.MovieFactoryConcrete;
import movies.Movie;
import clients.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database uniqueInstance;

    private final String clientsFile = "clients.csv";
    private final String moviesFile = "movies.csv";

//    private List<Client> clients;
//    private List<Movie> movies;

    private Database(){
//        clients = new ArrayList<>();
//        movies = new ArrayList<>();
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
            new File(clientsFile).createNewFile();
            new File(moviesFile).createNewFile();
        } catch (IOException e){
            System.out.println("Erro ao gerar arquivos: " + e.getMessage());
        }
    }

    // managing clients

    // saves client on file
    public void addClients(Client client){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientsFile, true))){
            writer.write(client.toString());
            writer.newLine();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR CLIENTE: " + e.getMessage());
        }
    }

    // loads clients file
    public List<Client> loadClients(){
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(clientsFile))){
            String line;
            while ((line = reader.readLine()) != null){
                clients.add(Client.fromCSV(line));
            }
        } catch (IOException e){
            System.out.println("ERRO AO CARREGAR CLIENTES: " + e.getMessage());
        }
        return clients;
    }

    // managing movies

    public void addMovie(Movie movie){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(moviesFile, true))) {
            writer.write(movie.getTitle() + "/" + movie.getDescription() + "/" + movie.getGenre() + "/" + movie.getYear() + "/" + movie.getPrice());
            writer.newLine();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR FILME: " + e.getMessage());
        }
    }
    //tdgyp

    public List<Movie> loadMovie(){
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(moviesFile))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split("/");
                movies.add(new MovieFactoryConcrete().createMovie(fields[3],fields[2],fields[1],Integer.parseInt(fields[0]),Double.parseDouble(fields[5])));
            }
        } catch (IOException e){
            System.out.println("ERRO AO CARREGAR FILMES: " + e.getMessage());
        }
        return movies;
    }


}
