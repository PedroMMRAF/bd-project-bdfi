package bdfi;

import bdfi.exceptions.*;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
public interface BDFI {

	/**
	 * Inserts a new professional in the system
	 * 
	 * @param idPerson - the professional's unique identifier
	 * @param name     - an artistic or professional name
	 * @param bYear    - the professional's year of birth
	 * @param gender   - the professional's gender
	 * @param email    - the professional's email
	 * @param phone    - the professional's phone
	 * @throws InvalidYearException if the provided year exceeds the current year
	 */
	void addPerson(String idPerson, String name, int bYear, Gender gender, String email, String phone)
			throws InvalidYearException, InvalidGenderException;

	/**
	 * Inserts a new show in the system
	 * 
	 * @param idShow - the show's unique identifier
	 * @param pYear  - the show's production year
	 * @param title  - the show's title
	 * @throws InvalidYearException if the provided year exceeds the current year
	 */
	void addShow(String idShow, int pYear, String title) throws InvalidYearException;

	/**
	 * Include the participation of some professional onto a show
	 * 
	 * @param idPerson    - the professional's unique identifier
	 * @param IdShow      - the show's unique identifier
	 * @param description - the participation's description
	 * @throws IdPersonDoesNotExistException if idPerson doesn't exist
	 * @throws IdShowDoesNotExistException   if idShow doesn't exist
	 */
	void addParticipation(String idPerson, String IdShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException;

	/**
	 * Premieres a show on the system
	 * 
	 * @param idShow - the show's unique identifier
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 * @throws HasPremieredException       if the show has already premiered
	 */
	void premiereShow(String idShow) throws IdShowDoesNotExistException, HasPremieredException;

	/**
	 * Removes a show from the system, and the connections between it's participants
	 * and itself
	 * 
	 * 
	 * @param idShow - the show's unique identifier
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 * @throws HasPremieredException       if the show has already premiered
	 */
	void removeShow(String idShow) throws IdShowDoesNotExistException, HasPremieredException;

	/**
	 * Adds a new keyword to a show
	 * 
	 * @param idShow - the show's unique identifier
	 * @param tag    - a keyword to tag the show as
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 */
	void addTag(String idShow, String tag) throws IdShowDoesNotExistException;

	/**
	 * 
	 * @param idShow - the show's unique identifier
	 * @param stars  - the show's evaluation
	 * @throws InvalidRatingException      if the evaluation's not between 0 and 10
	 *                                     stars
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 * @throws HasPremieredException       if the show has already premiered
	 */
	void rateShow(String idShow, int stars)
			throws InvalidRatingException, IdShowDoesNotExistException, HasPremieredException;

	/**
	 * AVISO: Considerar trocar isto por metodos mais especificos
	 * 
	 * @param idShow - the show's unique identifier
	 * @return the show associated to the idShow to print all the information
	 *         required
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 */
	Show infoShow(String idShow) throws IdShowDoesNotExistException;

}