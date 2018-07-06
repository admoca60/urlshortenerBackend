package es.osoco.urlshortener.api.utils;

import es.osoco.urlshortener.utils.ErrorDesc;

public class ResponseWrapper<T> {
	/**
	 * Attribute for storing the field status.
	 */
	private boolean status;
	/**
	 * Attribute for storing the field errorDesc.
	 */
	private ErrorDesc errorDesc;
	/**
	 * Attribute for storing the field data.
	 */
	private T data;
	/**
	 * Method that returns the value of the field status.
	 *
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * Sets the value of the field status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * Method that returns the value of the field errorDesc.
	 *
	 * @return the errorDesc
	 */
	public ErrorDesc getErrorDesc() {
		return errorDesc;
	}
	/**
	 * Sets the value of the field errorDesc.
	 *
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(ErrorDesc errorDesc) {
		this.errorDesc = errorDesc;
	}
	/**
	 * Method that returns the value of the field data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * Sets the value of the field data.
	 *
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
