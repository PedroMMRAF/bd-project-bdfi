package dataStructures;

/**
 * SCHTIterator implementation
 *
 * @param <K> Generic type Key
 * @param <V> Generic type Value
 */
public class SCHTIterator<K, V> implements Iterator<Entry<K, V>> {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Original SepChainHashTable table
     */
    private final Dictionary<K, V>[] table;

    /**
     * Current chain iterator
     */
    private Iterator<Entry<K, V>> current;

    /**
     * Next chain index
     */
    private int next;

    SCHTIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        while ((current == null || !current.hasNext()) && next < table.length)
            current = table[next++].iterator();

        if (current == null)
            return false;

        return current.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        return current.next();
    }

    @Override
    public void rewind() {
        next = 0;
        current = null;
    }

}
