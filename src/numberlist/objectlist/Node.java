package numberlist.objectlist;

/**
 * The Data class for node object
 *
 * @author Chenyu Niu
 */
class Node {

    private Node nextNode;
    private Copiable value;

    /**
     * Constructor of Node class
     */
    public Node(Copiable value) {
        this.value = value;
        nextNode = null;
    }

    /**
     * Get the value from a linked list
     *
     * @return copiable object
     */
    public Copiable getValue() {
        return value;
    }

    /**
     * Set a value of a node object
     */
    public void setValue(Copiable value) {
        this.value = value;
    }

    /**
     * Get the next node object
     *
     * @return next Node
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Set the current object to the next
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
