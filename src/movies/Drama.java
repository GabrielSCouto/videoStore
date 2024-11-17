package movies;

public class Drama implements Movie{

    private String title;
    private String description;
    private int year;
    private double price;

    public Drama(String title, String description, int year, double price) {
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
    public String getGender() {
        return "Drama";
    }

    @Override
    public int getYear(){
        return year;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void showInfo() {
        System.out.println("info");
    }
}