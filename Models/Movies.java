package Models;

public class Movies {

    private enum ShowingStatus{COMING_SOON, PREVIEW, NOW_SHOWING};
    private enum MovieRating{G, PG13, NC16, M18, R21};
    private enum MovieType{THREE_D, BLOCKBUSTER};
    
    private int movieId;
    private String movieTitle;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String cast;
    private int movieRuntime;
    private MovieRating movieRating;
    private MovieType movieType;





}
