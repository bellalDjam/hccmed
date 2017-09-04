package be.gest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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
@Table(name = "MaterielLocation")
public class MaterielLocation implements Serializable {

	private static final long serialVersionUID = -1724211763006745393L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;

	@Column(name = "dateDebutLocation", nullable = false)
	private Date dateDebutLocation;

	@Column(name = "dateFinLocation", nullable = true)
	private Date dateFinLocation;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "materiel_id", nullable = true)
	private Materiel materiel;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personne_id", nullable = true)
	private Personne personne;
	
	 @OneToOne (mappedBy="materielLocation")
	private Retard retard;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebutLocation() {
		return dateDebutLocation;
	}

	public void setDateDebutLocation(Date dateDebutLocation) {
		this.dateDebutLocation = dateDebutLocation;
	}

	public Date getDateFinLocation() {
		return dateFinLocation;
	}

	public void setDateFinLocation(Date dateFinLocation) {
		this.dateFinLocation = dateFinLocation;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	
	public Personne getPersonne() {
		return personne;
	}
	
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	public Retard getRetard() {
		return retard;
	}
	
	public void setRetard(Retard retard) {
		this.retard = retard;
	}
	
	public String getPrixTotalLocation() {
		Calendar day1 = Calendar.getInstance();
		day1.setTime(dateDebutLocation);
		
		Calendar day2 = Calendar.getInstance();
		day2.setTime(dateFinLocation);
		
		return daysBetween(day1, day2).toString() + "Eur";
	}
	
	public  BigDecimal daysBetween(Calendar day1, Calendar day2){
	    Calendar dayOne = (Calendar) day1.clone(),
	            dayTwo = (Calendar) day2.clone();

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	    	int abs = Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    	
			return  getMateriel().getPrixLocationJour().multiply(new BigDecimal(abs + 1));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }
	        int ret = extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOne.get(Calendar.DAY_OF_YEAR);
	        
	        return  getMateriel().getPrixLocationJour().multiply(new BigDecimal(ret + 1));
	    }
	}
	
}
