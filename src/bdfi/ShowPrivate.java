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
     * @param participant - the participant to add
     */
    void addParticipant(Participant participant);

    /**
     * Adds one rating on the show
     *
     * @param stars - the rating added to the show
     */
    void addRating(int stars);
}
