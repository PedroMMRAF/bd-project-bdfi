package bdfi;

import bdfi.exceptions.*;
import dataStructures.Iterator;

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
	 * TODO AVISO: Considerar trocar isto por metodos mais especificos
	 * 
	 * @param idShow - the show's unique identifier
	 * @return the show associated to the idShow to print all the information
	 *         required
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 */
	Show infoShow(String idShow) throws IdShowDoesNotExistException;

	/**
	 * TODO AVISO: Considerar trocar isto por metodos mais especificos
	 * 
	 * @param idPerson the Person's unique identifier
	 * @return the person associated to the idPerson ti print all the information
	 *         required
	 * @throws IdPersonDoesNotExistException if idPerson doesn't exist
	 */
	Person infoPerson(String idPerson) throws IdPersonDoesNotExistException;

	/**
	 * Lists all the shows where the person in the system are involved
	 * 
	 * @param idPerson - the professional's unique identifier
	 * @return the show where the person are involved --> only a show in stage 1
	 * @throws IdPersonDoesNotExistException if idPerson doesn't exist
	 * @throws PersonHasNoShowsException     if Person aren't involved in any show
	 */
	Show listPersonShows(String idPerson) throws IdPersonDoesNotExistException, PersonHasNoShowsException;

	/**
	 * Lists all the persons who participates in the show
	 * 
	 * @param idShow - the show's unique identifier
	 * @return an iterator with all the persons who participates in the show
	 * @throws IdShowDoesNotExistException if idShow doesn't exist
	 * @throws ShowHasNoParticipants       if the show doesn't have any person
	 *                                     participating in the show
	 */
	Iterator<Person> listPersonInShow(String idShow) throws IdShowDoesNotExistException, ShowHasNoParticipants;

	/**
	 * Lists all the shows with the best average evaluation in the moment
	 * 
	 * @return the shows in the system --> only a show in stage 1
	 * @throws NoShowsInSystemException  if the system haven't any show in the
	 *                                   system
	 * @throws NoShowsPremieredException if the system haven't any show premiered in
	 *                                   the system
	 * @throws NoRatedShowsException     if the system haven't any show rated
	 */
	Show listBestShows() throws NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException;

	/**
	 * TODO: DISCUTIR IMPLEMENTACAO DA FUNCAO
	 * 
	 * @param rating the rating condition to list the show
	 * @return the rated shows with the requested rating
	 * @throws InvalidRatingException
	 * @throws NoShowsInSystemException  - if the evaluation's not between 0 and 10
	 *                                   stars
	 * @throws NoShowsPremieredException - if the system haven't any show premiered
	 *                                   in the system
	 * @throws NoRatedShowsException     - if the system haven't any show rated
	 */
	Show listShows(int rating)
			throws InvalidRatingException, NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException;

	
	Show listTaggedShows(String tag) NoShowsInSystemException, NoTaggedShowsException;

}