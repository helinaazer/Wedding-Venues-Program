package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This is an abstract class of NumericObject
 *
 * @author Chenyu Niu
 * @version 2/25/2020
 */
abstract class NumericObjectList implements Serializable {

    int count;

    /**
     * Inserts the given object at the given index. The index is assumed to be a
     * value between 0 and count. Existing elements are moved up as needed to
     * make room for the new value.
     *
     * @param index the index where the new value should be stored.
     * @param value the object to be stored.
     */
    abstract void insert(int index, Copiable obj) throws IndexRangeException;

    /**
     * inserts at the end of the list and return the index it was inserted at.
     *
     * @param value the object to be stored.
     */
    public int insert(Copiable obj) throws IndexRangeException {
        this.insert(count, obj);
        return count - 1;
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed.
     * @return the object that was removed.
     */
    abstract Copiable deleteAt(int index) throws IndexRangeException;

    /**
     * Deletes the first instance of the given object. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the object to remove.
     */
    abstract void delete(Copiable obj);

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element.
     * @return the object at that index.
     */
    abstract Copiable getValueAt(int index) throws IndexRangeException;

    /**
     * Returns the index of the first instance of the given object in the array.
     * If the object doesn't exist, -1 is returned.
     *
     * @param value the object to find in the array.
     * @return the index where the object was found, or -1 if not found.
     */
    abstract int locate(Copiable obj);

    /**
     * Replace the object at the given index.
     *
     * @param index the index of the element that should be replaced.
     * @return the object that was removed.
     */
    abstract Copiable replace(int index, Copiable obj) throws IndexRangeException;

    /**
     * Getter for count
     *
     * @return the integer quantity of items in list.
     */
    public int getCount() {
        return count;
    }

}
