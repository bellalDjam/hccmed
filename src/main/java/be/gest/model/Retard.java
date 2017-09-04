package be.gest.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "Retard")
public class Retard implements Serializable {

	private static final long serialVersionUID = -1541596816456844683L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name = "nombreJours", nullable = false)
	private Integer nombreJours;
    
    @Column(name = "penalite", nullable = false)
	private BigDecimal penalite;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mmaterielLocation_id", nullable = false)
	private MaterielLocation materielLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNombreJours() {
		return nombreJours;
	}

	public void setNombreJours(Integer nombreJours) {
		this.nombreJours = nombreJours;
	}

	public BigDecimal getPenalite() {
		return penalite;
	}

	public void setPenalite(BigDecimal penalite) {
		this.penalite = penalite;
	}

	public MaterielLocation getMaterielLocation() {
		return materielLocation;
	}

	public void setMaterielLocation(MaterielLocation materielLocation) {
		this.materielLocation = materielLocation;
	}
    
}
	
	
