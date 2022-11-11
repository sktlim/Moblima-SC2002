package Models;

/**
 * Ratings and review class stores the movie ID, review, and rating for a particular movie.
 * There can be multiple ratings and reviews per movie.
 */
public class RatingAndReview {

    /**
     * Movie ID of associated movie
     */
    private int movieId;
    /**
     * User review of movie stored as String
     */
    private String review;
    /**
     * User Rating of movie stored as Integer between and inclusive of 1 and 5
     */
    private int rating;
    /**
     * Review ID of associated review by user
     */
    private int reviewId;

    // ** NEED AN INVALIDATING EXCEPTION CLASS TO ENSURE THAT RATINGS ARE BETWEEN 1 AND 5

    /*
    Overloaded constructor to create a new RatingandReview object in MovieManager classes with only movieId.
    Review and Ratings fields will be assigned to their default values
    and be overriden by the setter methods later on.
    */

    /**
     * Constructor for Rating and Review
     * @param movieId Movie ID of associated movie
     */
    public RatingAndReview(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Overloaded method for Constructor of Rating and Review
     * @param reviewId Review ID of associated review by user
     * @param movieId Movie ID of associated movie
     * @param review User review of movie in String format
     * @param rating User Rating of movie as Integer between and inclusive of 1 and 5
     */
    public RatingAndReview(int reviewId, int movieId, String review, int rating) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
    }


    /**
     * Getter method for movie ID
     * @return Returns movie ID in Integer format
     */
    public int getMovieId() {
        return this.movieId;
    }

    /**
     * Getter method for review
     * @return Returns review in String format
     */
    public String getReview() {
        return this.review;
    }

    /**
     * Getter method for rating
     * @return Returns rating in Integer format
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Getter method for review ID
     * @return Returns review ID in Integer format
     */
    public int getReviewId() {
        return this.reviewId;
    }

    /**
     * Setter method for movie ID
     * @param movieId Takes associated movie ID as input
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Setter method for review
     * @param review Takes String input of review as input
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Setter method for rating
     * @param rating Takes Integer input of rating as input
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Setter method for review ID
     * @param reviewId Takes Integer input of review ID as input
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

}
