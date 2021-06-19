import java.util.ArrayList;
/**
 * @author Dylan Carr
 * 
 * A class representing a standard day during the school week.
 * @schedule current  list of availability of sections throughout a day, 
      0 - free, and 1 - busy.
 * @day - name of the day.
 */
public class Day {
    String day;
    ArrayList<Integer> schedule;

    /**
     * Constructor for the Day class.
     * Initializes the schedule into 14 - 30 minute chunks starting as open.
     * 8 am - 3pm : 30 min each 
     */
    public Day(String day) {
        this.day = day;
        this.schedule = new ArrayList<>(14); 
        for(int i = 0; i < 14; i++) {
            this.schedule.add(0);
        }
    }  
}
