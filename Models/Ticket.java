package Models;


/**
 * Ticket class stores related information of particular tickets such as ticket ID, show ID, user ID, seat, user age type, type of day and price.
 */
public class Ticket {
    /**
     * Custom Type for User Age Type. Consists of STUDENT, SENIOR and ADULT
     */
    public enum UserAgeType {STUDENT, SENIOR, ADULT};

    /**
     * Custom Type for Day Type. Consists of WEEKDAY, WEEKEND and DEFAULT
     */
    public enum DayType {WEEKDAY, WEEKEND, DEFAULT}

    /**
     * ticket ID of ticket
     */
    private int ticketId;
    /**
     * Associated show ID of ticket
     */
    private int showId;
    /**
     * Associated user ID of ticket
     */
    private int userId;
    /**
     * Associated seat of ticket
     */
    private String seat;
    /**
     * User Age Type of ticket
     */
    private UserAgeType userAgeType;
    /**
     * Day type of ticket
     */
    private DayType dayType;
    /**
     * Price of ticket
     */
    private double price;

    /**
     * Constructor for ticket
     * @param ticketId ticket ID of ticket
     * @param showId Associated show ID of ticket
     * @param userId Associated user ID of ticket
     * @param seat Associated seat of ticket
     * @param userAgeType User Age Type of ticket
     * @param dayType Day type of ticket
     * @param price Price of ticket
     */
    public Ticket(int ticketId, int showId, int userId, String seat, UserAgeType userAgeType, DayType dayType, double price){
        this.ticketId = ticketId;
        this.showId = showId;
        this.userId = userId;
        this.seat = seat;
        this.userAgeType = userAgeType;
        this.dayType = dayType;
        this.price = price;
    }

    /**
     * Getter method for ticket ID
      * @return returns ticket ID of ticket
     */
    public int getTicketId(){
        return this.ticketId;
    }

    /**
     * Getter method for show ID
     * @return returns show ID of ticket
     */
    public int getShowId(){
        return this.showId;
    }

    /**
     * Getter method for user ID
     * @return returns user ID of ticket
     */
    public int getUserId(){
        return this.userId;
    }

    /**
     * Getter method for seat
     * @return returns seat of ticket
     */
    public String getSeat(){
        return this.seat;
    }

    /**
     * Getter method for user age type
     * @return returns user age type associated to ticket
     */
    public UserAgeType getUserAgeType(){
        return this.userAgeType;
    }

    /**
     * Getter method for day type
     * @return returns day type associated to ticket
     */
    public DayType getDayType(){
        return this.dayType;
    }

    /**
     * Getter method for price
     * @return returns price of ticket
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Setter method for ticket ID
     * @param ticketId ticket ID of ticket
     */
    public void setTicketId(int ticketId){
        this.ticketId = ticketId;
    }

    /**
     * Setter method for show ID
     * @param showId show ID of ticket
     */
    public void setShowId(int showId){
        this.showId = showId;
    }

    /**
     * Setter method for user ID
     * @param userId user ID of ticket
     */
    public void setUserId(int userId){
        this.userId = userId;
    }

    /**
     * Setter method for seat
     * @param seat Seat associated with ticket
     */
    public void setSeat(String seat){
        this.seat = seat;
    }

    /**
     * Setter method for User Age Type
     * @param userAgeType Enumerated User Age type. Consists of STUDENT, SENIOR and ADULT
     */
    public void setUserAgeType(UserAgeType userAgeType){
        this.userAgeType = userAgeType;
    }

    /**
     * Setter method for Day Type
     * @param dayType Enumerated Day Type. Consists of WEEKDAY, WEEKEND and DEFAULT
     */
    public void setDayType(DayType dayType){
        this.dayType = dayType;
    }

    /**
     * Setter method for price
     * @param price Price of ticket
     */
    public void setPrice(double price){
        this.price = price;
    }

}
