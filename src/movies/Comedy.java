package movies;

public class Comedy implements Movie{

    private String title;
    private String description;
    private String genre;
    private int year;
    private double price;

    public Comedy(String title, String description, String genre, int year, double price) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.price = price;
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getGenre() {
        return "Comedy";
    }

    @Override
    public int getYear(){
        return year;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setTitle(String title) {}

    @Override
    public void setDescription(String description) {}

    @Override
    public void setGenre(String genre) {}

    @Override
    public void setYear(int year) {}

    @Override
    public void setPrice(double price) {}

    @Override
    public void showInfo() {
        System.out.println("info");
    }

    @Override
    public String toCSV() {
        return title + "/" + description + "/" + genre + "/" + year + "/" + price;
    }

    @Override
    public Movie fromCSV(String csv) {
        String[] fields = csv.split("/");
        if (fields.length != 5) {
            throw new IllegalArgumentException("Linha CSV inválida para filme: " + csv);
        }
        String title = fields[0].trim();
        String description = fields[1].trim();
        String genre = fields[2].trim();
        int year = Integer.parseInt(fields[3].trim());
        double price = Double.parseDouble(fields[4].trim());

        return new Comedy(title, description, genre, year, price);
    }
}
