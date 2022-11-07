package Models;
import java.time.LocalDate;

public class Holiday {

    private String date;

    public Holiday(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
