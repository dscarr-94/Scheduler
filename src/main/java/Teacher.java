import java.util.*;
/**
 * @author Dylan Carr
 * 
 * Teacher class for a teacher.
 * @name Name of the teacher
 * @w_schedule Weekly schedule of tDay (teacher day) objects
 */
public class Teacher {
    String name;
    ArrayList<tDay> w_schedule;

    public Teacher(String name) {
        this.name = name;
        this.w_schedule = new ArrayList<>(5);
        this.w_schedule.add(new tDay("M"));
        this.w_schedule.add(new tDay("T"));
        this.w_schedule.add(new tDay("W"));
        this.w_schedule.add(new tDay("R"));
        this.w_schedule.add(new tDay("F"));         
    }

    /**
     * Setter method for a lunch section.
     * @param section Section number of the day to be set.
     */
    public void setLunch(int section) {
        for(tDay d: this.w_schedule)
            d.schedule.get(section).lunch = 1;
    }

    /**
     * Setter method for a section for an entire week to be set.
     * @param section Section number of the day to be set.
     */
    public void setBusy(int section) {
        for(tDay d: this.w_schedule)
            d.schedule.get(section).busy = 1;
    }
    
    /**
     * Setter method for a section on a specific day to be set.
     * @param section Section number of the day to be set.
     * @param day The day for the section to be set.
     */
    public void setBusy(int section, String day) {
        switch(day) {
            case "M":
                this.w_schedule.get(0).schedule.get(section).busy = 1;
                break;
            case "T":
                this.w_schedule.get(1).schedule.get(section).busy = 1;
                break;
            case "W":
                this.w_schedule.get(2).schedule.get(section).busy = 1;
                break;
            case "R":
                this.w_schedule.get(3).schedule.get(section).busy = 1;
                break;
            case "F":
                this.w_schedule.get(4).schedule.get(section).busy = 1;
                break;
            default:
                System.out.println("Invalid day to set busy");
                break;                                    
        }
    }
}
