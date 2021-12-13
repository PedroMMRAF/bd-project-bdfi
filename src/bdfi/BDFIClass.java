package bdfi;

import bdfi.exceptions.*;
import dataStructures.*;
/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class BDFIClass implements BDFI {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Rating score limits
    private static final int RATING_MIN = 0;
    private static final int RATING_MAX = 10;

    // Instance variables
    protected int currentYear;
    protected boolean premieredShows;
    protected Dictionary<String, PersonBDFI> people;
    protected OrderedDictionary<String, ShowBDFI> listShows;
    protected Dictionary<String, ShowBDFI> shows;
    protected OrderedDictionary<Integer, OrderedDictionary<String, Show>> ratedShows;
    protected Dictionary<String, OrderedDictionary<String, Show>> tagShows;

    /**
     * Database constructor
     *
     * @param currentYear - the currently ongoing year
     */
    public BDFIClass(int currentYear) {
        this.currentYear = currentYear;
        this.premieredShows = false;
        this.people = new SepChainHashTable<>();
        this.listShows = new OrderedDoubleList<>();
        shows = new SepChainHashTable<>();
        ratedShows = new BinarySearchTree<>();
        
    }

    @Override
    public void addPerson(String idPerson, String name, int bYear, String gender, String email,
                          String phone)
            throws InvalidYearException, InvalidGenderException, IdPersonExistsException {
        if (bYear < 0 || bYear > currentYear)
            throw new InvalidYearException();

        if (gender == null)
            throw new InvalidGenderException();
        
        if (people.find(idPerson) != null)
            throw new IdPersonExistsException();

        PersonBDFI person = new PersonClass(idPerson, name, bYear, gender, email, phone);

        people.insert(idPerson, person);
    }

    @Override
    public void addShow(String idShow, int pYear, String title)
            throws InvalidYearException, IdShowExistsException {
        if (pYear < 0 || pYear > currentYear)
            throw new InvalidYearException();

        if (shows.find(idShow) != null)
            throw new IdShowExistsException();
        
        ShowBDFI show = new ShowClass(idShow, title, pYear, pYear != currentYear);

        listShows.insert(title, show); //TODO  verificar ordem por titulo e pela capitalizacao dos caracteres 
        shows.insert(idShow, show);
        
        premieredShows = premieredShows || show.hasPremiered();
        
    }

    @Override
    public void addParticipation(String idPerson, String idShow, String description)
            throws IdPersonDoesNotExistException, IdShowDoesNotExistException {
        PersonBDFI person = getPerson(idPerson);

        ShowBDFI show = getShow(idShow);

        person.addShow(show);
        show.addParticipant(new ParticipantClass(person, description));
    }

    @Override
    public void premiereShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowBDFI show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        show.premiere();
        premieredShows = true;
    }

    @Override
    public void removeShow(String idShow)
            throws IdShowDoesNotExistException, HasPremieredException {
        ShowBDFI show = getShow(idShow);

        if (show.hasPremiered())
            throw new HasPremieredException();

        shows.remove(idShow);
        listShows.remove(idShow);
        
        Iterator<String> it = show.listTags();
        
        while(it.hasNext()) {
        	String tag = it.next();
        	tagShows.find(tag.toLowerCase()).remove(show.getTitle());
        }
    }

    @Override
    public void addTag(String idShow, String tag) throws IdShowDoesNotExistException {
        OrderedDictionary<String, Show> dict = tagShows.find(tag.toLowerCase());
        if(dict == null) {
        	dict = new OrderedDoubleList<>();
        	tagShows.insert(tag.toLowerCase(), dict); 
        }
        ShowBDFI show = getShow(idShow);
        show.addTag(tag);
        dict.insert(show.getTitle(), show);  //TODO capatilizacao
        
        
    }

    @Override
    public void rateShow(String idShow, int stars)
            throws InvalidRatingException, IdShowDoesNotExistException, HasPremieredException {
        if (stars < RATING_MIN || stars > RATING_MAX)
            throw new InvalidRatingException();

        ShowBDFI show = getShow(idShow);

        if (!show.hasPremiered())
            throw new HasPremieredException();
        
        if(show.hasRatings()) 
        	ratedShows.find(show.getRating()).remove(show.getTitle()); // TODO ordem do titulo capitalizacao
        
        
        show.addRating(stars);
        
        OrderedDictionary<String, Show> dict = ratedShows.find(show.getRating()); 
        
        if(dict == null) {
        	dict = new OrderedDoubleList<>();
        	ratedShows.insert(show.getRating(), dict);
        }
        
        dict.insert(show.getTitle(), show); // TODO ordem do titulo capitalizacao
        	
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

        if (!premieredShows)
            throw new NoShowsPremieredException();

        Iterator<OrderedDictionary<String, Show>> it = ratedShows.valuesIterator();

        if (!it.hasNext())
            throw new NoRatedShowsException();

        Iterator<Show> showsIt = it.next().valuesIterator();
        
        while(!showsIt.hasNext())
        	showsIt = it.next().valuesIterator();
        
        return showsIt;
    }

    // TODO: Implementacao 2 fase
    @Override
    public Iterator<Show> listShows(int rating)
            throws InvalidRatingException, NoShowsInSystemException, NoShowsPremieredException,
            NoRatedShowsException {
        if (rating < RATING_MIN || rating > RATING_MAX)
            throw new InvalidRatingException();

        if (listShows.isEmpty())
            throw new NoShowsInSystemException();


        if (!premieredShows)
            throw new NoShowsPremieredException();
        
        OrderedDictionary<String, Show> dict = ratedShows.find(rating);
        
        if (dict == null || dict.isEmpty())
            throw new NoRatedShowsException();
        
        return dict.valuesIterator();
    }

    @Override
    public Iterator<Show> listTaggedShows(String tag)
            throws NoShowsInSystemException, NoTaggedShowsException, NoShowsWithTagException {
        if (listShows.isEmpty())
            throw new NoShowsInSystemException();

        if(tagShows.isEmpty()) 
        	throw new NoTaggedShowsException();
        	
       OrderedDictionary<String, Show> dict = tagShows.find(tag.toLowerCase());
       
       if(dict == null || dict.isEmpty())
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
    private PersonBDFI getPerson(String idPerson) throws IdPersonDoesNotExistException {
        PersonBDFI person = people.find(idPerson);

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
    private ShowBDFI getShow(String idShow) throws IdShowDoesNotExistException {
        ShowBDFI show = shows.find(idShow);

        if (show == null)
            throw new IdShowDoesNotExistException();

        return show;
    }
}
