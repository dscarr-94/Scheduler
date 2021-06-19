/**
 * @author Dylan Carr
 * May 17, 2021
 * This program reads in student and teachers schedules and fills the teachers
 * schedules according to the provided input files.
 * Constraints include students that:
 *    can/can't be grouped
 *    must be grouped with specific students
 *    total amount of time a student must be seen
 *    both teachers and students current conflicts due to other classes,
         meetings, lunches, breaks, etc.
 * User instructions including input file format can be found in the README.
 */
import java.io.*;
import java.util.*;

public class Scheduler {
    
    /* Global student and teacher arrays */
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    
    /* Main function to intitate program */
    public static void main(String[] args) {
        Scheduler s = new Scheduler();
        s.run();  
    }
    
    /* Runs the program reading the inputs, populating the schedules, printing 
     * the results, and running tests to make sure all students were scheduled
     * properly.
     */
    public void run() {
            readFile("schedule.txt");   
            students.sort(Comparator.comparing(Student::getTotMin));
            Comparator c = Collections.reverseOrder(new sortByTotMin());
            Collections.sort(students, c);

            fillFromInput();
            fillSchedule();
            printTeachers();
            runTests();
        }
    
    /**
     * Reads the input file and calls the populateStudent function for each Student
     */
    public void readFile(String filename) {
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                int num_students = 0;

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    populateStudent(data);
                    num_students++;
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException.");
        }
    }   
    
    /**
     * Populates the students weekly schedule based on input availability.
     * @param data Input file format for a single student to be parsed.
     */
    public void populateStudent(String data) {
        String[] tokens = data.split(",");
        Student s = new Student(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        int size = tokens.length;
        int index;
        String name;
        //remaining tokens for students DAY (w_schedule)
        for(int i = 4; i <= size-1; i+=2) {
            
            switch(tokens[i].toUpperCase()) {
                case "M":
                    index = Integer.parseInt(tokens[i+1]);; 
                    s.w_schedule.get(0).schedule.set(index, 1);
                    break;
                case "T":
                    index = Integer.parseInt(tokens[i+1]);
                    s.w_schedule.get(1).schedule.set(index, 1);
                    break;
                case "W":
                    index = Integer.parseInt(tokens[i+1]);
                    s.w_schedule.get(2).schedule.set(index, 1);
                    break;
                case "R":
                    index = Integer.parseInt(tokens[i+1]);
                    s.w_schedule.get(3).schedule.set(index, 1);
                    break;
                case "F":
                    index = Integer.parseInt(tokens[i+1]);
                    s.w_schedule.get(4).schedule.set(index, 1);
                    break;
                case "G":
                    name = tokens[i+1];
                    s.group.add(name);  //add students name to current studets group list
                    s.setGrouped(true);
                    break;
                    
                default:
                    System.out.println("Invalid Day: " + tokens[i].toUpperCase() + " use M, T, W, R, F)");
            }
        }
        students.add(s);
    }
    
    /**
     * Prints student Schedules.
     * @param s Student
     */
    public void printStudentSchedule(Student s) {
        
        for(Day d: s.w_schedule) {
            System.out.println("day: " + d.day);
            System.out.println(d.schedule.toString());
        }
    }
    
    /**
     * Prints group a student is in.
     * @param s Student
     */
    public void printStudentGroup(Student s) {
        System.out.println("Grouped with:");
        for(String str: s.group) {
            System.out.println("   " + str);
        }
    }
    
    /**
     * Prints students information.
     */
    public void printStudents() {
        for(Student s: students) {
            System.out.println("student name: " + s.name);
            System.out.println("grade level: " + s.grade); 
            System.out.println("total_minutes: " + s.tot_min);
            System.out.println("allowed to group[yes 1/no 0]: " + s.groupable);
            printStudentSchedule(s);
            if(s.grouped)
                printStudentGroup(s);
        }
    }
   
    /**
     * Prints out the teachers schedules.
     */
    public void printTeachers() {
        for(Teacher t: teachers) {
            System.out.println();
            System.out.println(t.name);
            for(tDay d: t.w_schedule) {
                System.out.println(Util.convertDay(d.day));
                for(Section sec: d.schedule) {
                    System.out.println(Util.convertSecToTime(sec.time));
                    if(sec.busy == 1) {
                        System.out.println("   Busy");
                    }
                    else if(sec.lunch == 1) {
                        System.out.println("   Lunch");
                    }
                    else if(!sec.students.isEmpty()) {
                        for(String s: sec.students) {
                            System.out.println("   " + s);
                        }
                    }
                    else {
                        System.out.println("   Open");
                    }
                }
                System.out.println();
            }
        }
    }
    
    /**
     * Adds teacher to teachers list, hard coded exceptions for both teachers
     * used in the program.
     * @param name Name of the teacher
     */
    public void addTeacher(String name) {
        Teacher t = new Teacher(name);
        teachers.add(t);
        if(name.equals("Ms. Alex")) {
            t.setLunch(9);
            for(tDay d: t.w_schedule) {
                for(Section sec: d.schedule) {
                    if(d.day.equals("F")) { /* Friday */
                        t.setBusy(sec.time, d.day);
                    }
                    else { /* Monday-Thursday */
                        if(sec.time < 7) {
                            t.setBusy(sec.time, d.day);
                        }
                    }
                }
            }
        }
        if(name.equals("Ms. Gina")) {
            t.setLunch(9);
            for(tDay d: t.w_schedule) {
                for(Section sec: d.schedule) {
                    if(d.day.equals("F")) {
                        if(sec.time<6)
                            t.setBusy(sec.time, d.day);
                    }
                    else {
                        if(sec.time < 7)
                            t.setBusy(sec.time, d.day);
                        else if(sec.time == 13 || sec.time == 8)
                            t.setBusy(sec.time, d.day);
                    }
                }
            }
        }
    }
    
    /**
     * Main algorithm for the program. Fills the teachers schedules according to
     * all constraints provided. 
     */
    public void fillSchedule() {
    Comparator c = Collections.reverseOrder(new sortByTotMin());

    for(Teacher t: teachers) {
        for(tDay d: t.w_schedule) {
            int i, index;            
            Collections.sort(students, c);

            for(i = 0; i < 14; i++) { 
                Section sec = d.schedule.get(i);
                if(sec.busy == 0 && sec.lunch == 0 && sec.full == 0) {

                    for(Student s: students) {
                        if(sec.busy == 0 && sec.lunch == 0 && sec.full == 0) {
                            if(s.tot_min != 0) {// have time left to fill
                                //add student to section
                                if(d.curStudents.contains(s.name) == false) { //if student not already added that day
                                    if(s.grouped) {
                                        for(String sName: s.group) {
                                            index = students.indexOf(new Student(sName,0,0,0));

                                            if(index > 0) {
                                                if(students.get(index).tot_min > 0) {
                                                    //Ready to add to section
                                                    d.schedule.get(i).students.add(sName);
                                                    d.curStudents.add(sName);
                                                    students.get(index).tot_min -= 30;
                                                }
                                            }
                                            else
                                                System.out.println("Student in group doesn't exist");
                                        }
                                    }
                                    d.schedule.get(i).students.add(s.name);
                                    d.curStudents.add(s.name);
                                    s.tot_min -= 30;
                                    d.schedule.get(i).full = 1;
                                }    
                            }
                        }
                    }
                }
            }     
        }
    }
    }

    /**
     * Manually adds Special Education teachers names used in scheduler program.
     */
    public void fillFromInput() {
        addTeacher("Ms. Gina");
        addTeacher("Ms. Alex");
    }
    
    /**
     * Basic tests and asserts for Scheduler
     * Tests that all students were filled properly. 
     */
    public void runTests() {
        Student test_true = new Student("Gideon", 0, 0, 0);
        Student test_false = new Student("Dylan", 0, 0, 0);

        assert(students.contains(test_true));
        assert(!students.contains(test_false));

        if(checkAllFilled() < 0)
            System.out.println("FAILED TO FILL ALL STUDENTS");
        else
            System.out.println("ALL STUDENTS FILLED SUCCESSFULLY!");
    }
    
    /**
     * Checks that all students were filled and outputs any unfilled minutes.
     * @return 1 if all filled correctly, -1 otherwise.
     */
    public int checkAllFilled() {

    int allFilled = 1;
    for(Student s: students) {
        if(s.tot_min > 0) {
            System.out.println(s.name + " has " + s.tot_min + " minutes left to fill.");
            allFilled = -1;
        }
    }
    return allFilled;

    }
}
    