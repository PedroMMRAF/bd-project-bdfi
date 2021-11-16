package bdfi;

import bdfi.exceptions.*;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class BDFIClass implements BDFI {
    // Rating score limits
    private static final int RATING_MIN = 0;
    private static final int RATING_MAX = 10;

    // Instance variables
    protected int currentYear;
    protected List<PersonBDFI> people;
    protected List<ShowBDFI> shows;

    /**
     * Database constructor
     *
     * @param currentYear - the currently ongoing year
     */
    public BDFIClass(int currentYear) {
        this.currentYear = currentYear;
        this.people = new DoubleList<>();
        this.shows = new DoubleList<>();
    }

    @Override
    public void addPerson(String idPerson, String name, int bYear, String gender, String email,
                          String phone)
            throws InvalidYearException, InvalidGenderException, IdPersonExistsException {
        if (bYear < 0 || bYear > currentYear)
            throw new InvalidYearException();

        if (gender == null)
            throw new InvalidGenderException();

        PersonBDFI person = new PersonClass(idPerson, name, bYear, gender, email, phone);

        if (people.find(person) > -1)
            throw new IdPersonExistsException();

        people.addLast(person);
    }

    @Override
    public void addShow(String idShow, int pYear, String title)
            throws InvalidYearException, IdShowExistsException {
        if (pYear < 0 || pYear > currentYear)
            throw new InvalidYearException();

        ShowBDFI show = new ShowClass(idShow, title, pYear, pYear != currentYear);

        if (shows.find(show) > -1)
            throw new IdShowExistsException();

        shows.addLast(show);
    }

    @Override
    public void addParticipation(String idPerson, String idShow, String description)
            throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
        PersonBDFI person = getPerson(idPerson);

        ShowBDFI show = getShow(idShow);

        person.addShow(show);
        show.addParticipant(person);
    }

    @Override
    public void premiereShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowBDFI show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        show.premiere();
    }

    @Override
    public void removeShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowBDFI show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        shows.remove(show);
    }

    @Override
    public void addTag(String idShow, String tag) throws IdShowDoesNotExistException {
        getShow(idShow).addTag(tag);
    }

    @Override
    public void rateShow(String idShow, int stars)
            throws InvalidRatingException, IdShowDoesNotExistException, HasPremieredException {
        if (stars < RATING_MIN || stars > RATING_MAX)
            throw new InvalidRatingException();

        ShowBDFI show = getShow(idShow);

        if (!show.hasPremiered())
            throw new HasPremieredException();

        show.addRating(stars);
    }

    @Override
    public ShowBDFI infoShow(String idShow) throws IdShowDoesNotExistException {
        return getShow(idShow);
    }

    @Override
    public PersonBDFI infoPerson(String idPerson) throws IdPersonDoesNotExistException {
        return getPerson(idPerson);
    }

    @Override
    public Show listPersonShows(String idPerson)
            throws IdPersonDoesNotExistException, PersonHasNoShowsException {
        return getPerson(idPerson).listShows();
    }

    @Override
    public Iterator<Person> listPersonInShow(String idShow)
            throws IdShowDoesNotExistException, ShowHasNoParticipantsException {
        return getShow(idShow).listParticipants();
    }

    @Override
    public Show listBestShows()
            throws NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException {
        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        ShowBDFI show = shows.getFirst();

        if (!show.hasPremiered())
            throw new NoShowsPremieredException();

        if (!show.hasRatings())
            throw new NoRatedShowsException();

        return show;
    }

    @Override
    public Show listShows(int rating)
            throws InvalidRatingException, NoShowsInSystemException, NoShowsPremieredException,
            NoRatedShowsException {
        if (rating < RATING_MIN || rating > RATING_MAX)
            throw new InvalidRatingException();

        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        ShowBDFI show = shows.getLast();

        if (!show.hasPremiered())
            throw new NoShowsPremieredException();

        if (!show.hasRatings())
            throw new NoRatedShowsException();

        return show.getRating() == rating ? show : null;
    }

    @Override
    public Show listTaggedShows(String tag)
            throws NoShowsInSystemException, NoTaggedShowsException {
        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        ShowBDFI show = shows.getLast();

        if (!show.hasTag(tag))
            throw new NoTaggedShowsException();

        return show;
    }

    /**
     * Auxilary method for retrieving a professional from the database
     *
     * @param idPerson - the professional's unique identifier
     * @return a professional
     * @throws IdPersonDoesNotExistException if the professional does not exist
     */
    private PersonBDFI getPerson(String idPerson) throws IdPersonDoesNotExistException {
        int pos = people.find(new PersonClass(idPerson, null, 0, null, null, null));

        if (pos == -1)
            throw new IdPersonDoesNotExistException();

        return people.get(pos);
    }

    /**
     * Auxilary method for retrieving a show from the database
     *
     * @param idShow - the show's unique identifier
     * @return a show
     * @throws IdShowDoesNotExistException if the show does not exist
     */
    private ShowBDFI getShow(String idShow) throws IdShowDoesNotExistException {
        int pos = shows.find(new ShowClass(idShow, null, 0, false));

        if (pos == -1)
            throw new IdShowDoesNotExistException();

        return shows.get(pos);
    }


}
