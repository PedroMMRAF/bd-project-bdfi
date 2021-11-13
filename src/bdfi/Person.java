package bdfi;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
public interface Person {

	/**
	 * @return the person's id
	 */
	String getId();

	/**
	 * @return the person's name
	 */
	String getName();

	/**
	 * @return the person's birth year
	 */
	int getBirthYear();

	/**
	 * @return the person's gender
	 */
	String getGender();

	/**
	 * @return the person's email
	 */
	String getEmail();

	/**
	 * @return the person's phone number
	 */
	String getPhone();
	
	/**
	 * TODO: DISCUTIR IMPLEMENTACAO
	 * 
	 * @return a iterator with all the person's shows
	 */
	Show showsIterator();
	
	/**
	 * Adds a show where the person participate
	 * @param show
	 */
	void addShow(Show show);

}
