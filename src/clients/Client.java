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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String toCSV() {
        return name + "/" + id;
    }

    // Creates object Client a from CSV line
    public static Client fromCSV(String csv) {
        String[] fields = csv.split("/");
        if (fields.length < 0) {
            throw new IllegalArgumentException("INVALID CSV LINE FOR CLIENT: " + csv);
        }
        String name = fields[0].trim();
        String id = fields[1].trim();
        return new Client(name, id);
    }

    @Override
    public String toString(){
        return name + "/" + id;
    }

}
