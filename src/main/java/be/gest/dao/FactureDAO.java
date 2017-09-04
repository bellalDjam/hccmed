package be.gest.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import be.gest.model.BankOrder;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.Payement;

public interface FactureDAO extends AbstractDao<Facture> {
	List<Facture> findAll();
	Facture findFactureByID(Long id);
	List<Long> findAllImpayedByDate(Date dateEcheanceFacture);
	List<Facture> findAllImpayedByFounisseur(Fournisseur fourn);
	List<Facture> findAllImpayedFounisseur();
	List<Facture> findAllImpayed();
	
//	BigDecimal getSumFactures(List<Long> ids);
	List<Long> findAllFacIdImpayedByFounisseur(Fournisseur fourn);
	List<Long> findAllFacIdByFounisseur(Fournisseur fourn);
	public BigDecimal getSumSoldeFacturesList(List<Long> ids);
	BigDecimal getSumFacturesList(List<Long> ids);
	List<Long> findAllPayedFacIdByFounisseur(Fournisseur f);
	BigDecimal echeanceByDate(Date d);
	List<Object> daylyDeadlineTot();
	List<Facture> topayListFacture(List<Facture> fact,BankOrder bO);
	Facture factureToSold(Facture facture, BigDecimal partPayement,Date date,BankOrder bO);
	List<Long> findIdsFacturesList(List<Facture> fact);
	List<Facture> findAllPayedFactures();
//	void paySelectedFactureByBOrder(List<Facture> idffs, BigDecimal toPayValue, BankOrder bankOrd, Facture facture,
//			BigDecimal sumFactures, Date value);
//	void paySelectedFactureByBOrderEpure(List<Facture> idffs, BigDecimal sumFactures, BankOrder bankOrd);
	void paySinglePart(BigDecimal toPayValue, BankOrder bankOrd, Facture fact, Date date);
	List<Facture> findAllPayedFacturesByBo(BankOrder bo);
}
