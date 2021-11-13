package bdfi;

import bdfi.exceptions.*;


public interface BDFI {

	/**
	 * Insertion of a professional in the system
	 * 
	 * @param id     - is the only form of identification of the professional
	 * @param bYear  - is the BirthYear's of the professional
	 * @param email  - of the professional
	 * @param phone  - of the professional
	 * @param gender - of the professional
	 * @param name   - could be a artistic or professional name
	 * @throws InvalidYearException if the type of the year is not an integer.
	 */
	void addPerson(String id, int bYear, String email, String phone, Gender gender, String name)
			throws InvalidYearException, InvalidGenderException;

	/**
	 * Insertion of a program in the system
	 * 
	 * @param idShow - is the identifier of the new program added to the system
	 * @param pYear  - is the year of production of the program
	 * @param title  - is the title of the program
	 * @throws InvalidYearException if the year of production is invalid.
	 */
	void addShow(String idShow, int pYear, String title) throws InvalidYearException;

	/**
	 * Add to a program the information about a participation of some professional.
	 * 
	 * @param idPerson    - is the only form of identification of the professional
	 * @param IdShow      - is the identifier of the new program added to the system
	 * @param description - ???
	 * @throws IdPersonDoesNotExistException if the idPerson don't exist
	 * @throws IdShowDoesNotExistException   if the idShow don't exist
	 */
	void addParticipation(String idPerson, String IdShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException;

	/**
	 * 
	 * @param idShow - is the identifier of the new program added to the system
	 * @throws HasPremieredException		 if the show already had premiered
	 * @throws IdShowDoesNotExistException   if the idShow don't exist
	 */
	void showPremiere(String idShow) throws HasPremieredException, IdShowDoesNotExistException;

	/**
	 * Removes a register program on the system. This remove implies the remotion of
	 * the connections between program and participants
	 * 
	 * @param idShow - is the identifier of the new program added to the system
	 * @throws HasPremieredException if the show already had premiered
	 * @throws IdShowDoesNotExistException   if the idShow don't exist
	 */
	void removeShow(String idShow) throws HasPremieredException, IdShowDoesNotExistException;
	
	/**
	 * Adds a new key-word to the program
	 * @param idShow - is the identifier of the new program added to the system
	 * @param tag	 - the new key-word added to the show
	 * @throws IdShowDoesNotExistException if the idShow don't exist
	 */
	void addTag(String idShow, String tag) throws IdShowDoesNotExistException;

	/**
	 * 
	 * @param idShow - is the identifier of the new program added to the system
	 * @return the show associated to the idShow to print all the information required
	 * @throws IdShowDoesNotExistException	if the idShow don't exist
	 */
	Show infoShow(String idShow) throws IdShowDoesNotExistException;
	
	/**
	 * 
	 * @param idShow	- is the identifier of the new program added to the system
	 * @param stars 	- is the show's evaluation
	 * @throws InvalidRatingException 		if the evaluation stars aren't between 0 and 10
	 * @throws HasPremieredException 		if the show already had premiered
	 * @throws IdShowDoesNotExistException	if the idShow don't exist
	 */
	void rateShow(String idShow, int stars) throws InvalidRatingException, HasPremieredException, IdShowDoesNotExistException;
	
	
}