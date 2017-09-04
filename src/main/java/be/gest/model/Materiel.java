package be.gest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.gest.model.enumeration.EtatMateriel;
import be.gest.model.enumeration.TypeMateriel;

@Entity
@Table(name = "Materiel")
public class Materiel implements Serializable {
	
	private static final long serialVersionUID = 297401655906034438L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column(name = "codeMateriel",  nullable = false)
	private String codeMateriel;
	
	@Column(name = "description",  nullable = false)
	private String description;
	
	
	@Column(name = "typeMateriel",  nullable = false)
    @Enumerated(EnumType.STRING)
	private TypeMateriel typeMateriel;
	
	@Column(name = "numeroSerie")
	private String numeroSerie;
	
	@Column(name = "prixAchat")
	private BigDecimal prixAchat;
	
	@Column(name = "numeroFacture")
	private String numeroFacture; 
	
	@Column(name = "dateAchat")
	private Date dateAchat;
	
	
	@org.hibernate.annotations.Type(type= "org.hibernate.type.NumericBooleanType")
	@Column(name = "disponibilite", nullable = false)
	private Boolean disponibilite = true ;
	
	@Column(name = "etatMateriel",  nullable = false)
    @Enumerated(EnumType.STRING)
	private EtatMateriel etatMateriel;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Fournisseur_id", nullable = true)
	private Fournisseur fournisseur;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "departement_id", nullable = false)
	private Departement departement;


	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Transfert_id")
	private Transfert transfert;
	
	@Column(name = "prixLocationJour", nullable = false)
	private BigDecimal prixLocationJour = BigDecimal.ZERO;

   
   @OneToOne (mappedBy="materiel")
	private MaterielLocation materielLocation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeMateriel() {
		return codeMateriel;
	}

	public void setCodeMateriel(String codeMateriel) {
		this.codeMateriel = codeMateriel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeMateriel getTypeMateriel() {
		return typeMateriel;
	}

	public void setTypeMateriel(TypeMateriel typeMateriel) {
		this.typeMateriel = typeMateriel;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public String getNumeroFacture() {
		return numeroFacture;
	}

	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	

	public EtatMateriel getEtatMateriel() {
		return etatMateriel;
	}

	public void setEtatMateriel(EtatMateriel nouveau) {
		this.etatMateriel = nouveau;
	}

	
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	public Departement getDepartement() {
		return departement;
	}
	
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public Transfert getTransfert() {
		return transfert;
	}
	
	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}
	
	public Boolean getDisponibilite() {
		return disponibilite;
	}
	
	
	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	
	public MaterielLocation getMaterielLocation() {
		return materielLocation;
	}
	
	public void setMaterielLocation(MaterielLocation materielLocation) {
		this.materielLocation = materielLocation;
	}
	
	public BigDecimal getPrixLocationJour() {
		return prixLocationJour;
	}
	
	public void setPrixLocationJour(BigDecimal prixLocationJour) {
		this.prixLocationJour = prixLocationJour;
	}
	
}
