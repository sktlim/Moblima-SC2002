package Models;

/**
 * Represents a Holiday in our application.
 * The holidays used are in accordance with Singapore's Holidays
 * We note that the same Holidays can occur on different dates for different years.
 * As such, we tag a year to each Holiday Name for greater clarity.
 */

public class Holiday {

    /**
     * The name of the Holiday
     */
    private String name;

    /**
     * The date of the Holiday in the format YYYY-MM-DD
     */
    private String date;

    /**
     * Constructor Method for Holiday
     * Allows the Admin to create new Holiday instances
     * @param name
     * @param date
     */
    public Holiday(String name, String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Getter and Setter Methods
     */

    /**
     * Returns the name of the Holiday
     * @return This Holiday's name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the Holiday
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the date of the Holiday
     * @return This Holiday's date in the format YYYY-MM-DD
     */
    public String getDate() {
        return date;
    }

    /**
     * Modifies the date of the Holiday
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
