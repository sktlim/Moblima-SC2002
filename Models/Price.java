package Models;

/**
 * Represents the Prices in our application.
 */

public class Price {

    /**
     * This is the Standard price for Mon-Wed 2D Movies
     */
    private double Standard_2DWeekday_MonWed;

    /**
     * This is the Standard price for Thu 2D Movies
     */
    private double Standard_2DWeekday_Thu;

    /**
     * This is the Standard price for Fri 2D Movies
     */
    private double Standard_2DWeekday_Fri;

    /**
     * This is the Standard price for Weekend 2D Movies
     */
    private double Standard_2DWeekend;

    /**
     * This is the Standard price for 2D Movies on a Holiday
     */
    private double Standard_2DHoliday;

    /**
     * This is the Standard price for Mon-Wed 3D Movies
     */
    private double Standard_3DWeekday_MonWed;

    /**
     * This is the Standard price for Thu 3D Movies
     */
    private double Standard_3DWeekday_Thu;

    /**
     * This is the Standard price for Fri 3D Movies
     */
    private double Standard_3DWeekday_Fri;

    /**
     * This is the Standard price for Weekend 3D Movies
     */
    private double Standard_3DWeekend;

    /**
     * This is the Standard price for 3D Movies on a Holiday
     */
    private double Standard_3DHoliday;

    /**
     * This is the Standard price for Mon-Wed BlockBuster Movies
     */
    private double Standard_BlockBusterWeekday_MonWed;

    /**
     * This is the Standard price for Thu BlockBuster Movies
     */
    private double Standard_BlockBusterWeekday_Thu;

    /**
     * This is the Standard price for Fri BlockBuster Movies
     */
    private double Standard_BlockBusterWeekday_Fri;

    /**
     * This is the Standard price for Weekend BlockBuster Movies
     */
    private double Standard_BlockBusterWeekend;

    /**
     * This is the Standard price for BlockBuster Movies on a Holiday
     */
    private double Standard_BlockBusterHoliday;

    /**
     * This is the Platinum price for Mon-Wed 2D Movies
     */
    private double Platinum_2DWeekday_MonWed;

    /**
     * This is the Platinum price for Thu 2D Movies
     */
    private double Platinum_2DWeekday_Thu;

    /**
     * This is the Platinum price for Fri 2D Movies
     */
    private double Platinum_2DWeekday_Fri;

    /**
     * This is the Platinum price for Weekend 2D Movies
     */
    private double Platinum_2DWeekend;

    /**
     * This is the Platinum price for 2D Movies on a Holiday
     */
    private double Platinum_2DHoliday;

    /**
     * This is the Platinum price for Mon-Wed 3D Movies
     */
    private double Platinum_3DWeekday_MonWed;

    /**
     * This is the Platinum price for Thu 3D Movies
     */
    private double Platinum_3DWeekday_Thu;

    /**
     * This is the Platinum price for Fri 3D Movies
     */
    private double Platinum_3DWeekday_Fri;

    /**
     * This is the Platinum price for Weekend 3D Movies
     */
    private double Platinum_3DWeekend;

    /**
     * This is the Platinum price for 3D Movies on a Hoiday
     */
    private double Platinum_3DHoliday;

    /**
     * This is the Platinum price for Mon-Wed BlockBuster Movies
     */
    private double Platinum_BlockBusterWeekday_MonWed;

    /**
     * This is the Platinum price for Thu BlockBuster Movies
     */
    private double Platinum_BlockBusterWeekday_Thu;

    /**
     * This is the Platinum price for Fri BlockBuster Movies
     */
    private double Platinum_BlockBusterWeekday_Fri;

    /**
     * This is the Platinum price for Weekend BlockBuster Movies
     */
    private double Platinum_BlockBusterWeekend;

    /**
     * This is the Platinum price for BlockBuster Movies on a Holiday
     */
    private double Platinum_BlockBusterHoliday;

    /**
     * This is a fixed student price adhering to the following conditions:
     * Mon-Fri before 6pm
     * Standard Movie
     * 2D
     */
    private double student;

    /**
     * This is a fixed senior citizen price adhering to the following conditions:
     * Mon-Fri before 6pm
     * Standard Movie
     * 2D
     */
    private double seniorCitizen;

    /**
     * Constructor for Price
     */
    public Price() {
    }

    /**
     * Getter and Setter Methods
     */

    /**
     * Gets the price for Standard Mon-Wed 2D Movies
     * @return the price for Standard Mon-Wed 2D Movies
     */
    public double getStandard_2DWeekday_MonWed() {
        return Standard_2DWeekday_MonWed;
    }

    /**
     * Modifies the price for Standard Mon-Wed 2D Movies
     * @param standard_2DWeekday_MonWed
     */
    public void setStandard_2DWeekday_MonWed(double standard_2DWeekday_MonWed) {
        Standard_2DWeekday_MonWed = standard_2DWeekday_MonWed;
    }

    /**
     * Gets the price for Standard Thu 2D Movies
     * @return the price for Standard Thu 2D Movies
     */
    public double getStandard_2DWeekday_Thu() {
        return Standard_2DWeekday_Thu;
    }

    /**
     * Modifies the price for Standard Thu 2D Movies
     * @param standard_2DWeekday_Thu
     */
    public void setStandard_2DWeekday_Thu(double standard_2DWeekday_Thu) {
        Standard_2DWeekday_Thu = standard_2DWeekday_Thu;
    }

    /**
     * Gets the price for Standard Fri 2D Movies
     * @return the price for Standard Fri 2D Movies
     */
    public double getStandard_2DWeekday_Fri() {
        return Standard_2DWeekday_Fri;
    }

    /**
     * Modifies the price for Standard Fri 2D Movies
     * @param standard_2DWeekday_Fri
     */
    public void setStandard_2DWeekday_Fri(double standard_2DWeekday_Fri) {
        Standard_2DWeekday_Fri = standard_2DWeekday_Fri;
    }

    /**
     * Gets the price for Standard Weekend 2D Movies
     * @return the price for Standard Weekend 2D Movies
     */
    public double getStandard_2DWeekend() {
        return Standard_2DWeekend;
    }

    /**
     * Modifies the price for Standard Weekend 2D Movies
     * @param standard_2DWeekend
     */
    public void setStandard_2DWeekend(double standard_2DWeekend) {
        Standard_2DWeekend = standard_2DWeekend;
    }

    /**
     * Gets the price for Standard 2D Movies on a Holiday
     * @return the price for Standard 2D Movies on a Holiday
     */
    public double getStandard_2DHoliday() {
        return Standard_2DHoliday;
    }

    /**
     * Modifies the price for Standard 2D Movies on a Holiday
     * @param standard_2DHoliday
     */
    public void setStandard_2DHoliday(double standard_2DHoliday) {
        Standard_2DHoliday = standard_2DHoliday;
    }

    /**
     * Gets the price for Standard Mon-Wed 3D Movies
     * @return the price for Standard Mon-Wed 3D Movies
     */
    public double getStandard_3DWeekday_MonWed() {
        return Standard_3DWeekday_MonWed;
    }

    /**
     * Modifies the price for Standard Mon-Wed 3D Movies
     * @param standard_3DWeekday_MonWed
     */
    public void setStandard_3DWeekday_MonWed(double standard_3DWeekday_MonWed) {
        Standard_3DWeekday_MonWed = standard_3DWeekday_MonWed;
    }

    /**
     * Gets the price for Standard Thu 3D Movies
     * @return the price for Standard Thu 3D Movies
     */
    public double getStandard_3DWeekday_Thu() {
        return Standard_3DWeekday_Thu;
    }

    /**
     * Modifies the price for Standard Thu 3D Movies
     * @param standard_3DWeekday_Thu
     */
    public void setStandard_3DWeekday_Thu(double standard_3DWeekday_Thu) {
        Standard_3DWeekday_Thu = standard_3DWeekday_Thu;
    }

    /**
     * Gets the price for Standard Fri 3D Movies
     * @return the price for Standard Fri 3D Movies
     */
    public double getStandard_3DWeekday_Fri() {
        return Standard_3DWeekday_Fri;
    }

    /**
     * Modifies the price for Standard Fri 3D Movies
     * @param standard_3DWeekday_Fri
     */
    public void setStandard_3DWeekday_Fri(double standard_3DWeekday_Fri) {
        Standard_3DWeekday_Fri = standard_3DWeekday_Fri;
    }

    /**
     * Gets the price for Standard Weekend 3D Movies
     * @return the price for Standard Weekend 3D Movies
     */
    public double getStandard_3DWeekend() {
        return Standard_3DWeekend;
    }

    /**
     * Modifies the price for Standard Weekend 3D Movies
     * @param standard_3DWeekend
     */
    public void setStandard_3DWeekend(double standard_3DWeekend) {
        Standard_3DWeekend = standard_3DWeekend;
    }

    /**
     * Gets the price for Standard 3D Movies on a Holiday
     * @return the price for Standard 3D Movies on a Holiday
     */
    public double getStandard_3DHoliday() {
        return Standard_3DHoliday;
    }

    /**
     * Modifies the price for Standard 3D Movies on a Holiday
     * @param standard_3DHoliday
     */
    public void setStandard_3DHoliday(double standard_3DHoliday) {
        Standard_3DHoliday = standard_3DHoliday;
    }

    /**
     * Gets the price for Standard Mon-Wed BlockBuster Movies
     * @return the price for Standard Mon-Wed BlockBuster Movies
     */
    public double getStandard_BlockBusterWeekday_MonWed() {
        return Standard_BlockBusterWeekday_MonWed;
    }

    /**
     * Modifies the price for Standard Mon-Wed BlockBuster Movies
     * @param standard_BlockBusterWeekday_MonWed
     */
    public void setStandard_BlockBusterWeekday_MonWed(double standard_BlockBusterWeekday_MonWed) {
        Standard_BlockBusterWeekday_MonWed = standard_BlockBusterWeekday_MonWed;
    }

    /**
     * Gets the price for Standard Thu BlockBuster Movies
     * @return the price for Standard Thu BlockBuster Movies
     */
    public double getStandard_BlockBusterWeekday_Thu() {
        return Standard_BlockBusterWeekday_Thu;
    }

    /**
     * Modifies the price for Standard Thu BlockBuster Movies
     * @param standard_BlockBusterWeekday_Thu
     */
    public void setStandard_BlockBusterWeekday_Thu(double standard_BlockBusterWeekday_Thu) {
        Standard_BlockBusterWeekday_Thu = standard_BlockBusterWeekday_Thu;
    }

    /**
     * Gets the price for Standard Fri BlockBuster Movies
     * @return the price for Standard Fri BlockBuster Movies
     */
    public double getStandard_BlockBusterWeekday_Fri() {
        return Standard_BlockBusterWeekday_Fri;
    }

    /**
     * Modifies the price for Standard Fri BlockBuster Movies
     * @param standard_BlockBusterWeekday_Fri
     */
    public void setStandard_BlockBusterWeekday_Fri(double standard_BlockBusterWeekday_Fri) {
        Standard_BlockBusterWeekday_Fri = standard_BlockBusterWeekday_Fri;
    }

    /**
     * Gets the price for Standard Weekend BlockBuster Movies
     * @return the price for Standard Weekend BlockBuster Movies
     */
    public double getStandard_BlockBusterWeekend() {
        return Standard_BlockBusterWeekend;
    }

    /**
     * Modifies the price for Standard Weekend BlockBuster Movies
     * @param standard_BlockBusterWeekend
     */
    public void setStandard_BlockBusterWeekend(double standard_BlockBusterWeekend) {
        Standard_BlockBusterWeekend = standard_BlockBusterWeekend;
    }

    /**
     * Gets the price for Standard BlockBuster Movies on a Holiday
     * @return the price for Standard BlockBuster Movies on a Holiday
     */
    public double getStandard_BlockBusterHoliday() {
        return Standard_BlockBusterHoliday;
    }

    /**
     * Modifies the price for Standard BlockBuster Movies on a Holiday
     * @param standard_BlockBusterHoliday
     */
    public void setStandard_BlockBusterHoliday(double standard_BlockBusterHoliday) {
        Standard_BlockBusterHoliday = standard_BlockBusterHoliday;
    }

    /**
     * Gets the price for Platinum Mon-Wed 2D Movies
     * @return the price for Platinum Mon-Wed 2D Movies
     */
    public double getPlatinum_2DWeekday_MonWed() {
        return Platinum_2DWeekday_MonWed;
    }

    /**
     * Modifies the price for Platinum Mon-Wed 2D Movies
     * @param platinum_2DWeekday_MonWed
     */
    public void setPlatinum_2DWeekday_MonWed(double platinum_2DWeekday_MonWed) {
        Platinum_2DWeekday_MonWed = platinum_2DWeekday_MonWed;
    }

    /**
     * Gets the price for Platinum Thu 2D Movies
     * @return the price for Platinum Thu 2D Movies
     */
    public double getPlatinum_2DWeekday_Thu() {
        return Platinum_2DWeekday_Thu;
    }

    /**
     * Modifies the price for Platinum Thu 2D Movies
     * @param platinum_2DWeekday_Thu
     */
    public void setPlatinum_2DWeekday_Thu(double platinum_2DWeekday_Thu) {
        Platinum_2DWeekday_Thu = platinum_2DWeekday_Thu;
    }

    /**
     * Gets the price for Platinum Fri 2D Movies
     * @return the price for Platinum Fri 2D Movies
     */
    public double getPlatinum_2DWeekday_Fri() {
        return Platinum_2DWeekday_Fri;
    }

    /**
     * Modifies the price for Platinum Fri 2D Movies
     * @param platinum_2DWeekday_Fri
     */
    public void setPlatinum_2DWeekday_Fri(double platinum_2DWeekday_Fri) {
        Platinum_2DWeekday_Fri = platinum_2DWeekday_Fri;
    }

    /**
     * Gets the price for Platinum Weekend 2D Movies
     * @return the price for Platinum Weekend 2D Movies
     */
    public double getPlatinum_2DWeekend() {
        return Platinum_2DWeekend;
    }

    /**
     * Modifies the price for Platinum Weekend 2D Movies
     * @param platinum_2DWeekend
     */
    public void setPlatinum_2DWeekend(double platinum_2DWeekend) {
        Platinum_2DWeekend = platinum_2DWeekend;
    }

    /**
     * Gets the price for Platinum 2D Movies on a Holiday
     * @return the price for Platinum 2D Movies on a Holiday
     */
    public double getPlatinum_2DHoliday() {
        return Platinum_2DHoliday;
    }

    /**
     * Modifies the price for Platinum 2D Movies on a Holiday
     * @param platinum_2DHoliday
     */
    public void setPlatinum_2DHoliday(double platinum_2DHoliday) {
        Platinum_2DHoliday = platinum_2DHoliday;
    }

    /**
     * Gets the price for Platinum Mon-Wed 3D Movies
     * @return the price for Platinum Mon-Wed 3D Movies
     */
    public double getPlatinum_3DWeekday_MonWed() {
        return Platinum_3DWeekday_MonWed;
    }

    /**
     * Modifies the price for Platinum Mon-Wed 3D Movies
     * @param platinum_3DWeekday_MonWed
     */
    public void setPlatinum_3DWeekday_MonWed(double platinum_3DWeekday_MonWed) {
        Platinum_3DWeekday_MonWed = platinum_3DWeekday_MonWed;
    }

    /**
     * Gets the price for Platinum Thu 3D Movies
     * @return the price for Platinum Thu 3D Movies
     */
    public double getPlatinum_3DWeekday_Thu() {
        return Platinum_3DWeekday_Thu;
    }

    /**
     * Modifies the price for Platinum Thu 3D Movies
     * @param platinum_3DWeekday_Thu
     */
    public void setPlatinum_3DWeekday_Thu(double platinum_3DWeekday_Thu) {
        Platinum_3DWeekday_Thu = platinum_3DWeekday_Thu;
    }

    /**
     * Gets the price for Platinum Fri 3D Movies
     * @return the price for Platinum Fri 3D Movies
     */
    public double getPlatinum_3DWeekday_Fri() {
        return Platinum_3DWeekday_Fri;
    }

    /**
     * Modifies the price for Platinum Fri 3D Movies
     * @param platinum_3DWeekday_Fri
     */
    public void setPlatinum_3DWeekday_Fri(double platinum_3DWeekday_Fri) {
        Platinum_3DWeekday_Fri = platinum_3DWeekday_Fri;
    }

    /**
     * Gets the price for Platinum Weekend 3D Movies
     * @return the price for Platinum Weekend 3D Movies
     */
    public double getPlatinum_3DWeekend() {
        return Platinum_3DWeekend;
    }

    /**
     * Modifies the price for Platinum Weekend 3D Movies
     * @param platinum_3DWeekend
     */
    public void setPlatinum_3DWeekend(double platinum_3DWeekend) {
        Platinum_3DWeekend = platinum_3DWeekend;
    }

    /**
     * Gets the price for Platinum 3D Movies on a Holiday
     * @return the price for Platinum 3D Movies on a Holiday
     */
    public double getPlatinum_3DHoliday() {
        return Platinum_3DHoliday;
    }

    /**
     * Modifies the price for Platinum 3D Movies on a Holiday
     * @param platinum_3DHoliday
     */
    public void setPlatinum_3DHoliday(double platinum_3DHoliday) {
        Platinum_3DHoliday = platinum_3DHoliday;
    }

    /**
     * Gets the price for Platinum Mon-Wed BlockBuster Movies
     * @return the price for Platinum Mon-Wed BlockBuster Movies
     */
    public double getPlatinum_BlockBusterWeekday_MonWed() {
        return Platinum_BlockBusterWeekday_MonWed;
    }

    /**
     * Modifies the price for Platinum Mon-Wed BlockBuster Movies
     * @param platinum_BlockBusterWeekday_MonWed
     */
    public void setPlatinum_BlockBusterWeekday_MonWed(double platinum_BlockBusterWeekday_MonWed) {
        Platinum_BlockBusterWeekday_MonWed = platinum_BlockBusterWeekday_MonWed;
    }

    /**
     * Gets the price for Platinum Thu BlockBuster Movies
     * @return the price for Platinum Thu BlockBuster Movies
     */
    public double getPlatinum_BlockBusterWeekday_Thu() {
        return Platinum_BlockBusterWeekday_Thu;
    }

    /**
     * Modifies the price for Platinum Thu BlockBuster Movies
     * @param platinum_BlockBusterWeekday_Thu
     */
    public void setPlatinum_BlockBusterWeekday_Thu(double platinum_BlockBusterWeekday_Thu) {
        Platinum_BlockBusterWeekday_Thu = platinum_BlockBusterWeekday_Thu;
    }

    /**
     * Gets the price for Platinum Fri BlockBuster Movies
     * @return the price for Platinum Fri BlockBuster Movies
     */
    public double getPlatinum_BlockBusterWeekday_Fri() {
        return Platinum_BlockBusterWeekday_Fri;
    }

    /**
     * Modifies the price for Platinum Fri BlockBuster Movies
     * @param platinum_BlockBusterWeekday_Fri
     */
    public void setPlatinum_BlockBusterWeekday_Fri(double platinum_BlockBusterWeekday_Fri) {
        Platinum_BlockBusterWeekday_Fri = platinum_BlockBusterWeekday_Fri;
    }

    /**
     * Gets the price for Platinum Weekend BlockBuster Movies
     * @return the price for Platinum Weekend BlockBuster Movies
     */
    public double getPlatinum_BlockBusterWeekend() {
        return Platinum_BlockBusterWeekend;
    }

    /**
     * Modifies the price for Platinum Weekend BlockBuster Movies
     * @param platinum_BlockBusterWeekend
     */
    public void setPlatinum_BlockBusterWeekend(double platinum_BlockBusterWeekend) {
        Platinum_BlockBusterWeekend = platinum_BlockBusterWeekend;
    }

    /**
     * Gets the price for Platinum BlockBuster Movies on a Holiday
     * @return the price for Platinum BlockBuster Movies on a Holiday
     */
    public double getPlatinum_BlockBusterHoliday() {
        return Platinum_BlockBusterHoliday;
    }

    /**
     * Modifies the price for Platinum BlockBuster Movies on a Holiday
     * @param platinum_BlockBusterHoliday
     */
    public void setPlatinum_BlockBusterHoliday(double platinum_BlockBusterHoliday) {
        Platinum_BlockBusterHoliday = platinum_BlockBusterHoliday;
    }

    /**
     * Gets the price for Students
     * @return the price for Students
     */
    public double getStudent() {
        return student;
    }

    /**
     * Modifies the price for Students
     * @param student
     */
    public void setStudent(double student) {
        this.student = student;
    }

    /**
     * Gets the price for Senior Citizens
     * @return the price for Senior Citizens
     */
    public double getSeniorCitizen() {
        return seniorCitizen;
    }

    /**
     * Modifies the price for Senior Citizens
     * @param seniorCitizen
     */
    public void setSeniorCitizen(double seniorCitizen) {
        this.seniorCitizen = seniorCitizen;
    }
}
