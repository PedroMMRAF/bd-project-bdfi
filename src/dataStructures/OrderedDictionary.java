package dataStructures;

/**
 * Ordered Dictionary interface
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED Team
 * @version 1.0
 */
public interface OrderedDictionary<K extends Comparable<K>, V> extends Dictionary<K, V> {

    /**
     * Returns the entry with the smallest key in the dictionary.
     *
     * @throws EmptyDictionaryException if the dictionary is empty
     */
    Entry<K, V> minEntry() throws EmptyDictionaryException;

    /**
     * Returns the entry with the largest key in the dictionary.
     *
     * @throws EmptyDictionaryException if the dictionary is empty
     */
    Entry<K, V> maxEntry() throws EmptyDictionaryException;

}
