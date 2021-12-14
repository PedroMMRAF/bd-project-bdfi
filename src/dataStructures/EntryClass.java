package dataStructures;

/**
 * EntryClass implementation
 *
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class EntryClass<K, V> implements Entry<K, V> {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The entry's key and value
     */
    private K key;
    private V value;

    /**
     * EntryClass constructor
     *
     * @param key   the entry's key
     * @param value the entry's value
     */
    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
