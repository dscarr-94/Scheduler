import java.util.ArrayList;
/**
 * @author Dylan Carr
 * 
 * The section class represents a 30 minute period of time during the school day
 * that a teacher or student may be scheduled for a meeting, have lunch,
 * or be busy for other reasons.
 * 
 * @time Time sections are described as:
      numbers 0-13 = section from 8AM - 3PM broken into 30 minute periods
 * @busy a flag representing a section is busy.(unable to be filled)
 * @lunch a flag representing when a teacher/student has lunch.
 * @full boolean representing when a section is full.
 * @students list of students assigned to that section.
 */
public class Section {
    int time; 
    int busy;
    int lunch;
    int full; /* 1 full, 0 empty */
    ArrayList<String> students;

    /**
     * 
     * Constructor for Section class.
     */
    public Section(int time, int busy, int lunch) {
        this.time = time;
        this.busy = busy;
        this.lunch = lunch;
        this.full = 0;
        students = new ArrayList<>();
    }
}
