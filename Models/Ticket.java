package Models;



public class Ticket {

    public enum UserAgeType {STUDENT, SENIOR, ADULT};
    public enum DayType {WEEKDAY, WEEKEND, DEFAULT}

    private int ticketId;
    private int showId;
    private int userId;
    private String seat;
    private UserAgeType userAgeType;
    private DayType dayType;
    private double price;

    /**
     * Constructor for ticket
     * @param ticketId
     * @param showId
     * @param userId
     * @param seat
     * @param userAgeType
     * @param dayType
     * @param price
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
     * Getter and Setter Methods
      * @return
     */
    public int getTicketId(){
        return this.ticketId;
    }

    public int getShowId(){
        return this.showId;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getSeat(){
        return this.seat;
    }

    public UserAgeType getUserAgeType(){
        return this.userAgeType;
    }

    public DayType getDayType(){
        return this.dayType;
    }

    public double getPrice(){
        return this.price;
    }

    public void setTicketId(int ticketId){
        this.ticketId = ticketId;
    }

    public void setShowId(int showId){
        this.showId = showId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setSeat(String seat){
        this.seat = seat;
    }

    public void setUserAgeType(UserAgeType userAgeType){
        this.userAgeType = userAgeType;
    }

    public void setDayType(DayType dayType){
        this.dayType = dayType;
    }

    public void setPrice(double price){
        this.price = price;
    }

}
