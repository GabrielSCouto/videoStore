package movies;

public class Action implements Movie{

    private String title;
    private String description;
    private int year;
    private double price;

    public Action(String title, String description, String gender, int year, double price) {
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
        return "Action";
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
