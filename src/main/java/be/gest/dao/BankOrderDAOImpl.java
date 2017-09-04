package be.gest.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.gest.model.BankOrder;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.enumeration.EtatCheque;
import be.gest.model.enumeration.EtatFacture;

@Repository("BankOrderDAO")
public class BankOrderDAOImpl extends AbstractDaoImpl<BankOrder> implements BankOrderDAO, Serializable { 
	@Autowired
	private FactureDAO factureDAO;
/**
	 * 
	 */
	private static final long serialVersionUID = 994617574351725893L;

public List<BankOrder> findAll() {
	return this.getAll(BankOrder.class);
}
//
//
@Override
@SuppressWarnings("unchecked")
public List<BankOrder> findBankOrderDisponible() {

	List<BankOrder> mats = sessionFactory.getCurrentSession().createCriteria(BankOrder.class)
		    .add( Restrictions.eq("etatCheque", EtatCheque.NOUVEAU)).list(); 
		    
	
	return mats;
}
@Override
@SuppressWarnings("unchecked")
public List<BankOrder> findBankOrderSigner() {

	List<BankOrder> mats = sessionFactory.getCurrentSession().createCriteria(BankOrder.class)
		    .add( Restrictions.eq("etatCheque", EtatCheque.SIGNER)).list(); 
		    
	
	return mats;
}

@Override
public BankOrder findByID(Long code) {
	if(code != null) {
		System.out.println("le id doit exister");
	}

	return getById(BankOrder.class, code);
	}
	@Override
public void save(BankOrder bank_ord){
	super.save(bank_ord);
}
	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> findAllPayedFacByO() {
	
//		
//		+ "fac.fournisseur.nom, "
//		+ "fac.etatFacture,"
//		+ "fac.totFacture"
//		+ "  fac.soldFacture,"
//		+ "fac.dateFacture  "
		List<Facture> factrs =  sessionFactory.getCurrentSession().createQuery("select	fac FROM Facture fac left join fac.bankOrders bo where bo.etatCheque='SIGNER' ")
				.list();
		return factrs;
	}
	
	/** Using a payed  Facture to get list of BankOrder
	 * 
	 *  @param Facture
	 *  
	 *  @return List<BankOrder>
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankOrder> findBosByPayedFac(Facture fac) {
		List<BankOrder> bos =  sessionFactory.getCurrentSession().createQuery("Select bo FROM BankOrder bo  join bo.factures fac where  fac.id=:idfac ")
		.setParameter("idfac", fac.getId()).list();
		
		return bos;
	}
	/** Using a bankOrder must be updated
	 * 
	 *  @param BankOrder
	 *  
	 *  @return updated BankOrder
	 * 
	 * */
	public BankOrder bankOrderToUse(BankOrder bankOrder,BigDecimal montant){
		bankOrder.setEtatCheque(EtatCheque.SIGNER);
		bankOrder.setDatePayement(new Date());
		bankOrder.setMontant(montant);
		return update(bankOrder);
	}
	/**
	 *  payment with  Using a bankOrder must be updated
	 * 
	 *  @param BankOrder
	 *  
	 *  @return updated BankOrder
	 * 
	 * */
	public void bankOrderToPay(BankOrder bankOrder,BigDecimal montant, List<Facture> fact){
		BankOrder bankO= bankOrderToUse( bankOrder, montant);
//		if(fact.size()>1 && montant!=null && fact!=null){
			
			List<Facture> facturesUp = factureDAO.topayListFacture(fact,bankO);
				bankO.setFactures(facturesUp);
				update(bankO);
			
//		}else if(fact.size()==1 && montant!=null && fact!=null){
//			
//			Facture facture =fact.get(0);
//			facture.setEtatFacture(EtatFacture.PAYEE);
//			facture.setSoldFacture(BigDecimal.ZERO);
//			factureDAO.update(facture);
//			bankO.getFactures().add(facture);
//			update(bankO);
//		}
		
	}
	
	/**
	 * From panel payment Model window Invoice List and
	 * a BankOrder are selected & updated for a partial payment
	 * 
	 * @param List<Facture>
	 * @param BigDecimal  value of amount payment
	 * @param BankOrder bO 
	 * @param Facture lastF that we choose for the next payment 
	 * @param BigDecimal totSumF total amount of the List<Facture>
	 */
	public void bankOrderPartToPay(List<Facture> factures, BankOrder bO,BigDecimal montant, 
			Facture fact,BigDecimal summ,Date date){
		BankOrder bankO= bankOrderToUse( bO, montant);
		if (montant.compareTo(summ)==-1 && factures!=null && factures.size()>1){
//			if (montant.compareTo(summ)==-1 && factures!=null ){
			fact = factures.remove(factures.indexOf( fact));
			Facture factUp = factureDAO.factureToSold(fact,  summ.setScale(2).subtract(montant.setScale(2)), date, bankO);
			List<Facture> facturesUp = factureDAO.topayListFacture(factures,bankO);
			facturesUp.add(factUp);
			bO.setFactures(facturesUp);
			update(bO);
		}
		//case part payment with : Single invoice
//		if(factures.size()==1 && montant!=null && fact!=null){
////			CASE 2
//			
//			Facture factUp = factureDAO.factureToSold(fact,  summ.setScale(2).subtract(montant.setScale(2)), date, bO);
//			List<Facture> facturesUp =new ArrayList<Facture>();
//			facturesUp.add(factUp);
//			bO.setFactures(facturesUp);
//			update(bO);
//			}
	}
	public void bankOrderSinglPartToPay(BankOrder bankOrder,BigDecimal montant, Facture fact,Date date){
		if (montant!=null && fact!=null ){
			BankOrder bankO= bankOrderToUse( bankOrder, montant);
			Facture factUp = factureDAO.factureToSold(fact,  fact.getSoldFacture().setScale(2).subtract(montant.setScale(2)), date, bankO);
			bankO.getFactures().add(factUp);
			update(bankO);
		}
	}

/**
 * this function generate save in DB 
 *  every check with real serial number 
 * of check book since we start with
 * the first serial number of the book 
  * */
	public void validateBankOrderBook(int numDepart, String serie) {
		 
		
		if(serie ==null){
			for( int i=0 ; i <50 ;i++){ 
				BankOrder mat = new BankOrder();
				mat.setDate_validation(new Date());
				mat.setNumero_depart(numDepart);
				mat.setNumero_cheque(numDepart+i);
				mat.setEtatCheque(EtatCheque.NOUVEAU);
				save(mat);
				}
		}else{
			for(int i=0 ; i <50 ;i++){ 
				BankOrder mat = new BankOrder();
				mat.setDate_validation(new Date());
				mat.setNumero_depart(numDepart);
				mat.setNumero_cheque(numDepart+i);
				mat.setSerie_cheque(serie);
				mat.setEtatCheque(EtatCheque.NOUVEAU);
				save(mat);
				}
		}
	}
}
