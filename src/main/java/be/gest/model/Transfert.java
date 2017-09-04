package be.gest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "Transfert")
public class Transfert implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9028141250062779564L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column(name="dateTransfert" )
	private Date dateTransfert;
	
	 @OneToMany(mappedBy = "transfert", cascade = CascadeType.ALL)
	private List<Materiel> materiels;
	 
	 @OneToMany(mappedBy = "transfert", cascade = CascadeType.ALL)
		private List<Departement> departement;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTransfert() {
		return dateTransfert;
	}

	public void setDateTransfert(Date dateTransfert) {
		this.dateTransfert = dateTransfert;
	}

	public List<Materiel> getMateriels() {
		return materiels;
	}

	public void setMateriels(List<Materiel> materiels) {
		this.materiels = materiels;
	}
	
	public List<Departement> getDepartement() {
		return departement;
	}
	
	public void setDepartement(List<Departement> departement) {
		this.departement = departement;
	}

	}


