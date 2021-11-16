package bdfi;

import bdfi.exceptions.PersonHasNoShowsException;

public class ParticipantClass implements Participant {

    private final Person person;
    private final String description;

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
    public Show listShows() throws PersonHasNoShowsException {
        return person.listShows();
    }

    public String getDescription() {
        return description;
    }
}
