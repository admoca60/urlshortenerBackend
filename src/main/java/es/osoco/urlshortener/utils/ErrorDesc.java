package es.osoco.urlshortener.utils;

/**
 * @author amolinca
 *
 */
public class ErrorDesc {
	/**
	 * Attribute for storing the field errorCode.
	 */
	private int errorCode;
	/**
	 * Attribute for storing the field errorDesc.
	 */
	private String errorDesc;
	
	public ErrorDesc(ResponseEnum enumerate) {
		super();
		this.errorCode = enumerate.getErrorNumber();
		this.errorDesc = enumerate.getErrorMessage();
	}
	
	/**
	 * Constructor method without parameters.	
	 */
	public ErrorDesc() {
		super();
	}
	
	
	
	
	/**
	 * Method that returns the value of the field errorCode.
	 *
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * Sets the value of the field errorCode.
	 *
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * Method that returns the value of the field errorDesc.
	 *
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * Sets the value of the field errorDesc.
	 *
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
}
