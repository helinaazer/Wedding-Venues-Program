package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for primitive long values.
 *
 * @author Chenyu Niu
 * @version 2/25/2020
 */
public class BigIntArrayList implements Serializable {

    //fields
    private long[] list;
    private int count;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public BigIntArrayList() {
        list = new long[10];
        count = 0;
    }

    /**
     * Inserts the given long value at the given index. The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored.
     * @param value the value to be stored.
     */
    public void insert(int index, long value) throws IndexRangeException {
        if (index >= 0 && index <= count) {
            if (list.length == count) {
                long[] list2 = new long[2 * list.length];
                for (int k = 0; k < list.length; k++) {
                    list2[k] = list[k];
                }
                list = list2;
            }
            for (int i = count; i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = value;
            count++;
        } else {
            throw new IndexRangeException(0, count, index);
        }
    }

    /**
     * Replace the value at the given index.
     *
     * @param index the index of the element that should be replaced.
     * @return the value that was removed.
     */
    public long replace(int index, long value) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index >= 0 && index <= count) {
            long temp = list[index];
            list[index] = value;
            return temp;
        } else {
            throw new IndexRangeException(0, count - 1, index);
        }
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed.
     * @return the value that was removed.
     */
    public long deleteAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index >= 0 && index <= count - 1) {
            long temp = list[index];
            int shift = 0;
            for (int i = index; i < list.length - 1; i++) {
                list[i] = list[i + 1];
                shift++;
            }
            list[index + shift] = 0l;
            count--;
            return temp;
        } else {
            throw new IndexRangeException(0, count - 1, index);
        }
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove.
     */
    public void delete(long value) {
        if (locate(value) != -1) {
            int i = locate(value);
            for (i = locate(value); i < list.length - 1; i++) {
                list[i] = list[i + 1];
            }
            list[count - 1] = 0;
            count--;
        }
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element.
     * @return the value at that index.
     */
    public long getValueAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index >= 0 && index <= count - 1) {
            long value = list[index];
            return value;
        } else {
            throw new IndexRangeException(0, count - 1, index);
        }
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array.
     * @return the index where the value was found, or -1 if not found.
     */
    public int locate(long value) {
        int index = -1;
        for (int i = 0; i < list.length && index < 0; i++) {
            if (list[i] == value) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array.
     */
    public int getCount() {
        return count;
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
        for (int i = 0; i < count; i++) {
            output += list[i] + ", ";
        }
        if (count > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }

}
