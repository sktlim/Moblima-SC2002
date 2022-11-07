package Models;



public class Ticket {

    public enum UserAgeType {STUDENT, SENIOR};
    public enum DayType {WEEKDAY, WEEKEND}

    private int ticketId;
    private int showId;
    private int userId;
    private String seat;
    private UserAgeType userAgeType;
    private DayType dayType;
    private float price;

    public Ticket(int ticketId, int showId, int userId, String seat, UserAgeType userAgeType, DayType dayType, int price){
        this.ticketId = ticketId;
        this.showId = showId;
        this.userId = userId;
        this.seat = seat;
        this.userAgeType = userAgeType;
        this.dayType = dayType;
        this.price = price;
    }

    //getter setters
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

    public int getPrice(){
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

    public void setPrice(int price){
        this.price = price;
    }



}
