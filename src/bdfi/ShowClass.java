package bdfi;

import dataStructures.*;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
public class ShowClass implements Show {
	protected String id;
	protected String title;
	protected int prodYear;
	protected boolean producing;
	protected List<String> tags;
	private int ratingCount;
	private int rating;

	public ShowClass(String id, String title, int prodYear, boolean producing) {
		this.id = id;
		this.title = title;
		this.prodYear = prodYear;
		this.producing = producing;
		this.tags = new DoubleList<>();
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
	public boolean isProducing() {
		return producing;
	}

	@Override
	public void finishProduction() {
		producing = false;
	}

	@Override
	public Iterator<String> tagsIterator() {
		return tags.iterator();
	}
	
	public void addRating(int stars) {
		rating = Math.round((float) ((stars + ratingCount * rating) / ((float) (rating + 1))));
		ratingCount++;
	}
	
	@Override
	public int getRating() {
		return rating;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ShowClass))
			return false;
		ShowClass other = (ShowClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
