package bdfi;

import java.io.Serializable;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public interface Participant extends Serializable {

    /**
     * @return the participant's person
     */
    Person getPerson();

    /**
     * @return the participant's description
     */
    String getDescription();

}
