package bdfi;

import bdfi.exceptions.IdPersonDoesNotExistException;
import bdfi.exceptions.IdShowDoesNotExistException;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidYearException;
import bdfi.exceptions.ShowAlreadyPremieredException;

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
	 * @throws ShowAlreadyPremieredException if the show already had premiered
	 * @throws IdShowDoesNotExistException   if the idShow don't exist
	 */
	void showPremiere(String idShow) throws ShowAlreadyPremieredException, IdShowDoesNotExistException;

	/**
	 * Removes a register program on the system. This remove implies the remotion of
	 * the connections between program and participants
	 * 
	 * @param idShow - is the identifier of the new program added to the system
	 * @throws ShowAlreadyPremieredException if the show already had premiered
	 * @throws IdShowDoesNotExistException   if the idShow don't exist
	 */
	void removeShow(String idShow) throws ShowAlreadyPremieredException, IdShowDoesNotExistException;

}