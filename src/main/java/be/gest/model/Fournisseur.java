package be.gest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable {

	private static final long serialVersionUID = -8928208908171645420L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Length(min=3 ,max=35, message = "aux moin 2 chifres")
	@Column(name = "nom", nullable = false,unique = true)
	private String nom;
	
	@NotNull
	@Length(min=3 , max=15,message = "aux moin 15 chifres")
	@Column(name = "tvanum", unique = true)
	private String numTva;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Address address;
	
	@Embedded
	private MoyenContact moyenContact;
	
	@NotNull
	@Length(min=3 , max=15,message = "aux moin 15 chifres")
	@Column(name = "registernum", nullable = false,unique = true)
	private String numeroRegistre;
	
	@Length(min=3 , max=11,message = "aux moin 11 chifres")
	@Column(name = "article")
	private String article;
	 
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "fournisseur", cascade = CascadeType.ALL)
	private List<Facture> factureList;
	
	@Column(name = "chiffraff", nullable = true)
	private String chiffre;

	public Fournisseur() {
		super();
	}
	public Long getId() {
		return id;
	}

	public String getChiffre() {
		return chiffre;
	}
	public void setChiffre(String chiffre) {
		this.chiffre = chiffre;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

	public String getNumTva() {
		return numTva;
	}

	public void setNumTva(String numTva) {
		this.numTva = numTva;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public MoyenContact getMoyenContact() {
		return moyenContact;
	}

	public void setMoyenContact(MoyenContact moyenContact) {
		this.moyenContact = moyenContact;
	}
	
	public List<Facture> getFactureList() {
		return factureList;
	}


	public void setFactureList(List<Facture> factureList) {
		this.factureList = factureList;
	}

	public String getNumeroRegistre() {
		return numeroRegistre;
	}

	public void setNumeroRegistre(String numeroRegistre) {
		this.numeroRegistre = numeroRegistre;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
	
	
}
