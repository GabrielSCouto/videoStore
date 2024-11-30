package movies;

public interface Movie {

    String getTitle();
    String getDescription();
    String getGenre();
    int getYear();
    double getPrice();

    void setTitle(String title);
    void setDescription(String description);
    void setGenre(String genre);
    void setYear(int year);
    void setPrice(double price);

    void showInfo();
    String toCSV();
    Movie fromCSV(String csv);
}
