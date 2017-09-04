package be.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 178515645232651000L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@Length(min=3 ,max=35)
    @Column(name = "rue")
    private String rue;

    @Column(name = "numero")
    private String numero;

    @Column(name = "boitePostale")
    private String boitePostale;

    @Column(name = "codePostal")
    private String codePostal;
	
	@NotNull
	@Length(min=3 ,max=35)
    @Column(name = "nomCommune")
	private String nomCommune;

    /**
     * Constructeur.
     */
    public Address() {

    }

    public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
    
    
    public String getRue() {
	return rue;
    }

    public void setRue(String rue) {
	this.rue = rue;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getBoitePostale() {
	return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
	this.boitePostale = boitePostale;
    }

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", numero=" + numero + ", boitePostale"
				+ boitePostale + ", nomCommune="
				+ nomCommune + "]";
	}
	
}
