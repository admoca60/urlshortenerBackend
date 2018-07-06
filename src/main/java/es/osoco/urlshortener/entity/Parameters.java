package es.osoco.urlshortener.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author amolinca
 *
 */
public class Parameters {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PARAM_ID")
	private Long id;
	
	@Column(name="PARAM_CODE", length=10, nullable=false)
	private String paramCode;
	
	@Column(name="PARAM_VALUE",length=255, nullable=false)
	private String paramValue;

	/**
	 * Method that returns the value of the field id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value of the field id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method that returns the value of the field paramCode.
	 *
	 * @return the paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}

	/**
	 * Sets the value of the field paramCode.
	 *
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	/**
	 * Method that returns the value of the field paramValue.
	 *
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * Sets the value of the field paramValue.
	 *
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	
}
