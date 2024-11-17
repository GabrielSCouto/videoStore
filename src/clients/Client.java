package clients;

public class Client {

    private String name;
    private String id;

    public Client(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Cliente: " + name + " | CPF: " + id;
    }

    public static Client fromCSV(String line){
        String[] fields = line.split("/");
        return new Client(fields[0],fields[1]);
    }
}
