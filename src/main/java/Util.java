/**
 * @author Dylan Carr
 * 
 *  Utility class for conversions.
 */
public class Util {
        
    /**
     * Converts sections into string representations for time slots during a day
     * and returns them.
     */
    public static String convertSecToTime(int section) {
        
        switch(section) {
            case(0) -> {
                return "8:00-8:30";
            }
            case(1) -> {
                return "8:30-9:00";
            }
            case(2) -> {
                return "9:00-9:30";
            }
            case(3) -> {
                return "9:30-10:00";
            }
            case(4) -> {
                return "10:00-10:30";
            }
            case(5) -> {
                return "10:30-11:00";
            }
            case(6) -> {
                return "11:00-11:30";
            }
            case(7) -> {
                return "11:30-12:00";
            }
            case(8) -> {
                return "12:00-12:30";
            }
            case(9) -> {
                return "12:30-1:00";
            }
            case(10) -> {
                return "1:00-1:30";
            }
            case(11) -> {
                return "1:30-2:00";
            }
            case(12) -> {
                return "2:00-2:30";
            }
            case(13) -> {
                return "2:30-3:00";
            }
            default -> {
                System.out.println("Invalid conversion of section to time");
                return "Failed to convert section to time";     
            }
        }
    }
    
    /**
     * Converts input format days to full names of each day and returns it.
     */
    public static String convertDay(String day) {
        
        return switch (day) {
            case "M" -> "Monday";
            case "T" -> "Tuesday";
            case "W" -> "Wednesday";
            case "R" -> "Thursday";
            case "F" -> "Friday";
            default -> "Invalid day to print: " + day;
        };
    }
}
