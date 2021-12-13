package bdfi;

import bdfi.exceptions.PersonHasNoShowsException;
import dataStructures.Iterator;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class ParticipantClass implements Participant {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Person person;
    private final String description;

    /**
     * Participant data structure implementation
     *
     * @param person      - the participant's person object
     * @param description - the participant's description
     */
    public ParticipantClass(Person person, String description) {
        this.person = person;
        this.description = description;
    }

    @Override
    public String getId() {
        return person.getId();
    }

    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public int getBirthYear() {
        return person.getBirthYear();
    }

    @Override
    public String getGender() {
        return person.getGender();
    }

    @Override
    public String getEmail() {
        return person.getEmail();
    }

    @Override
    public String getPhone() {
        return person.getPhone();
    }

    @Override
    public Iterator<Show> listShows() throws PersonHasNoShowsException {
        return person.listShows();
    }

    public String getDescription() {
        return description;
    }
}
