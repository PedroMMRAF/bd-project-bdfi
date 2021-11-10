package bdfi;

public interface Program {

	/**
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * 
	 * @return
	 */
	int getProductionYear();
	
	/**
	 * @return If the 
	 */
	boolean isProducing();
	
	/**
	 * Finishes production of this program
	 */
	void finishProduction();

}
