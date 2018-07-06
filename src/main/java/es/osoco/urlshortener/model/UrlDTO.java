package es.osoco.urlshortener.model;

public class UrlDTO {
	
	/**
	 * Attribute for storing the field hashCode.
	 */
	private String hashCode;
	/**
	 * Attribute for storing the field urlLong.
	 */
	private String urlLong;
	
	
	
	/**
	 * Constructor method.	
	 */
	public UrlDTO() {
		super();
	}
	/**
	 * Constructor method.	
	 * @param hashCode
	 * @param urlLong
	 */
	public UrlDTO(String hashCode, String urlLong) {
		super();
		this.hashCode = hashCode;
		this.urlLong = urlLong;
	}
	/**
	 * Method that returns the value of the field hashCode.
	 *
	 * @return the hashCode
	 */
	public String getHashCode() {
		return hashCode;
	}
	/**
	 * Sets the value of the field hashCode.
	 *
	 * @param hashCode the hashCode to set
	 */
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	/**
	 * Method that returns the value of the field urlLong.
	 *
	 * @return the urlLong
	 */
	public String getUrlLong() {
		return urlLong;
	}
	/**
	 * Sets the value of the field urlLong.
	 *
	 * @param urlLong the urlLong to set
	 */
	public void setUrlLong(String urlLong) {
		this.urlLong = urlLong;
	}
	
	

	
}
