package be.gest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.gest.dao.BankOrderDAO;
import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.dao.PayementDAO;
import be.gest.model.Address;
import be.gest.model.BankOrder;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.Payement;
import be.gest.model.enumeration.EtatCheque;
import be.gest.model.enumeration.EtatFacture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class PayementDAOTest {

//	@Autowired
//	private BankOrderDAO bankOrderDAO;
//	@Autowired
//	private FournisseurDAO fournisseurrDAO;
//	@Autowired
//	private FactureDAO factureFDAO;
//	@Autowired
//	private PayementDAO payementDAO;
//	
//	@Test
//	public void test() {
//		Adresse add = new Adresse();
//		add.setNomCommune("bllkkl");
//		add.setCodePostal("100");
//		add.setNumero("111");
//		add.setRue("bxlmmmmm");
//		
//		Adresse add1 = new Adresse();
//		add1.setNomCommune("bjkklll");
//		add1.setCodePostal("100");
//		add1.setNumero("1");
//		add1.setRue("bxlm1m");
//		
//		Adresse add2 = new Adresse();
//		add2.setNomCommune("bx2kkl");
//		add2.setCodePostal("100");
//		add2.setNumero("22");
//		add2.setRue("b1mmm");
//		
//		Fournisseur F = new Fournisseur();
//		F.setArticle("12113811189");
//		F.setNumeroRegistre("123684111858214");
//		F.setNumTva("123548878798454");
//		F.setNom("Fnac1c1");
//		F.setAddress(add);
////		fournisseurrDAO.save(F);
//		
//		Fournisseur Fsaidale = new Fournisseur();
//		Fsaidale.setNom("s4i1al11");
//		Fsaidale.setArticle("83451119234");
//		Fsaidale.setNumeroRegistre("128611158858214");
//		Fsaidale.setNumTva("144235418198454");
//		Fsaidale.setAddress(add1);
//		fournisseurrDAO.save(Fsaidale);
//		
//		Fournisseur pfizer = new Fournisseur();
//		pfizer.setNom("4fize11");
//		pfizer.setArticle("12114846789");
//		pfizer.setNumeroRegistre("223684458858255");
//		pfizer.setNumTva("213548448845459");
//		pfizer.setAddress(add2);
////		fournisseurrDAO.save(pfizer);
//		
//		Facture factureF = new Facture();
//		factureF.setFournisseur(F);
//		factureF.setTotFacture(new BigDecimal("2.5"));
//		factureF.setSoldFacture(new BigDecimal("2.5"));
//		factureF.setDateEcheanceFacture(new Date());
//		factureF.setDateFacture(new Date());
//		factureF.setEtatFacture(EtatFacture.NONPAYEE);
////		factureFDAO.save(factureF);
//		
//		Facture facture = new Facture();
//		facture.setFournisseur(F);
//		facture.setTotFacture(new BigDecimal("12.5"));
//		facture.setSoldFacture(new BigDecimal("12.5"));
//		facture.setDateEcheanceFacture(new Date());
//		facture.setDateFacture(new Date());
//		facture.setEtatFacture(EtatFacture.NONPAYEE);
////		factureFDAO.save(facture);
//		
//		Facture facture1 = new Facture();
//		facture1.setFournisseur(pfizer);
//		facture1.setTotFacture(new BigDecimal("22.5"));
//		facture1.setSoldFacture(new BigDecimal("22.5"));
//		facture1.setDateEcheanceFacture(new Date());
//		facture1.setDateFacture(new Date());
//		facture1.setEtatFacture(EtatFacture.NONPAYEE);
////		factureFDAO.save(facture1);
//		
//		Facture facture2 = new Facture();
//		facture2.setFournisseur(Fsaidale);
//		facture2.setTotFacture(new BigDecimal("8.5"));
//		facture2.setSoldFacture(new BigDecimal("8.5"));
//		facture2.setDateEcheanceFacture(new Date());
//		facture2.setDateFacture(new Date());
//		facture2.setEtatFacture(EtatFacture.NONPAYEE);
//		
////		factureFDAO.save(facture2);
//		BankOrder bo = new BankOrder();
//		bo.setNumero_cheque(515);
//		bo.setNumero_depart(511);
//		bo.setDate_validation(new Date());
//		bo.setEtatCheque(EtatCheque.NOUVEAU);
//		
//		bankOrderDAO.save(bo);
//		
//		BankOrder bo1 =bankOrderDAO.findByID((long) 1);
//		System.out.println("BANKORDER  "+bo1);
//		List<Facture> factures =new ArrayList<Facture>();
//		factures.add(facture);
//		factures.add(factureF);
//		
//		System.out.println("factures  "+factures);
//	
//		
//		
//		payementDAO.paySelectedFactureByBOrderEpure(factures, new BigDecimal("15"), bankOrderDAO.findByID((long) 1));
//		
////		Payement payement =new Payement();
////		payement.setDatePayement(new Date());
////		payement.getFactures().add(facture);
////		payement.getFactures().add(factureF);
////		payement.setBankOrders(bankOrder);
////		System.out.println("payement  "+payement);
////		payementDAO.save(payement);
//		
//	}
//	@Test
//	public void findPayemnt() {
//		
//		List<Payement> result = payementDAO.findAll();
//		System.out.println("result  "+result);
//		Assert.assertNotNull(result);
//		
//	}

}
