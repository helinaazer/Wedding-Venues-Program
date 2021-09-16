package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for Object values.
 *
 * @author Chenyu Niu
 * @version 2/25/2020
 */
public class NumericObjectArrayList extends NumericObjectList implements Copiable, Serializable {

    //fields
    private Copiable[] list;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public NumericObjectArrayList() {
        list = new Copiable[10];
        count = 0;
    }

    /**
     * Inserts the given copiable object at the given index. The index is
     * assumed to be a value between 0 and count. Existing elements are moved up
     * as needed to make room for the new value.
     *
     * @param index the index where the new value should be stored.
     * @param value the object to be stored.
     * @throws numberlist.IndexRangeException
     */
    @Override
    public void insert(int index, Copiable value) throws IndexRangeException {
        if (index >= 0 && index <= count) {
            if (list.length == count) {
                Copiable[] list2 = new Copiable[2 * list.length];
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
     * Replace the object at the given index.
     *
     * @param index the index of the element that should be replaced.
     * @param obj the object
     * @return the object that was removed.
     * @throws numberlist.IndexRangeException
     */
    public Copiable replace(int index, Copiable obj) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index >= 0 && index <= count) {
            Copiable temp = list[index];
            list[index] = obj;
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
     * @return the object that was removed.
     * @throws numberlist.IndexRangeException
     */
    @Override
    public Copiable deleteAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index >= 0 && index <= count - 1) {
            Copiable temp = list[index];
            int shift = 0;
            for (int i = index; i < list.length - 1; i++) {
                list[i] = list[i + 1];
                shift++;
            }
            list[index + shift] = null;
            count--;
            return temp;
        } else {
            throw new IndexRangeException(0, count - 1, index);
        }
    }

    /**
     * Deletes the first instance of the given object. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param obj object to delete
     */
    @Override
    public void delete(Copiable obj) {
        if (locate(obj) != -1) {
            try {
                deleteAt(locate(obj));
            } catch (IndexRangeException ex) {
                //Shouldn't happen
            }
        }
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element.
     * @return the object at that index.
     * @throws numberlist.IndexRangeException
     */
    @Override
    public Copiable getValueAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        return list[index];
    }

    /**
     * Returns the index of the first instance of the given object in the array.
     * If the object doesn't exist, -1 is returned.
     *
     * @param value the object to find in the array.
     * @return the index where the object was found, or -1 if not found.
     */
    @Override
    public int locate(Copiable value) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
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

    /**
     * Make a deepCopy of the NumericObjectArrayList object
     *
     * @return the copy of NumericObjectArrayList object
     */
    @Override
    public NumericObjectArrayList makeDeepCopy() {
        NumericObjectArrayList list = new NumericObjectArrayList();
        for (int i = 0; i < this.getCount(); i++) {
            try {
                list.insert(i, this.getValueAt(i));
            } catch (IndexRangeException ex) {
                //Shouldn't happen
            }
        }
        return list;
    }
}
