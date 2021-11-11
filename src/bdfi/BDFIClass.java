package bdfi;

import bdfi.exceptions.IdPersonDoesNotExistException;
import bdfi.exceptions.IdShowDoesNotExistException;
import bdfi.exceptions.InvalidGenderException;
import bdfi.exceptions.InvalidYearException;
import bdfi.exceptions.ShowAlreadyPremieredException;

public class BDFIClass implements BDFI {

	@Override
	public void addPerson(String id, int Byear, String email, String phone, Gender gender, String name)
			throws InvalidYearException, InvalidGenderException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addShow(String idShow, int Pyear, String title) throws InvalidYearException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addParticipation(String idPerson, String IdShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void showPremiere(String idShow) throws ShowAlreadyPremieredException, IdShowDoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeShow(String idShow) throws ShowAlreadyPremieredException, IdShowDoesNotExistException {
		// TODO Auto-generated method stub

	}

}
