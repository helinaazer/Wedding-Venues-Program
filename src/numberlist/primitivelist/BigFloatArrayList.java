package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class use the functions from BigIntArrayList.
 *
 * @author Chenyu Niu
 * @version 2/27/2020
 */
public class BigFloatArrayList implements Serializable {

    private BigIntArrayList intArrayList;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public BigFloatArrayList() {
        intArrayList = new BigIntArrayList();
    }

    /**
     * Inserts the given long value at the given index. The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored.
     * @param value the value to be stored.
     * @throws numberlist.IndexRangeException
     */
    public void insert(int index, double value) throws IndexRangeException {
        intArrayList.insert(index, Double.doubleToRawLongBits(value));
    }

    /**
     * Replace the value at the given index.
     *
     * @param index the index of the element that should be replaced.
     * @param value the value to replace
     * @return the value that was removed.
     * @throws numberlist.IndexRangeException
     */
    public double replace(int index, double value) throws IndexRangeException {
        long lValue = Double.doubleToRawLongBits(value);
        return Double.longBitsToDouble(intArrayList.replace(index, lValue));
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed.
     * @return the value that was removed.
     * @throws numberlist.IndexRangeException
     */
    public double deleteAt(int index) throws IndexRangeException {
        return Double.longBitsToDouble(intArrayList.deleteAt(index));
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove.
     */
    public void delete(double value) {
        intArrayList.delete(Double.doubleToRawLongBits(value));
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element.
     * @return the value at that index.
     * @throws numberlist.IndexRangeException
     */
    public double getValueAt(int index) throws IndexRangeException {
        return Double.longBitsToDouble(intArrayList.getValueAt(index));
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array.
     * @return the index where the value was found, or -1 if not found.
     */
    public int locate(double value) {
        return intArrayList.locate(Double.doubleToRawLongBits(value));
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array.
     */
    public int getCount() {
        return intArrayList.getCount();
    }

    /**
     * Provides a string representation of the growable array, displaying all
     * values currently in the array using the format [ value1, value2, ... ].
     *
     * @return the string representation of the array.
     */
    @Override
    public String toString() {
        String output = "[ ";
        for (int i = 0; i < intArrayList.getCount(); i++) {
            try {
                output += Double.longBitsToDouble(intArrayList.getValueAt(i))
                        + ", ";
            } catch (IndexRangeException ex) {
                //Shouldn't happen
            }
        }
        if (intArrayList.getCount() > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }
}
