package bdfi;

import bdfi.exceptions.ShowHasNoParticipantsException;
import dataStructures.Iterator;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public interface Show extends ShowMain {

    /**
     * Finishes production of the show
     */
    void premiere();

    /**
     * Adds a keyword to the show
     *
     * @param tag - the added keyword added
     */
    void addTag(String tag);

    /**
     * Adds a participant to the show
     *
     * @param person - the participant to add
     */
    void addParticipant(Person person);

    /**
     * Adds one rating on the show
     *
     * @param stars - the rating added to the show
     */
    void addRating(int stars);
}
