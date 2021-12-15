package bdfi;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
interface PersonPrivate extends Person {

    /**
     * Adds a show where the person participate
     *
     * @param show - show to add
     */
    void addShow(ShowPrivate show);

    /**
     * Removes a show where the person used to participate
     *
     * @param show - show to remove
     */
    void removeShow(ShowPrivate show);

}
