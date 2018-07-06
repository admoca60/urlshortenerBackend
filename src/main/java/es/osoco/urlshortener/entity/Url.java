package es.osoco.urlshortener.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Url {

	@Id
	@Column(name="URL_ID")
	@GeneratedValue(generator="urlIdSeq")
	@SequenceGenerator(name="urlIdSeq",sequenceName="URL_SEQ", allocationSize=1)
	private Long id;
	
	@Column(nullable = true, name="HASH_CODE", length=8)
	private String hashCode;
	
	@Column(nullable = true, name="URL_LONG", length=2000)
	private String urlLong;
	
	@Column(nullable = false, name = "EXPIRED")
	private boolean expired;
	
	@Column(nullable = false, name = "ALLOCATED")
	private boolean allocated;

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

	/**
	 * Method that returns the value of the field expired.
	 *
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * Sets the value of the field expired.
	 *
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * Method that returns the value of the field allocated.
	 *
	 * @return the allocated
	 */
	public boolean isAllocated() {
		return allocated;
	}

	/**
	 * Sets the value of the field allocated.
	 *
	 * @param allocated the allocated to set
	 */
	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	
	
}
