package dataStructures;

/**
 * ConcatenableQueueInList implementation
 *
 * @param <E> Generic Element
 */
public class ConcatenableQueueInList<E> extends QueueInList<E> implements ConcatenableQueue<E> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    @Override
    public void append(ConcatenableQueue<E> queue) {
        if (queue instanceof ConcatenableQueueInList<?>) {
            ConcatenableQueueInList<E> other = (ConcatenableQueueInList<E>) queue;
            ((DoubleList<E>) this.list).append((DoubleList<E>) other.list);
        }
        else {
            while (!queue.isEmpty())
                enqueue(queue.dequeue());
        }
    }

}
