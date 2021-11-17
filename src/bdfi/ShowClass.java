package bdfi;

import bdfi.exceptions.ShowHasNoParticipantsException;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 */
public class ShowClass implements ShowBDFI {
    // Instance variables
    protected String id;
    protected String title;
    protected int prodYear;
    protected boolean premiered;
    protected List<String> tags;
    protected List<Participant> participants;
    protected int ratingCount;
    protected int rating;

    public ShowClass(String id, String title, int prodYear, boolean premiered) {
        this.id = id;
        this.title = title;
        this.prodYear = prodYear;
        this.premiered = premiered;
        this.tags = new DoubleList<>();
        this.participants = new DoubleList<>();
        this.ratingCount = 0;
        this.rating = 0;
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
    public Iterator<Participant> listParticipants()
            throws ShowHasNoParticipantsException {
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
    public void addParticipant(Participant participant) {
        participants.addLast(participant);
    }

    public void addRating(int stars) {
        rating = bdfiAlg.updateReview(stars, ratingCount, rating);
        ratingCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowClass showClass = (ShowClass) o;
        return id.equalsIgnoreCase(showClass.id);
    }
}
