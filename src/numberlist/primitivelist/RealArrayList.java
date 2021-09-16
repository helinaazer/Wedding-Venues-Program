package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class extends the function in BigFloatArrayList class
 *
 * @author Chenyu Niu
 * @version 2/27/2020
 */
public class RealArrayList extends BigFloatArrayList implements Serializable {

    /**
     * Inserts the given double value at the last index.
     *
     * @param value the value to be stored.
     */
    public int insert(double value) throws IndexRangeException {
        super.insert(super.getCount(), value);
        return super.getCount();
    }

    /**
     * Deletes all instances of the value from the list
     *
     * @param value the value that need to be deleted.
     */
    public void deleteAll(double value) throws IndexRangeException {
        for (int i = 0; i < super.getCount(); i++) {
            if (super.getValueAt(i) == value) {
                super.deleteAt(i);
                i--;
            }
        }
    }

    /**
     * Returns the index of the last element in the list that contains the
     * value, or -1 if the value does not exist
     *
     * @param value the value to find in the array.
     * @return the index where the value was found, or -1 if not found.
     */
    public int locateLast(double value) throws IndexRangeException {
        for (int i = super.getCount() - 1; i >= 0; i--) {
            if (super.getValueAt(i) == value) {
                return i;
            }
        }
        return -1;
    }
}
