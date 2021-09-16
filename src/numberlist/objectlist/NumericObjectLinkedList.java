package numberlist.objectlist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable linked list for Object values.
 *
 * @author Chenyu Niu
 * @version 3/1/2020
 */
class NumericObjectLinkedList extends NumericObjectList implements Copiable, Serializable {

    private Node firstNode;

    /**
     * Constructor for NumericObjectLinkedList
     */
    public NumericObjectLinkedList() {
        count = 0;
    }

    /**
     * Inserts the given copiable object at the given index. The index is
     * assumed to be a value between 0 and count. Existing elements are moved up
     * as needed to make room for the new value.
     *
     * @param index the index where the new value should be stored.
     * @param value the object to be stored.
     */
    @Override
    public void insert(int index, Copiable obj) throws IndexRangeException {
        if (index < 0 || index > count) {
            throw new IndexRangeException(0, count, index);
        }
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
        count++;
    }

    /**
     * Replace the object at the given index.
     *
     * @param index the index of the element that should be replaced.
     * @return the object that was removed.
     */
    @Override
    public Copiable replace(int index, Copiable obj) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        Node newNode = new Node(obj);
        if (index == 0) {
            Node temp = firstNode;
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            return temp.getValue();
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }
            Copiable temp = currentNode.getValue();
            currentNode.setValue(obj);
            return temp;
        }
    }

    /**
     * Deletes the value at the given index. The index is assumed to be a value
     * between 0 and count - 1. Existing elements are moved down as needed to
     * keep all values contiguous, without any empty spaces in the array.
     *
     * @param index the index of the element that should be removed.
     * @return the object that was removed.
     */
    @Override
    public Copiable deleteAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        if (index == 0) {
            Node nextNode = firstNode.getNextNode();
            firstNode.setNextNode(null);
            count--;
            Node tempNode = firstNode;
            firstNode = nextNode;
            return tempNode.getValue();
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            Node nextCurrentNode = currentNode.getNextNode();
            currentNode.setNextNode(nextCurrentNode.getNextNode());
            count--;
            return nextCurrentNode.getValue();
        }
    }

    /**
     * Deletes the first instance of the given object. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the object to remove.
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
     */
    @Override
    public Copiable getValueAt(int index) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        Node currentNode = firstNode;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getValue();
    }

    /**
     * Returns the index of the first instance of the given object in the array.
     * If the object doesn't exist, -1 is returned.
     *
     * @param value the object to find in the array.
     * @return the index where the object was found, or -1 if not found.
     */
    @Override
    public int locate(Copiable obj) {
        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            if (currentNode.getValue().equals(obj)) {
                return i;
            } else {
                currentNode = currentNode.getNextNode();
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
        Node currentNode = firstNode;
        while (currentNode != null && currentNode.getValue() != null) {
            output += (currentNode.getValue() + ", ");
            currentNode = currentNode.getNextNode();
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
     * Make a deepCopy of the NumericObjectLinkedList object
     *
     * @return the copy of NumericObjectLinkedList object
     */
    public NumericObjectLinkedList makeDeepCopy() {
        NumericObjectLinkedList list = new NumericObjectLinkedList();
        Node node = firstNode;
        for (int i = 0; i < count; i++) {
            try {
                list.insert(node.getValue().makeDeepCopy());
            } catch (IndexRangeException ex) {
                //Shouldn't happen
            }
            node = node.getNextNode();
        }
        return list;
    }

}
