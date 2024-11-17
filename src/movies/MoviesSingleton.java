package movies;

public class MoviesSingleton {

    private static MoviesSingleton uniqueInstance;

    private MoviesSingleton(){

    }

    public static synchronized MoviesSingleton getInstance(){

        if (uniqueInstance == null){
            uniqueInstance = new MoviesSingleton();
        }
        return uniqueInstance;
    }
}
