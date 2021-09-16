package numberlist.objectlist;

/**
 * This class is a object class for date
 *
 * @author Chenyu Niu
 * @version 3/10/2020
 */
public class Date implements Comparable<Date>, Copiable {

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /**
     * Compare two date object
     *
     * @param other the other object to subtract from this one.
     * @return 0 if same, negative if smaller, positive if larger
     */
    @Override
    public int compareTo(Date other) {
    if(getYear() == other.getYear()) {
        if (getMonth() == other.getMonth()) {
            return getDay() - other.getDay ();
        } else {
            return getMonth() - other.getMonth ();
        }
    } else {
        return getYear() - other.getYear();
    }
}

    @Override
    public Copiable makeDeepCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
     @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

}
