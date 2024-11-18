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
        return name + "/" + id;
    }

    public static Client fromCSV(String line){
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] fields = line.split("/");
        if (fields.length < 2) {
            System.out.println("Informações em falta: " + line);
            return null;
        }
        return new Client(fields[0],fields[1]);
    }
}
