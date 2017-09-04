package be.gest.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.gest.model.BankOrder;
import be.gest.model.Facture;
import be.gest.model.Payement;

@Repository("PayementDAO")
public class PayementDAOImpl extends AbstractDaoImpl<Payement> implements PayementDAO, Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = -8943737029902086094L;
//
//	@Autowired
//	BankOrderDAO bankOrderDAO;
//	@Autowired
//	FactureDAO factureDAO;
//
//	@Override
//	public List<Payement> findAll() {
//		return this.getAll(Payement.class);
//	}
//
//	@Override
//	public Payement findById(Long id) {
//		return this.getById(Payement.class, id);
//	}
//
//	public void save(Payement payement) {
//		super.save(payement);
//
//	}
//
//	/**
//	 * From panel payment Model window Invoice List and
//	 * a BankOrder are selected & updated for a partial payment
//	 * 
//	 * @param List<Facture>
//	 * @param BigDecimal  value of amount payment
//	 * @param BankOrder bO 
//	 * @param Facture lastF that we choose for the next payment 
//	 * @param BigDecimal totSumF total amount of the List<Facture>
//	 */
//	public void paySelectedFactureByBOrder(List<Facture> factures, 
//			BigDecimal montant, BankOrder bO,Facture lastF, 
//			BigDecimal totSumF,Date date ) {
//		
//		BankOrder bOUp = bankOrderDAO.bankOrderToUse(bO, montant);
//		Payement payement = new Payement(new Date(), bOUp);
//	
//			if (montant.compareTo(totSumF)==-1 && lastF!=null){
//			
//			Facture fact = factures.remove(factures.indexOf(lastF));
//			Facture factUp = factureDAO.factureToSold(fact, 
//					totSumF.setScale(2).subtract(montant.setScale(2)), payement,date);
//			List<Facture> facturesUp = factureDAO.topayListFacture(factures,payement);
//			facturesUp.add(factUp);
//			payement.setFactures(facturesUp);
//			save(payement);
//		}
//		
//	}
//	/**
//	 * From panel payment Model window Invoice List and
//	 * a BankOrder are selected & updated for a final payment
//	 * 
//	 * @param List<Facture>
//	 * @param BigDecimal amount
//	 * @param BankOrder bO
//	 * 
//	 * see  bankOrderToUse()
//	 */
//	public void paySelectedFactureByBOrderEpure(List<Facture> idffs, BigDecimal montant, BankOrder bO) {
//		
//		BankOrder bOUp = bankOrderDAO.bankOrderToUse(bO, montant);
//		Payement payement = new Payement(new Date(), bOUp);
//		if (montant!=null && idffs!=null){
//			List<Facture> facturesUp =factureDAO.topayListFacture(idffs, payement);
//			payement.setFactures(facturesUp);
//			save(payement);
//		}
//		
//	}
	

}
