package be.gest.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.gest.dao.BankOrderDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.model.Address;
import be.gest.model.Fournisseur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class FournisseurDAOTest {

	@Autowired
	private FournisseurDAO fournisseurrDAO;
	@Autowired
	private BankOrderDAO bankOrderDAO;
	
	@Before
	@Rollback(false)
	public void save() throws ParseException{
		
		Address add = new Address();
		add.setNomCommune("bool");
		add.setCodePostal("100");
		add.setNumero("1");
		add.setRue("bolm");
		
		Address add1 = new Address();
		add1.setNomCommune("bxlool");
		add1.setCodePostal("100");
		add1.setNumero("22");
		add1.setRue("bmmmmm");


		Fournisseur F = new Fournisseur();
		F.setArticle("12113456789");
		F.setNumeroRegistre("123654158858214");
		F.setNumTva("123548871998454");
		F.setNom("Faidal");
		F.setAddress(add);
		fournisseurrDAO.save(F);
		
		Fournisseur Fsaidale = new Fournisseur();
		Fsaidale.setNom("saidal");
		Fsaidale.setArticle("13456789234");
		Fsaidale.setNumeroRegistre("223654158858214");
		Fsaidale.setNumTva("223548899845459");
		Fsaidale.setAddress(add);
		fournisseurrDAO.save(Fsaidale);
		
		Fournisseur pfizer = new Fournisseur();
		pfizer.setNom("Pfizer");
		pfizer.setArticle("12113456789");
		pfizer.setNumeroRegistre("223654158858255");
		pfizer.setNumTva("213548879845459");
		pfizer.setAddress(add1);
		fournisseurrDAO.save(pfizer);

			
	}
	@Test
	public void testFindAll() {
//		List<Fournisseur> result = fournisseurrDAO.getAll(Fournisseur.class);
//		Assert.assertNotNull(result);
	}

}
