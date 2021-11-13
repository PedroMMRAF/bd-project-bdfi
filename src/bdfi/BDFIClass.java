package bdfi;

import dataStructures.*;
import bdfi.exceptions.*;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
public class BDFIClass implements BDFI {

	private int currentYear;
	private List<Person> people;
	private List<Show> shows;

	public BDFIClass(int currentYear) {
		this.currentYear = currentYear;
		this.people = new DoubleList<>();
		this.shows = new DoubleList<>();
	}

	@Override
	public void addPerson(String idPerson, String name, int bYear, String gender, String email, String phone)
			throws InvalidYearException, InvalidGenderException {
		if (bYear < 0 && bYear > currentYear)
			throw new InvalidYearException();

		if (gender == null)
			throw new InvalidGenderException();

		people.addLast(new PersonClass(idPerson, name, bYear, gender, email, phone));
	}

	@Override
	public void addShow(String idShow, int pYear, String title) throws InvalidYearException {
		if (pYear > currentYear)
			throw new InvalidYearException();

		shows.addLast(new ShowClass(idShow, title, pYear, pYear == currentYear));
	}

	@Override
	public void addParticipation(String idPerson, String IdShow, String description)
			throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void premiereShow(String idShow) throws IdShowDoesNotExistException, HasPremieredException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeShow(String idShow) throws IdShowDoesNotExistException, HasPremieredException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTag(String idShow, String tag) throws IdShowDoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rateShow(String idShow, int stars)
			throws InvalidRatingException, IdShowDoesNotExistException, HasPremieredException {
		// TODO Auto-generated method stub

	}

	@Override
	public Show infoShow(String idShow) throws IdShowDoesNotExistException {
		if (shows.isEmpty())
			throw new IdShowDoesNotExistException();
		
		return shows.getFirst();
	}

	@Override
	public Person infoPerson(String idPerson) throws IdPersonDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Show listPersonShows(String idPerson) throws IdPersonDoesNotExistException, PersonHasNoShowsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Person> listPersonInShow(String idShow) throws IdShowDoesNotExistException, ShowHasNoParticipants {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Show listBestShows() throws NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Show listShows(int rating)
			throws InvalidRatingException, NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Show listTaggedShows(String tag) throws NoShowsInSystemException, NoTaggedShowsException {
		// TODO Auto-generated method stub
		return null;
	}

}
