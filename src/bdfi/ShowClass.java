package bdfi;

import bdfi.exceptions.ShowHasNoParticipantsException;
import dataStructures.*;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class ShowClass implements ShowPrivate {

    /**
     * Serial Version UID of the Class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instance variables
     */
    private final String id;
    private final String title;
    private final int prodYear;
    private boolean premiered;
    private int ratingCount;
    private int rating;

    /**
     * List of tags
     * Used for iteration and tag verification (find)
     * Implemented using a double list
     */
    private final List<String> tags;

    /**
     * List of participants
     * Used for iteration
     * Implemented using a double list
     */
    private final List<Participant> participants;

    /**
     * Mapping of idPerson -> Person
     * Used for show removal
     * Implemented using a separate chain hash table
     */
    private final Dictionary<String, PersonPrivate> people;

    /**
     * Show data structure implementation
     *
     * @param id        - the show's unique identifier
     * @param title     - the show's title
     * @param prodYear  - the show's year of production
     * @param premiered - the show's production state
     */
    public ShowClass(String id, String title, int prodYear, boolean premiered) {
        this.id = id;
        this.title = title;
        this.prodYear = prodYear;
        this.premiered = premiered;
        this.ratingCount = 0;
        this.rating = 0;
        this.tags = new DoubleList<>();
        this.participants = new DoubleList<>();
        this.people = new SepChainHashTable<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getProductionYear() {
        return prodYear;
    }

    @Override
    public boolean hasPremiered() {
        return premiered;
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.find(tag) > -1;
    }

    @Override
    public Iterator<String> listTags() {
        return tags.iterator();
    }

    @Override
    public Iterator<Participant> listParticipants() throws ShowHasNoParticipantsException {
        if (participants.isEmpty())
            throw new ShowHasNoParticipantsException();

        return participants.iterator();
    }

    @Override
    public boolean hasRatings() {
        return ratingCount > 0;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void premiere() {
        premiered = true;
    }

    @Override
    public void addTag(String tag) {
        tags.addLast(tag);
    }

    @Override
    public void addParticipant(PersonPrivate person, String description) {
        participants.addLast(new ParticipantClass(person, description));
        people.insert(person.getId(), person);
    }

    @Override
    public void addRating(int stars) {
        rating = bdfiAlg.updateReview(stars, ratingCount, rating);
        ratingCount++;
    }

    @Override
    public void removePeople() {
        Iterator<PersonPrivate> it = people.valuesIterator();

        while (it.hasNext())
            it.next().removeShow(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShowClass showClass = (ShowClass) o;
        return id.equalsIgnoreCase(showClass.id);
    }

}
