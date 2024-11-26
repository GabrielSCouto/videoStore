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
            //writer.write(client.toString());
            writer.write(client.toCSV());
            writer.newLine();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR CLIENTE: " + e.getMessage());
        }
    }


    public List<Client> loadClients(){
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(clientsFile))){
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
//                String[] data = line.split("/");
//                if (data.length == 2){
//                    String name = data[0];
//                    String id = data[1];
//                    Client client = new Client(name, id);
//                    clients.add(client);
//                }
                clients.add(Client.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clients;
    }
    // MÉTODO PARA LIMPAR CSV CLIENTES

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
                movies.add(new MovieFactoryConcrete().createMovie(fields[0],fields[1],fields[2],Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
            }
        } catch (IOException e){
            System.out.println("ERRO AO CARREGAR FILMES: " + e.getMessage());
        }
        return movies;
    }


    public void updateMovie(String oldTitle, String newTitle, String newDescription, String newGenre, int newYear, double newPrice){
        List<Movie> movies = loadMovie();
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(moviesFile))){
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
    //  MÉTODO PARA LIMPAR CSV MOVIES

}
