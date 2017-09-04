package be.gest.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import be.gest.model.BankOrder;
import be.gest.model.Facture;

public interface BankOrderDAO extends AbstractDao<BankOrder> {
	public List<BankOrder> findAll();
//	
	public List<BankOrder> findBankOrderDisponible();
//	
	public BankOrder findByID(Long code);
	 public BankOrder bankOrderToUse(BankOrder bankOrder,BigDecimal montant);
	 public void validateBankOrderBook(int numDepart, String serie);
	List<BankOrder> findBankOrderSigner();
	public void bankOrderToPay(BankOrder bankOrder,BigDecimal montant, List<Facture> facts);
	public void bankOrderPartToPay(List<Facture> factures, BankOrder bankOrder,BigDecimal montant, Facture fact,BigDecimal summ,Date date);
	public void bankOrderSinglPartToPay(BankOrder bankOrder,BigDecimal montant, Facture fact,Date date);
	public List<Facture> findAllPayedFacByO();
	public List<BankOrder> findBosByPayedFac(Facture fac);
}