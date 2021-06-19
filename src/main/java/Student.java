import java.util.*;
/**
 * @author Dylan Carr
 * 
 * The Student class represents a student.
 * @name The name of the student.
 * @grade The grade of the student.
 * @tot_min The total number of minutes the student needs to be seen in the week
 * @groupable flag representing if the student can be grouped with others.
 *    1 yes, 0 no
 * @w_schedule A schedule of the week (5 days) for the student.
 * @group A list of other students the current student MUST be grouped with.
 * @grouped boolean representing if the student is still required to be grouped.
 */
public class Student {
    String name;
    int grade;
    int tot_min;
    int groupable;
    ArrayList<Day> w_schedule; 
    ArrayList<String> group;
    boolean grouped;

    /**
     * Constructor for the Student class. 
     * Initializes parameters and weekly schedule to according days. 
     */
    public Student(String name, int grade, int tot_min, int group) {
        this.name = name;
        this.grade = grade;
        this.tot_min = tot_min;
        this.groupable = group;
        this.w_schedule = new ArrayList<>(5);
        this.w_schedule.add(new Day("M"));
        this.w_schedule.add(new Day("T"));
        this.w_schedule.add(new Day("W"));
        this.w_schedule.add(new Day("R"));
        this.w_schedule.add(new Day("F"));   
        this.grouped = false;
        this.group = new ArrayList<>();
    }

    public void setGrouped(boolean status) {
        this.grouped = status;
    }

    public int getTotMin() {
        return this.tot_min;
    }

    /**
     * Overrides to use the student's name to compare equality.
     */
    @Override
    public boolean equals(Object o) {
        
        if(o == this)
            return true;

        if(!(o instanceof Student))
            return false;

        Student s = (Student)o;
        return this.name.equals(s.name);
    }
}


