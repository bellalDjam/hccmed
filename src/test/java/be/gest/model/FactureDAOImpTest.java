package be.gest.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import be.gest.model.Address;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.enumeration.EtatCheque;
import be.gest.model.enumeration.EtatFacture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class FactureDAOImpTest {

//	@Autowired
//	private FournisseurDAO fournisseurrDAO;
	@Autowired
	private FactureDAO factureFDAO;
	@Autowired
	private BankOrderDAO bankOrderDAO;	
	@Before
	public void saveFacture(){
		Address add = new Address();
		add.setNomCommune("bjlll");
		add.setCodePostal("100");
		add.setNumero("11");
		add.setRue("bmmm");
		
//		Adresse add1 = new Adresse();
//		add.setNomCommune("bjll");
//		add.setCodePostal("100");
//		add.setNumero("1");
//		add.setRue("bolm");
//		
//		Adresse add2 = new Adresse();
//		add.setNomCommune("bxll");
//		add.setCodePostal("100");
//		add.setNumero("22");
//		add.setRue("bmmmmm");
		
//		Fournisseur F = new Fournisseur();
//		F.setArticle("12113856789");
//		F.setNumeroRegistre("123658158858214");
//		F.setNumTva("123548881998454");
//		F.setNom("Fnacc1");
//		F.setAddress(add);
//		fournisseurrDAO.save(F);
//		
		Fournisseur ffsaidal = new Fournisseur();
		ffsaidal.setNom("said1hal");
		ffsaidal.setArticle("13456881730");
		ffsaidal.setNumeroRegistre("283654171708214");
		ffsaidal.setNumTva("223848799810479");
		ffsaidal.setAddress(add);
//		fournisseurrDAO.save(Fsaidal);
//		
//		Fournisseur pfizer = new Fournisseur();
//		pfizer.setNom("pfize1");
//		pfizer.setArticle("12183456789");
//		pfizer.setNumeroRegistre("283654158858255");
//		pfizer.setNumTva("213548879885459");
//		pfizer.setAddress(add);
//		fournisseurrDAO.save(pfizer);
//		
//		Facture factureF = new Facture();
//		factureF.setFournisseur(F);
//		factureF.setTotFacture(new BigDecimal("2.5"));
//		factureF.setSoldFacture(new BigDecimal("2.5"));
//		factureF.setDateEcheanceFacture(new Date());
//		factureF.setDateFacture(new Date());
//		factureF.setEtatFacture(EtatFacture.NONPAYEE);
//		factureFDAO.save(factureF);
//		
//		Facture facture = new Facture();
//		facture.setFournisseur(ffsaidal);
//		facture.setTotFacture(new BigDecimal("12.5"));
//		facture.setSoldFacture(new BigDecimal("12.5"));
//		facture.setDateEcheanceFacture(new Date());
//		facture.setDateFacture(new Date());
//		facture.setEtatFacture(EtatFacture.NONPAYEE);
//		factureFDAO.save(facture);
//		
//		Facture facture1 = new Facture();
//		facture1.setFournisseur(pfizer);
//		facture1.setTotFacture(new BigDecimal("22.5"));
//		facture1.setSoldFacture(new BigDecimal("22.5"));
//		facture1.setDateEcheanceFacture(new Date());
//		facture1.setDateFacture(new Date());
//		facture1.setEtatFacture(EtatFacture.NONPAYEE);
//		factureFDAO.save(facture1);
		
//		Facture facture2 = new Facture();
//		facture2.setFournisseur(ffsaidal);
//		facture2.setTotFacture(new BigDecimal("8.5"));
//		facture2.setSoldFacture(new BigDecimal("8.5"));
//		facture2.setDateEcheanceFacture(new Date());
//		facture2.setDateFacture(new Date());
//		facture2.setEtatFacture(EtatFacture.NONPAYEE);
		
		
//		BankOrder bo = new BankOrder();
//		bo.setDate_validation(new Date());
//		bo.setDatePayement(new Date());
//		bo.setNumero_cheque(1552);
//		bo.setNumero_depart(1550);
//		bo.setSerie_cheque("SG");
//		bo.setEtatCheque(EtatCheque.SIGNER);
//		facture2.setEtatFacture(EtatFacture.NONPAYEE);
//		facture.setEtatFacture(EtatFacture.NONPAYEE);
//		bo.getFactures().add(facture2);
//		bo.getFactures().add(facture);
//		bo.setMontant(new BigDecimal("21.00"));
//		bankOrderDAO.save(bo);
//		BankOrder bo1 = new BankOrder();
//		bo1.setDate_validation(new Date());
//		bo1.setDatePayement(new Date());
//		bo1.setNumero_cheque(1552);
//		bo1.setNumero_depart(15450);
//		bo1.setSerie_cheque("SG");
//		bo1.setEtatCheque(EtatCheque.SECOUR);
//		facture2.getBankOrders().add(bo1);
//		facture2.getBankOrders().add(bo);
//		factureFDAO.save(facture2);
//		
		
	}

	@Test
	public void testFindAll() {
//		List<Facture> result = factureFDAO.getAll(Facture.class);
//		System.out.println(result);
//		List<Facture> result1 = factureFDAO.findAllPayedFactures();
//		System.out.println("AAAAAAAAAAAAAAAAA"+result1.size());

//		Assert.assertNotNull(result);

//		Assert.assertEquals(EtatFacture.NONPAYEE, result.get(0).getEtatFacture());

	}
	

}
