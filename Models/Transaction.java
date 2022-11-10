package Models;

public class Transaction {

    private String TID;
    private int ticketId;
    private int movieGoerId;
    private String dateTime;

    /**
     * Constructor for Transaction
     * @param TID
     * @param ticketId
     * @param movieGoerId
     * @param dateTime
     */
    public Transaction(String TID, int ticketId, int movieGoerId, String dateTime) {
        this.TID = TID;
        this.ticketId = ticketId;
        this.movieGoerId = movieGoerId;
        this.dateTime = dateTime;
    }

    /**
     * Getter and Setter Methods
     * @return
     */
    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getMovieGoerId() {
        return movieGoerId;
    }

    public void setMovieGoerId(int movieGoerId) {
        this.movieGoerId = movieGoerId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
