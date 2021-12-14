package dataStructures;

/**
 * StackInList implementation
 *
 * @param <E> Generic Element
 */
public class StackInList<E> implements Stack<E> {

    /**
     * Serial Version UID of the Class.
     */
    public static final long serialVersionUID = 0L;

    /**
     * Memory of the stack: a list.
     */
    protected List<E> list;

    /**
     * StackInList constructor
     */
    public StackInList() {
        list = new DoubleList<>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public E top() throws EmptyStackException {
        if (list.isEmpty())
            throw new EmptyStackException();

        return list.getFirst();
    }

    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (list.isEmpty())
            throw new EmptyStackException();

        return list.removeFirst();
    }

}
