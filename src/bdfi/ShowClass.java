package bdfi;

import java.time.LocalDateTime;

public class ShowClass implements Show {
	protected String id;
	protected String title;
	protected int prodYear;
	protected boolean producing;

	public ShowClass(String id, String title, int prodYear) {
		this.id = id;
		this.title = title;
		this.prodYear = prodYear;
		this.producing = prodYear == LocalDateTime.now().getYear();
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
}
