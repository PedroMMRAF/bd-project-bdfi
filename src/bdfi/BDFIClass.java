package bdfi;

import bdfi.exceptions.*;
import dataStructures.*;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class BDFIClass implements BDFI {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Rating score limits
     */
    private static final int RATING_MIN = 0;
    private static final int RATING_MAX = 10;
    private static final int RATINGS = RATING_MAX - RATING_MIN + 1;

    /**
     * Used to check if shows are still in production or not
     */
    protected int currentYear;

    /**
     * Used to check if any shows have premiered or not
     */
    protected boolean hasPremieredShows;

    /**
     * Used to check if any shows have been rated or not
     */
    protected boolean hasRatedShows;

    /**
     * Used to check how many shows have been tagged
     */
    protected int taggedShowCount;

    /**
     * Mapping of idPerson -> PersonBDFI
     * Only used within this package
     * Implemented using a separate chain hash table
     */
    protected Dictionary<String, PersonPrivate> people;

    /**
     * Mapping of idShow -> ShowBDFI
     * Only used within this package
     * Implemented using a separate chain hash table
     */
    protected Dictionary<String, ShowPrivate> shows;

    /**
     * Array of key ordered mapping of titleShow -> Show
     * Used for title ordered iteration of shows with a specific rating
     * Implemented using an array of ordered double lists
     */
    protected OrderedDictionary<String, Show>[] ratedShows;

    /**
     * Key ordered mapping of tagShow -> (Key ordered mapping of titleShow -> Show)
     * Used for title ordered iteration of shows with a specific tag
     * First layer: Separate chain hash table
     * Second layer: Ordered double list
     */
    protected Dictionary<String, OrderedDictionary<String, Show>> taggedShows;

    /**
     * Database constructor
     *
     * @param currentYear - the currently ongoing year
     */
    @SuppressWarnings("unchecked")
    public BDFIClass(int currentYear) {
        this.currentYear = currentYear;
        this.hasPremieredShows = false;
        this.taggedShowCount = 0;
        this.people = new SepChainHashTable<>();
        this.shows = new SepChainHashTable<>();
        this.ratedShows = (OrderedDictionary<String, Show>[]) new OrderedDictionary[RATINGS];
        this.taggedShows = new SepChainHashTable<>();

        for (int i = 0; i < RATINGS; i++)
            this.ratedShows[i] = new OrderedDoubleList<>();
    }

    @Override
    public void addPerson(String idPerson, String name, int bYear, String gender, String email,
                          String phone)
            throws InvalidYearException, InvalidGenderException, IdPersonExistsException {
        if (bYear < 0 || bYear > currentYear)
            throw new InvalidYearException();

        if (gender == null)
            throw new InvalidGenderException();

        if (people.find(idPerson.toLowerCase()) != null)
            throw new IdPersonExistsException();

        PersonPrivate person = new PersonClass(idPerson, name, bYear, gender, email, phone);

        people.insert(idPerson.toLowerCase(), person);
    }

    @Override
    public void addShow(String idShow, int pYear, String title)
            throws InvalidYearException, IdShowExistsException {
        if (pYear < 0 || pYear > currentYear)
            throw new InvalidYearException();

        if (shows.find(idShow.toLowerCase()) != null)
            throw new IdShowExistsException();

        ShowPrivate show = new ShowClass(idShow, title, pYear, pYear != currentYear);

        shows.insert(idShow.toLowerCase(), show);

        hasPremieredShows = hasPremieredShows || show.hasPremiered();
    }

    @Override
    public void addParticipation(String idPerson, String idShow, String description)
            throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
        PersonPrivate person = getPerson(idPerson);

        ShowPrivate show = getShow(idShow);

        person.addShow(show);
        show.addParticipant(person, description);
    }

    @Override
    public void premiereShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowPrivate show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        show.premiere();
        hasPremieredShows = true;
    }

    @Override
    public void removeShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowPrivate show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        // Show cannot be rated, therefore does not need to be removed from ratedShows

        shows.remove(idShow);
        show.removePeople();

        Iterator<String> it = show.listTags();

        while (it.hasNext()) {
            taggedShows.find(it.next().toLowerCase()).remove(show.getTitle().toLowerCase());
            taggedShowCount--;
        }
    }

    @Override
    public void addTag(String idShow, String tag) throws IdShowDoesNotExistException {
        OrderedDictionary<String, Show> tagged = taggedShows.find(tag.toLowerCase());

        if (tagged == null) {
            tagged = new OrderedDoubleList<>();
            taggedShows.insert(tag.toLowerCase(), tagged);
        }

        ShowPrivate show = getShow(idShow);

        tagged.insert(show.getTitle().toLowerCase(), show);

        show.addTag(tag);
        taggedShowCount++;
    }

    @Override
    public void rateShow(String idShow, int stars)
            throws InvalidRatingException, IdShowDoesNotExistException, HasPremieredException {
        if (stars < RATING_MIN || stars > RATING_MAX)
            throw new InvalidRatingException();

        ShowPrivate show = getShow(idShow);

        if (!show.hasPremiered())
            throw new HasPremieredException();

        // Necessary since rating can change
        if (show.hasRatings())
            ratedShows[invRating(show)].remove(show.getTitle().toLowerCase());

        show.addRating(stars);
        hasRatedShows = true;

        ratedShows[invRating(show)].insert(show.getTitle().toLowerCase(), show);
    }

    @Override
    public ShowPrivate infoShow(String idShow) throws IdShowDoesNotExistException {
        return getShow(idShow);
    }

    @Override
    public PersonPrivate infoPerson(String idPerson) throws IdPersonDoesNotExistException {
        return getPerson(idPerson);
    }

    @Override
    public Iterator<Show> listShowsPerson(String idPerson)
            throws IdPersonDoesNotExistException, PersonHasNoShowsException {
        return getPerson(idPerson).listShows();
    }

    @Override
    public Iterator<Participant> listParticipations(String idShow)
            throws IdShowDoesNotExistException, ShowHasNoParticipantsException {
        return getShow(idShow).listParticipants();
    }

    @Override
    public Iterator<Show> listBestShows()
            throws NoShowsInSystemException, NoShowsPremieredException, NoRatedShowsException {
        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        if (!hasPremieredShows)
            throw new NoShowsPremieredException();

        if (!hasRatedShows)
            throw new NoRatedShowsException();

        int current = 0;

        OrderedDictionary<String, Show> shows = ratedShows[current];

        while (shows.isEmpty())
            shows = ratedShows[++current];

        // No need to verify if 'current' exceeds 'ratedShows''s size
        // because at least one rated show exists

        return shows.valuesIterator();
    }

    @Override
    public Iterator<Show> listShows(int rating)
            throws InvalidRatingException, NoShowsInSystemException, NoShowsPremieredException,
            NoRatedShowsException, NoShowsWithRatingException {
        if (rating < RATING_MIN || rating > RATING_MAX)
            throw new InvalidRatingException();

        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        if (!hasPremieredShows)
            throw new NoShowsPremieredException();

        if (!hasRatedShows)
            throw new NoRatedShowsException();

        OrderedDictionary<String, Show> shows = ratedShows[invRating(rating)];

        if (shows.isEmpty())
            throw new NoShowsWithRatingException();

        return shows.valuesIterator();
    }

    @Override
    public Iterator<Show> listTaggedShows(String tag)
            throws NoShowsInSystemException, NoTaggedShowsException, NoShowsWithTagException {
        if (shows.isEmpty())
            throw new NoShowsInSystemException();

        if (taggedShowCount == 0)
            throw new NoTaggedShowsException();

        OrderedDictionary<String, Show> dict = taggedShows.find(tag.toLowerCase());

        if (dict == null || dict.isEmpty())
            throw new NoShowsWithTagException();

        return dict.valuesIterator();
    }

    /**
     * Auxilary method for retrieving a professional from the database
     *
     * @param idPerson - the professional's unique identifier
     * @return a professional
     * @throws IdPersonDoesNotExistException if the professional does not exist
     */
    private PersonPrivate getPerson(String idPerson) throws IdPersonDoesNotExistException {
        PersonPrivate person = people.find(idPerson.toLowerCase());

        if (person == null)
            throw new IdPersonDoesNotExistException();

        return person;
    }

    /**
     * Auxilary method for retrieving a show from the database
     *
     * @param idShow - the show's unique identifier
     * @return a show
     * @throws IdShowDoesNotExistException if the show does not exist
     */
    private ShowPrivate getShow(String idShow) throws IdShowDoesNotExistException {
        ShowPrivate show = shows.find(idShow.toLowerCase());

        if (show == null)
            throw new IdShowDoesNotExistException();

        return show;
    }

    /**
     * @param show - a show
     * @return Inverted show rating for sorting
     */
    private int invRating(ShowPrivate show) {
        return invRating(show.getRating());
    }

    /**
     * @param rating - a rating value
     * @return Inverted rating for sorting
     */
    private int invRating(int rating) {
        return RATING_MAX - rating;
    }

}
