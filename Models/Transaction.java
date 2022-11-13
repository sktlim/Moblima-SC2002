package Models;

/**
 * Transaction class consisting of Transaction ID, ticket ID, movie goer ID, and datetime.
 */
public class Transaction {
    /**
     * Transaction ID of transaction
     */
    private String TID;
    /**
     * Ticket ID associated to transaction
     */
    private int ticketId;
    /**
     * Movie Goer ID associated to transaction
     */
    private int movieGoerId;
    /**
     * Date time associated with transaction. Date time format is in YYYY-MM-DD;HH:MM
     */
    private String dateTime;

    /**
     * Constructor for Transaction
     * @param TID Transaction ID of transaction
     * @param ticketId Ticket ID associated to transaction
     * @param movieGoerId Movie Goer ID associated to transaction
     * @param dateTime Date time associated with transaction. Date time format is in YYYY-MM-DD;HH:MM
     */
    public Transaction(String TID, int ticketId, int movieGoerId, String dateTime) {
        this.TID = TID;
        this.ticketId = ticketId;
        this.movieGoerId = movieGoerId;
        this.dateTime = dateTime;
    }

    /**
     * Getter method for transaction ID
     * @return returns transaction ID of particular transaction
     */
    public String getTID() {
        return TID;
    }

    /**
     * Setter method for transaction ID
     * @param TID new transaction ID of particular transaction
     */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * Getter method for ticket ID
     * @return returns ticket ID associated to transaction
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Setter method for ticket ID
     * @param ticketId new ticket ID associated to transaction
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Getter method for Movie Goer ID
     * @return returns Movie Goer ID associated to transaction
     */
    public int getMovieGoerId() {
        return movieGoerId;
    }

    /**
     * Setter method for Movie Goer ID
     * @param movieGoerId new Movie Goer ID associated to transaction
     */
    public void setMovieGoerId(int movieGoerId) {
        this.movieGoerId = movieGoerId;
    }

    /**
     * Getter method for date time
     * @return returns date time in YYYY-MM-DD;HH:MM format
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Setter method for date time
     * @param dateTime date time to be set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
