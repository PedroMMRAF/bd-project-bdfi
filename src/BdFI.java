import bdfi.exceptions.IdPersonDontExistException;
import bdfi.exceptions.IdShowDontExistsException;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidYearException;
import bdfi.exceptions.ShowAlreadyPremieredException;

public interface BdFI {
	

	
	/**
	 * Insertion of a professional in the system
	 * @param id is the only form of identification of the professional
	 * @param Byear is the BirthYear's of the professional
	 * @param email of the professional
	 * @param phone  of the professional
	 * @param gender of the professional
	 * @param name could be a artistic or professional name 
	 * @throws InvalidYearException if the type of the year is not an integer
.	 * @throws InvalidGenderException if the Gender is not one on the system
	 */
	void addPerson(String id, int Byear, String email, String phone, String gender, String name) throws InvalidYearException, InvalidGenderException; 
	
	/**
	 * Insertion of a program in the system
	 * @param idShow is the identifier of the new program added to the system
	 * @param Pyear is the year of production of the program
	 * @param title is the title of the program
	 * @throws InvalidYearException if the year of production is invalid.
	 */
	void addShow(String idShow, int Pyear, String title) throws InvalidYearException;
	
	
	/**
	 * Add to a program the information about a participation of some professional. 
	 * @param idPerson is the only form of identification of the professional
	 * @param IdShow is the identifier of the new program added to the system
	 * @param description
	 * @throws IdPersonDontExistException if the idPerson don't exist
	 * @throws IdShowDontExistsException if the idShow don't exist
	 */
	void addParticipation(String idPerson, String IdShow, String description) throws IdPersonDontExistException, IdShowDontExistsException;

	/**
	 * 
	 * @param idShow is the identifier of the new program added to the system
	 * @throws ShowAlreadyPremieredException if the show already had premiered
	 * @throws IdShowDontExistsException if the idShow don't exist
	 */
	void ShowPremiere(String idShow) throws ShowAlreadyPremieredException, IdShowDontExistsException;
	
	/**
	 * Removes a register program on the system. This remove implies the remotion of the connections between program and participants
	 * @param idShow is the identifier of the new program added to the system
	 * @throws ShowAlreadyPremieredException if the show already had premiered
	 * @throws IdShowDontExistsException if the idShow don't exist
	 */
	void removeShow(String idShow) throws ShowAlreadyPremieredException, IdShowDontExistsException;
	
}