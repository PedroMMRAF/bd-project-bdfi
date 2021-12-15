package bdfi;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class ParticipantClass implements Participant {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Store original person object as is
     */
    protected Person person;

    /**
     * Include a description of the person's role
     */
    protected String description;

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
    public Person getPerson() {
        return person;
    }

    public String getDescription() {
        return description;
    }

}
