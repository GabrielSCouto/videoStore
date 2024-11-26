package movies;

public class Horror implements Movie{

    private String title;
    private String description;
    private int year;
    private double price;

    public Horror(String title, String description, int year, double price) {
        this.title = title;
        this.description = description;
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
        return "Horror";
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
        return "";
    }

    @Override
    public Movie fromCSV(String csv) {
        return null;
    }
}
