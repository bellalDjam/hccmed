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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import be.gest.model.enumeration.EtatCheque;

/**
 * Entity implementation class for Entity: Bank_Order
 *
 */
@Entity
@Table(name = "bankOrder")
public class BankOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "etatCheque", nullable = false)
	@Enumerated(EnumType.STRING)
	private EtatCheque etatCheque;

	@Column(name = "Montant")
	private BigDecimal montant;

	@Column(name = "numero_cheque", nullable = false)
	private Integer numero_cheque;

	@Column(name = "numero_depart", nullable = false)
	private Integer numero_depart;

	@Column(name = "serie_cheque")
	private String serie_cheque;

	@Column(name = "date_validation")
	private Date date_validation;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="bankOrders" ,cascade = CascadeType.ALL)
	private List<Facture> factures =new ArrayList<Facture>();
	
	@Column(name = "datepayement", nullable = true)
	private Date datePayement;

	public BankOrder() {
		super();
	}
	
	public BankOrder(EtatCheque etatCheque, BigDecimal montant, Date date_singnatur, Date date_echeance,
			Date date_validation,Date datePayement) {
		super();
		this.etatCheque = etatCheque;
		this.montant = montant;
		this.date_validation = date_validation;
//		this.payement = payement;
	}

	public Date getDatePayement() {
		return datePayement;
	}
	public void setDatePayement(Date datePayement) {
		this.datePayement = datePayement;
	}

	public EtatCheque getEtatCheque() {
		return etatCheque;
	}

	public void setEtatCheque(EtatCheque etatCheque) {
		this.etatCheque = etatCheque;
	}

	public String getSerie_cheque() {
		return serie_cheque;
	}

	public void setSerie_cheque(String serie_cheque) {
		this.serie_cheque = serie_cheque;
	}

	public Date getDate_validation() {
		return date_validation;
	}

	public void setDate_validation(Date date_validation) {
		this.date_validation = date_validation;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public Integer getNumero_cheque() {
		return numero_cheque;
	}

	public void setNumero_cheque(Integer numero_cheque) {
		this.numero_cheque = numero_cheque;
	}

	public Integer getNumero_depart() {
		return numero_depart;
	}

	public void setNumero_depart(Integer numero_depart) {
		this.numero_depart = numero_depart;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Facture> getFactures() {
		return factures;
	}


	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}
//
//	public Payement getPayement() {
//		return payement;
//	}
//
//	public void setPayement(Payement payement) {
//		this.payement = payement;
//	}

	@Override
	public String toString() {
		return "BankOrder [fournisseur=" + ", facture=" + ", montant=" + montant + ", numero_cheque=" + numero_cheque
				+ ", numero_depart=" + numero_depart + ", date_singnatur=" + ", date_echeance="
				+"]";
	}

}
