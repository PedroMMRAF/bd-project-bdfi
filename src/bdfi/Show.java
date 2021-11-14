package bdfi;

import bdfi.exceptions.ShowHasNoParticipantsException;
import dataStructures.Iterator;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public interface Show {

    /**
     * @return the show's unique identifier
     */
    String getId();

    /**
     * @return the show's title
     */
    String getTitle();

    /**
     * @return the year the show was produced on
     */
    int getProductionYear();

    /**
     * @return <code>true</code> if the show has premiered, <code>false</code> otherwise
     */
    boolean hasPremiered();

    /**
     * @param tag - the tag to check for
     * @return <code>true</code> if the show has the given tag, <code>false</code> other wise
     */
    boolean hasTag(String tag);

    /**
     * Lists all the tags of the program
     *
     * @return a iterator of tags
     */
    Iterator<String> listTags();

    /**
     * Lists every participant of the show
     *
     * @return an iterator of participants
     * @throws ShowHasNoParticipantsException if no participants are present
     */
    Iterator<Person> listParticipants() throws ShowHasNoParticipantsException;

    /**
     * @return <code>true</code> if the show has ratings, <code>false</code> otherwise
     */
    boolean hasRatings();

    /**
     * @return the show's rating
     */
    int getRating();

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
