package Models;

public class Holiday {

    private String name;
    private String date;

    /**
     * Constructor Method for Holiday
     * @param name
     * @param date
     */
    public Holiday(String name, String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Getter and Setter Methods
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
