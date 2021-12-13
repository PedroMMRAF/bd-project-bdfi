package dataStructures;


public class DictionaryValuesIterator<K,V> implements Iterator<V> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Iterator of the entries of a dictionary.
	 */
	protected Iterator<Entry<K, V>> it;


	/**
	 * DictionaryValuesIterator constructor
	 *
	 * @param iter - Original entry iterator
	 */
	public DictionaryValuesIterator(Iterator<Entry<K, V>> iter) {
		it = iter;
		this.rewind();
	}


	@Override
	public void rewind() {
		it.rewind();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}


	@Override
	public V next() throws NoSuchElementException {
		return it.next().getValue();

		
	}


}
