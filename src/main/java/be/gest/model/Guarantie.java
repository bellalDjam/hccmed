package be.gest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Guarantie")
public class Guarantie implements Serializable {

	private static final long serialVersionUID = -1541596816456844683L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name = "referenceFacture", nullable = false)
	private String referenceFacture;
    
    @Column(name = "dateDebut", nullable = false)
	private Date dateDebut;
    
    @Column(name = "dateFin", nullable = false)
   	private Date dateFin;
	
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materiel_id", nullable = false)
	private Materiel materiel;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getReferenceFacture() {
		return referenceFacture;
	}


	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public Materiel getMateriel() {
		return materiel;
	}


	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}
    
}
	
	
