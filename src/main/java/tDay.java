import java.util.ArrayList;
/**
 * @author Dylan Carr
 * 
 * The tDay class represents a teacher's day.
 * @schedule A list of sections for the day.
 * @curStudents A list of the current students added to a section for the day.
 *    used to not add a student twice in a single day when populating.
 */
public class tDay {
        String day;
        ArrayList<Section> schedule;
        ArrayList<String> curStudents; 
        
        /**
         * Constructor for the tDay class.
         */
        public tDay(String day) {
            this.day = day;
            this.schedule = new ArrayList<>(14);
            
            /*Initializes the schedule's sections time and availability.*/
            for(int i = 0; i < 14; i++) {
                this.schedule.add(new Section(i, 0, 0));
            }
            
            this.curStudents = new ArrayList<>();
        }
    }