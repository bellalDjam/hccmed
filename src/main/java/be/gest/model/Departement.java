package be.gest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.gest.model.enumeration.TypeDepartement;



@Entity
@Table(name = "Departement")
public class Departement implements Serializable {

	private static final long serialVersionUID = -1541596816456844683L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name = "nom", nullable = false)
	private String nom;
	
    @Embedded
    private MoyenContact moyenContact; 
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = true)
	private Address address;
    
    @Column(name = "typeDepartement", nullable = false)
    @Enumerated(EnumType.STRING)
	private TypeDepartement typeDepartement;
	
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
	private List<Personne> personnes;
    
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
	private List<Materiel> materiels;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Transfert_id")
	private Transfert transfert;

	public Long getId() {
		return id;
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

	public MoyenContact getMoyenContact() {
		return moyenContact;
	}

	public void setMoyenContact(MoyenContact moyenContact) {
		this.moyenContact = moyenContact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public TypeDepartement getTypeDepartement() {
		return typeDepartement;
	}

	public void setTypeDepartement(TypeDepartement typeDepartement) {
		this.typeDepartement = typeDepartement;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}
	
	public Transfert getTransfert() {
		return transfert;
	}
	
	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}
	
}
	
	
