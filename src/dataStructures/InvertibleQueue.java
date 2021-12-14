package dataStructures;

/**
 * InvertibleQueue Abstract Data Type
 *
 * @param <E> Generic Element
 */
public interface InvertibleQueue<E> extends Queue<E> {

    /**
     * Puts all elements in the queue in the opposite order.
     */
    void invert();

}
