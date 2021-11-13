package bdfi;

import dataStructures.Iterator;

public interface Show {

	/**
	 * @return the show's unique identifier
	 */
	String getId();

	/**
	 * @return the show's title
	 */
	String getTitle();

	/**
	 * @return the year the show was produced on
	 */
	int getProductionYear();

	/**
	 * @return <code>true</code> if the show is still being produced,
	 *         <code>false</code> otherwise
	 */
	boolean isProducing();

	/**
	 * Finishes production of the show
	 */
	void finishProduction();
	
	/**
	 * Lists all the tags of the program 
	 * @return a iterator of tags
	 */
	Iterator<String> tagsIterator();

}
