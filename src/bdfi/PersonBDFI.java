package bdfi;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
interface PersonBDFI extends Person {

    /**
     * Adds a show where the person participate
     *
     * @param show - show to add
     */
    void addShow(ShowBDFI show);

}
