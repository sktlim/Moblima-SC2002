package Models;

public class RatingAndReview {

    private int movieId;
    private String review;
    private int rating;

    public RatingAndReview(int movieId, String review, int rating){
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
    }

    //getters setters

    public int getMovieId(){
        return this.movieId;
    }

    public String getReview(){
        return this.review;
    }

    public int getRating(){
        return this.rating;
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    public void setReview(String review){
        this.review = review;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
    
}
