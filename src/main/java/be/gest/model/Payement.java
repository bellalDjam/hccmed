package be.gest.model;

import java.io.Serializable;

import javax.persistence.Table;

//@Entity
//@Table(name = "payement")
public class Payement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -438656533739300432L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	protected Long id;
//
//	@Column(name = "datepayement", nullable = false)
//	private Date datePayement;
//
//	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
//	@JoinColumn(name = "bankOrder_id")
//	private BankOrder bankOrder;
//	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "payement", cascade = CascadeType.ALL)
//	private List<Facture> factures;
//	
//
//	public Payement() {
//		super();
//	}
//
//	public Payement(Date datePayement, BankOrder bankOrder) {
//		super();
//		this.datePayement = datePayement;
//		this.bankOrder = bankOrder;
//	}
//
//
//	public Date getDatePayement() {
//		return datePayement;
//	}
//
//	public void setDatePayement(Date datePayement) {
//		this.datePayement = datePayement;
//	}
//
//	public BankOrder getBankOrders() {
//		return bankOrder;
//	}
//
//	public void setBankOrders(BankOrder bankOrders) {
//		this.bankOrder = bankOrders;
//	}
//
//	public List<Facture> getFactures() {
//		return factures;
//	}
//
//	public void setFactures(List<Facture> factures) {
//		this.factures = factures;
//	}
//
//
//	public BankOrder getBankOrder() {
//		return bankOrder;
//	}
//
//	public void setBankOrder(BankOrder bankOrder) {
//		this.bankOrder = bankOrder;
//	}
//
//	public Long getId() {
//		return id;
//	}
//	public void addFacture(Facture facture){
//		this.factures.add(facture);
//	}
//	

}
