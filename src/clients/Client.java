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

    // Converte o objeto para o formato CSV
    public String toCSV() {
        return name + "/" + id;
    }

    // Cria um objeto Client a partir de uma linha CSV
    public static Client fromCSV(String csv) {
        String[] fields = csv.split("/");
        if (fields.length != 2) {
            throw new IllegalArgumentException("Linha CSV inválida para Client: " + csv);
        }
        String nome = fields[0].trim();
        String id = fields[1].trim();
        return new Client(nome, id);
    }

    @Override
    public String toString(){
        return name + "/" + id;
    }

//    public static Client fromCSV(String line){
//        if (line == null || line.trim().isEmpty()) {
//            return null;
//        }
//        String[] fields = line.split("/");
//        if (fields.length < 2) {
//            System.out.println("Informações em falta: " + line);
//            return null;
//        }
//        return new Client(fields[0],fields[1]);
//    }
}
