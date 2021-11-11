package bdfi;

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
	 * @return the person's email
	 */
	String getEmail();

	/**
	 * @return the person's phone number
	 */
	String getPhone();

	/**
	 * @return the person's gender
	 */
	Gender getGender();

	/**
	 * @return the person's birth year
	 */
	int getBirthYear();	
	
}
