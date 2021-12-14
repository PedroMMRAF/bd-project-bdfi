package dataStructures;

/**
 * ConcatenableQueue Abstract Data Type
 *
 * @param <E> Generic Element
 */
public interface ConcatenableQueue<E> extends Queue<E> {

    /**
     * Removes all of the elements from the specified queue and
     * inserts them at the end of the queue (in proper order).
     *
     * @param queue the queue to append
     */
    void append(ConcatenableQueue<E> queue);

}
