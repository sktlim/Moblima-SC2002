package Models;

public class RatingAndReview {

    private int movieId;
    private String review;
    private int rating;

    private int reviewId;

    // ** NEED AN INVALIDATING EXCEPTION CLASS TO ENSURE THAT RATINGS ARE BETWEEN 1 AND 5

    /*
    Overloaded constructor to create a new RatingandReview object in MovieManager classes with only movieId.
    Review and Ratings fields will be assigned to their default values
    and be overriden by the setter methods later on.
    */
    public RatingAndReview(int movieId) {
        this.movieId = movieId;
    }

    public RatingAndReview(int reviewId, int movieId, String review, int rating) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
    }

    //getters setters

    public int getMovieId() {
        return this.movieId;
    }

    public String getReview() {
        return this.review;
    }

    public int getRating() {
        return this.rating;
    }

    public int getReviewId() {
        return this.reviewId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

}
