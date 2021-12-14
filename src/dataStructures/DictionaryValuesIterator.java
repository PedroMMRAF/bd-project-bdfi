package dataStructures;

/**
 * DictionaryValuesIterator implementation
 *
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class DictionaryValuesIterator<K, V> implements Iterator<V> {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Iterator of the entries of a dictionary.
     */
    protected Iterator<Entry<K, V>> iter;

    /**
     * DictionaryValuesIterator constructor
     *
     * @param iter - Original entry iterator
     */
    public DictionaryValuesIterator(Iterator<Entry<K, V>> iter) {
        this.iter = iter;
        this.rewind();
    }

    @Override
    public void rewind() {
        iter.rewind();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public V next() throws NoSuchElementException {
        return iter.next().getValue();
    }

}
