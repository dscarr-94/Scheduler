import java.util.Comparator;
/**
 *
 * @author Dylan Carr
 * Class is used  to sort students by total number of minutes
 * they need to be seen.
 */
public class sortByTotMin implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.tot_min - s2.tot_min;
    }

    /*
    Collections.sort(Database.arrayList, new Comparator<MyObject>() {
       @Override
       public int compare(Student s1, Student s2) {
           return o1.getStartDate().compareTo(o2.getStartDate());
       }
    });
    */
}
