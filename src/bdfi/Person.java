package bdfi;

import bdfi.exceptions.PersonHasNoShowsException;

import java.io.Serializable;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public interface Person extends Serializable {

    /**
     * @return the person's id
     */
    String getId();

    /**
     * @return the person's name
     */
    String getName();

    /**
     * @return the person's birth year
     */
    int getBirthYear();

    /**
     * @return the person's gender
     */
    String getGender();

    /**
     * @return the person's email
     */
    String getEmail();

    /**
     * @return the person's phone number
     */
    String getPhone();

    /**
     * TODO: DISCUTIR IMPLEMENTACAO
     *
     * @return an iterator with all the person's shows
     * @throws PersonHasNoShowsException if person isn't involved in any show
     */
    Show listShows() throws PersonHasNoShowsException;

}
