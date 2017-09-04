package be.gest.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.gest.dao.BankOrderDAO;
import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class BankOrderDAOTest  {
	
	@Autowired
	private BankOrderDAO bankOrderDAO;
	@Autowired
	private FournisseurDAO fournisseurrDAO;
	@Autowired
	private FactureDAO factureFDAO;
	
	
//	@Before
//	@Rollback(false)
//	public void save() throws ParseException{
//		
//		 bankOrderDAO.validateBankOrderBook(1,"SG");
//
//			
//	}

	
	@Test
	public void findAll() {
	System.out.println(factureFDAO.findFactureByID((long) 55).toString()+"aaaaaaaaaaaaaaa");
	Assert.assertNotNull(factureFDAO.findFactureByID((long) 55));
		bankOrderDAO.findBosByPayedFac(factureFDAO.findFactureByID((long) 55));
		Assert.assertNotNull(bankOrderDAO.findBosByPayedFac(factureFDAO.findFactureByID((long) 55)));
		System.out.println(bankOrderDAO.findBosByPayedFac(factureFDAO.findFactureByID((long) 55)).toString()+"AAAAAAAAAAAAAAA");
//		 bankOrderDAO.validateBankOrderBook(1,"SG");
//		Address add2 = new Address();
//		add2.setNomCommune("bxll");
//		add2.setCodePostal("100");
//		add2.setNumero("22");
//		add2.setRue("bmmmmm");
//		
//		Fournisseur F = new Fournisseur();
//		F.setArticle("12313856789");
//		F.setNumeroRegistre("13658158858214");
//		F.setNumTva("133548881998454");
//		F.setNom("Fn1");
//		F.setAddress(add2);
////		fournisseurrDAO.save(F);
//		Facture facture = new Facture();
//		facture.setFournisseur(F);
//		facture.setTotFacture(new BigDecimal("12.5"));
//		facture.setSoldFacture(new BigDecimal("12.5"));
//		facture.setDateEcheanceFacture(new Date());
//		facture.setDateFacture(new Date());
//		facture.setEtatFacture(EtatFacture.PAYEE);
////		factureFDAO.save(facture);
//		
//		Facture facture1 = new Facture();
//		facture1.setFournisseur(F);
//		facture1.setTotFacture(new BigDecimal("22.5"));
//		facture1.setSoldFacture(new BigDecimal("22.5"));
//		facture1.setDateEcheanceFacture(new Date());
//		facture1.setDateFacture(new Date());
//		facture1.setEtatFacture(EtatFacture.PAYEE);
////		factureFDAO.save(facture1);
//		BankOrder bO = bankOrderDAO.findByID((long) 1);
//		facture1.setSoldFacture(BigDecimal.ZERO);
//		bO.getFactures().add(facture1);
//		facture.setSoldFacture(BigDecimal.ZERO);
//		bO.getFactures().add(facture);
//		bO.setDatePayement(new Date());
//		bO.setMontant(new BigDecimal("35.00"));
//		bO.setEtatCheque(EtatCheque.SIGNER);
//		bankOrderDAO.save(bO);
////		Assert.assertNotNull(bO);
//		
	}
	
}

