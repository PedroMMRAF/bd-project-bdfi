package bdfi;

import dataStructures.Iterator;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
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
	 * Lists all the tags of the program
	 * 
	 * @return a iterator of tags
	 */
	Iterator<String> tagsIterator();
	
	/**
	 * @return the show's rating
	 */
	int getRating();

	/**
	 * Finishes production of the show
	 */
	void finishProduction();
	
	/**
	 * Adds a participant to the show
	 * 
	 * @param person - the participant to add
	 */
	void addParticipant(Person person);

}
