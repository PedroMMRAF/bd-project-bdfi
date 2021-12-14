package dataStructures;

/**
 * BSTKeyOrderIterator implementation
 *
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The root of the original tree.
     */
    private final BSTNode<K, V> root;

    /**
     * The current element of the iterator
     */
    private BSTNode<K, V> current;

    /**
     * Stack holding the entries of the binary search tree
     */
    private Stack<BSTNode<K, V>> entries;

    BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return entries.size() > 1 || current != null;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        if (current != null) {
            while (current.getLeft() != null) {
                entries.push(current);
                current = current.getLeft();
            }
            entries.push(current);
        }

        BSTNode<K, V> result = entries.pop();
        current = result.getRight();
        return result.getEntry();
    }

    @Override
    public void rewind() {
        current = root;
        entries = new StackInList<>();
        entries.push(root);
    }

}
