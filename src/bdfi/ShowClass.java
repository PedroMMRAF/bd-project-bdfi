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

	public ShowClass(String id, String title, int prodYear, boolean producing) {
		this.id = id;
		this.title = title;
		this.prodYear = prodYear;
		this.producing = producing;
		this.tags = new DoubleList<>();
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
}
