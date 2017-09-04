package be.gest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import be.gest.model.enumeration.EtatFacture;

/**
 * Entity implementation class for Entity: Facture
 *
 */
@Entity
@Table(name = "Facture")
public class Facture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Fournisseur_id", nullable = false)
	private Fournisseur fournisseur;

	@Column(name = "totFacture", nullable = false)
	private BigDecimal totFacture;

	@Column(name = "soldFacture", nullable = false)
	private BigDecimal soldFacture;

	@Column(name = "dateFacture", nullable = false)
	private Date dateFacture;

	@Column(name = "dateEcheanceFacture", nullable = false)
	private Date dateEcheanceFacture;

	@Column(name = "etatfacture", nullable = false)
	@Enumerated(EnumType.STRING)
	private EtatFacture etatFacture;
	
	@ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@Fetch(FetchMode.SELECT) to avoid the lazyinitException 
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "facture_bankOrder",joinColumns={@JoinColumn(name="facture_id")},
	inverseJoinColumns={@JoinColumn(name="bankOrder_id")})
	private List<BankOrder> bankOrders =new ArrayList<BankOrder>();

	public EtatFacture getEtatFacture() {
		return etatFacture;
	}

	public void setEtatFacture(EtatFacture etatFacture) {
		this.etatFacture = etatFacture;
	}

	public Facture() {
		super();
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public BigDecimal getTotFacture() {
		return totFacture;
	}

	public void setTotFacture(BigDecimal totFacture) {
		this.totFacture = totFacture;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Date getDateEcheanceFacture() {
		return dateEcheanceFacture;
	}

	public void setDateEcheanceFacture(Date dateEcheanceFacture) {
		this.dateEcheanceFacture = dateEcheanceFacture;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getSoldFacture() {
		return soldFacture;
	}

	public void setSoldFacture(BigDecimal soldFacture) {
		this.soldFacture = soldFacture;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	public List<BankOrder> getBankOrders() {
		return bankOrders;
	}

	public void setBankOrders(List<BankOrder> bankOrders) {
		this.bankOrders = bankOrders;
	}
	@Override
	public String toString() {
		return "Facture [id=" + id + ", fournisseur=" + fournisseur + ", numFacture="  + ", codFacture="
				+ ", totFacture=" + totFacture + ", dateFacture=" + dateFacture + ", dateEcheanceFacture="
				+ dateEcheanceFacture + "]";
	}

}
