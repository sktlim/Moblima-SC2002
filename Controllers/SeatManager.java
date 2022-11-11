package Controllers;
import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SeatManager {
    private int[][] seatPlan;
    public final static String FILE_FIXED_PLAN = "Databases/seatingPlanTheatre.txt";
    public final static String FILE_SHOW_PLAN = "Databases/seatingPlanShow.txt";
    public static final String SEPARATOR = "|";

    public static void readSeatPlan(int showId) throws Exception {
        // display available seats
        // also prints labels for x-y axis for user selection
            List show_plans = read(FILE_SHOW_PLAN);
            HashMap show_plan = null;
            for (int i=0; i<show_plans.size(); i++) {
                if ((int)((HashMap)show_plans.get(i)).get("showId") == showId)
                    show_plan = (HashMap)show_plans.get(i);
            }
            if (show_plan == null) throw new Exception("Show plan does not exist!");
            String [][][] plan = (String[][][]) show_plan.get("seatingPlan");
            // print top labels
            int rows = (int)show_plan.get("rows");
            int cols = (int)show_plan.get("cols");
            StringBuilder numLabels = new StringBuilder("           ");
            for (int i=0; i<cols; i++) {
                if (i == cols / 2) numLabels.append("     ");
                if (i < 9) numLabels.append(" " + (i + 1) + "  ");
                else numLabels.append(" " + (i + 1) + " ");
            }
            numLabels.append("           ");
            System.out.println(numLabels);
            // Print every row
            for (int i=0; i<rows; i++) {
                char label = (char) (i + 65);
                System.out.print(" " + label + " | | | | "); // side aisle
                for (int j = 0; j < cols; j++) {
                    String avail = plan[i][j][0];
                    String seatType = plan[i][j][1];
                    // Hardcoded main aisle
                    if (j == cols / 2) System.out.print(" | | ");

                    if (seatType.equals("NULL")) System.out.print("[*]");
                    else if (avail.equals("0") && seatType.equals("SINGLE")) System.out.print("[0]");
                    else if (avail.equals("1") && seatType.equals("SINGLE")) System.out.print("[X]");
                    else if (avail.equals("0") && seatType.equals("COUPLEL")) System.out.print("[0 ");
                    else if (avail.equals("1") && seatType.equals("COUPLEL")) System.out.print("[X ");
                    else if (avail.equals("0") && seatType.equals("COUPLER")) System.out.print(" 0]");
                    else if (avail.equals("1") && seatType.equals("COUPLER")) System.out.print(" X]");
                    System.out.print(" ");
                }
                System.out.print(" | | | | " + label + "\n"); // side aisle
            }

            // Print legend
            System.out.println("Legend");
            System.out.println("   *    : no seat");
            System.out.println("  | |   : stairs/aisle");
            System.out.println("  [ ]   : single seat");
            System.out.println(" [    ] : couple seat");
            System.out.println("   0    : seat is available");
            System.out.println("   1    : seat is taken");
    }

    public static String getSeatType(int showId, String seat) throws Exception {
        List show_plans = read(FILE_SHOW_PLAN);
        HashMap show_plan = null;
        for (int i = 0; i < show_plans.size(); i++) {
            if ((int) ((HashMap) show_plans.get(i)).get("showId") == showId)
                show_plan = (HashMap) show_plans.get(i);
        }
        if (show_plan == null) throw new IllegalArgumentException("Show plan does not exist!");
        String[][][] plan = (String[][][]) show_plan.get("seatingPlan");

        // To add validation whether seat exists on plan!
        int row = seat.charAt(0)-65;
        int col = Integer.parseInt(seat.substring(1))-1;
        if (row >= (int)show_plan.get("rows") || col >= (int)show_plan.get("cols")) {
            throw new IllegalArgumentException("Seat number does not exist on seat plan!");
        }
        return plan[row][col][1];
    }

    public static int isSeatAvail(int showId, String seat) throws Exception {
        //check seat, if seat avail, return true, else return false

        List show_plans = read(FILE_SHOW_PLAN);
        HashMap show_plan = null;
        for (int i = 0; i < show_plans.size(); i++) {
            if ((int) ((HashMap) show_plans.get(i)).get("showId") == showId)
                show_plan = (HashMap) show_plans.get(i);
        }
        if (show_plan == null) throw new IllegalArgumentException("Show plan does not exist!");
        String[][][] plan = (String[][][]) show_plan.get("seatingPlan");

        // To add validation whether seat exists on plan!
        int row = seat.charAt(0)-65;
        int col = Integer.parseInt(seat.substring(1))-1;
        if (row >= (int)show_plan.get("rows") || col >= (int)show_plan.get("cols")) {
            throw new IllegalArgumentException("Seat number does not exist on seat plan!");
        }
        if (plan[row][col][1].equals("NULL")) throw new IllegalArgumentException("Seat does not exist at position!");
        if (plan[row][col][0].equals("0")) {
            return 1;
        } else if(plan[row][col][0].equals("1")) {
            return 0;
        } else return -1;
    }

    public static void createShowPlan(int showId) {
        // creates empty seat plan related to show based on theatre id
        // Must pass theatreId since is called in constructor of show
        try {
            Show show = ShowManager.findShow(showId);
            int tid = show.getTheatre();
            List show_plans = read(FILE_SHOW_PLAN);
            for (int i=0; i<show_plans.size(); i++) {
                if ((int)((HashMap)show_plans.get(i)).get("showId") == showId)
                    throw new Exception("Show plan already exists!");
            }

            HashMap new_show = new HashMap();
            new_show.put("showId", showId);
            List fixed_plans = read(FILE_FIXED_PLAN);
            HashMap plan = null;
            for (int i=0; i<fixed_plans.size(); i++) {
                // for fixed plan, showId is actually theatre id
                if ((int)((HashMap)fixed_plans.get(i)).get("showId") == tid)
                    plan = (HashMap)fixed_plans.get(i);
            }
            if (plan == null) throw new Exception("plan for show's theatre does not exist!");
            new_show.put("rows", (int)plan.get("rows"));
            new_show.put("cols", (int)plan.get("cols"));
            new_show.put("seatingPlan", plan.get("seatingPlan"));
            show_plans.add(new_show);
            saveShowPlans(FILE_SHOW_PLAN, show_plans);
            System.out.println("Show Plan created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean updateSeatPlan(int showId, String seat, int option) {
        try {
            List plans = read(FILE_SHOW_PLAN);
            HashMap show = null;
            for (int i=0; i < plans.size(); i++) {
                HashMap curr = (HashMap) plans.get(i);
                if ((int)curr.get("showId") == showId) {
                    show = curr;
                }
            }
            if (show == null) throw new Exception("Show does not exist!");
            String[][][] plan = (String [][][]) show.get("seatingPlan");

            int row = seat.charAt(0)-65;
            int col = Integer.parseInt(seat.substring(1))-1;
            if (plan[row][col][1].equals("NULL")) throw new Exception("Seat does not exist at position!");
            plan[row][col][0] = Integer.toString(option);
            // write plan into new file
            saveShowPlans(FILE_SHOW_PLAN, plans);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private static List read(String fileName) throws IOException {
        // [ {showId: 1, rows: 9, cols: 16, seatingPlan: String[][][] }, ... ]
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                HashMap<String, Object> showSeating = new HashMap<String,Object>();
                if (line.charAt(0) == '!') {
                    line = line.replace("!", "");
                    String props[] = line.split(",");
                    int rows = Integer.parseInt(props[1]);
                    int cols = Integer.parseInt(props[2]);
                    showSeating.put("showId", Integer.parseInt(props[0]));
                    showSeating.put("rows", rows);
                    showSeating.put("cols", cols);

                    String seatingPlan[][][] = new String[rows][cols][2];
                    for (int i=0; i<rows; i++) {
                        String seatRow = scanner.nextLine();
                        String[] seats = seatRow.split("\\|");
                        for (int j=0; j<seats.length; j++) {
                            String[] seat = seats[j].split("\\/");
                            seatingPlan[i][j][0] = seat[0]; // 0/1
                            seatingPlan[i][j][1] = seat[1]; // SINGLE/COUPLEL
                        }
                    }
                    showSeating.put("seatingPlan", seatingPlan);
                }
                data.add(showSeating);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            scanner.close();
        }
        return data;
    }

    private static void saveShowPlans(String fileName, List al) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i=0; i<al.size(); i++) {   // for every show
                HashMap show = (HashMap) al.get(i);
                String[][][] plan = (String [][][]) show.get("seatingPlan");
                String output = "!" + show.get("showId") + "," + Integer.toString((int)show.get("rows"))+ "," + Integer.toString((int)show.get("cols"));
                out.println(output);

                for (int j=0; j<(int)show.get("rows"); j++) {
                    StringBuilder row = new StringBuilder();
                    for (int k=0; k<(int)show.get("cols"); k++) {
                        row.append(plan[j][k][0] + "/" + plan[j][k][1] + "|");
                    }
                    row.deleteCharAt(row.length() - 1);
                    out.println(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            out.close();
        }
    }
}
