package bdfi;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
interface ShowPrivate extends Show {

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
     * @param person        - the participating person
     * @param description   - the participation's description
     */
    void addParticipant(PersonPrivate person, String description);

    /**
     * Adds one rating on the show
     *
     * @param stars - the rating added to the show
     */
    void addRating(int stars);

    /**
     * Removes every association a person has with this show
     */
    void removePeople();

}
