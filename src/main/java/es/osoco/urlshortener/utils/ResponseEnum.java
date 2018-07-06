package es.osoco.urlshortener.utils;

/**
 * @author amolinca
 *
 */
public enum ResponseEnum {

	OK(0,"OK"),
	URLNOTFOUND(1,"Url not found"),
	EXPIREDURL(2,"Url Expired"),
	INVALIDREQUEST(3,"Invalid Request"),
	NOTENOUGHTSPACE(4,"Not enough space");
	
	
	/**
	 * Attribute for storing the field errorNumber.
	 */
	private int errorNumber;
	/**
	 * Attribute for storing the field errorMessage.
	 */
	private String errorMessage;
	
	ResponseEnum(int errorNumber, String errorMessage){
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
	}

	/**
	 * Method that returns the value of the field errorNumber.
	 *
	 * @return the errorNumber
	 */
	public int getErrorNumber() {
		return errorNumber;
	}

	/**
	 * Sets the value of the field errorNumber.
	 *
	 * @param errorNumber the errorNumber to set
	 */
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	/**
	 * Method that returns the value of the field errorMessage.
	 *
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the value of the field errorMessage.
	 *
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
