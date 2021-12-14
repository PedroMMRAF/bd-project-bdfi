package bdfi;

import bdfi.exceptions.PersonHasNoShowsException;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.OrderedDoubleList;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class PersonClass implements PersonBDFI {
    // Serial version ID
	private static final long serialVersionUID = 1L;

	// Instance variables describing a person's details
    protected String id;
    protected String name;
    protected int birthYear;
    protected String gender;
    protected String email;
    protected String phone;

    /**
     * Ordered mapping of showId -> Show
     * Used to create an iterator of Shows ordered by showId
     * Implemented using an ordered double list (favors iteration over insertion)
     */
    protected OrderedDictionary<String, Show> shows;

    /**
     * Person data structure implementation
     *
     * @param id        - the person's unique identifier
     * @param name      - the person's name
     * @param birthYear - the person's year of birth
     * @param gender    - the person's gender
     * @param email     - the person's email
     * @param phone     - the person's phone
     */
    public PersonClass(String id, String name, int birthYear, String gender, String email,
                       String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthYear = birthYear;
        this.shows = new OrderedDoubleList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public Iterator<Show> listShows() throws PersonHasNoShowsException {
        if (shows.isEmpty())
            throw new PersonHasNoShowsException();

        return shows.valuesIterator();
    }

    @Override
    public void addShow(ShowBDFI show) {
        shows.insert(show.getId().toLowerCase(), show);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PersonClass that = (PersonClass) o;
        return id.equalsIgnoreCase(that.id);
    }
}
