package numberlist;

/**
 *
 * @author Chenyu Niu
 * @version 3/1/2020
 */
public class IndexRangeException extends Exception {

    private int minAllowed;
    private int maxAllowed;
    private int valueUsed;

    /**
     * Constructor of IndexRangeException class
     *
     * @param minAllowed the minimum index allowed
     * @param maxAllowed the maximum index allowed
     * @param valueUsed the value used
     */
    public IndexRangeException(int minAllowed, int maxAllowed, int valueUsed) {
        super("IndexRangeException: Index is out of range!");
        this.maxAllowed = maxAllowed;
        this.minAllowed = minAllowed;
        this.valueUsed = valueUsed;
    }

    /**
     * Get the value for minimum index allowed
     *
     * @return minimum value
     */
    public int getMinAllowed() {
        return minAllowed;
    }

    /**
     * Get the value for maximum index allowed
     *
     * @return maximum value
     */
    public int getMaxAllowed() {
        return maxAllowed;
    }

    /**
     * Get the value for the index of user chose
     *
     * @return value user chose
     */
    public int getValueUsed() {
        return valueUsed;
    }

}
