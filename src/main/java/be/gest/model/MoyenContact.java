package be.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class MoyenContact implements Serializable {

	private static final long serialVersionUID = -4352359402495328530L;

    @Column(name = "fax")
	private String fax;
	
	 @Column(name = "telephone")
	private String telephone;
	// @Pattern(regexp =
		// "^((\\+)?[1-9]{1,2})?([-\\s\\.])?((\\(\\d{1,4}\\))|\\d{1,4})(([-\\s\\.])?[0-9]{1,12}){1,2}$")
	@Column(name = "gsm")
	private String gsm;
	
//	@NotNull
	@Length(min=3 ,max=15)
	@Email
    @Column(name = "email")
	private String email;
	
    /**
	 * Constructeur.
	 */
	public MoyenContact() {
		super();
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
