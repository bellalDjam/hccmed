package be.gest.model;

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

import be.gest.dao.DepartementDAO;
import be.gest.model.Address;
import be.gest.model.Departement;
import be.gest.model.Fournisseur;
import be.gest.model.MoyenContact;
import be.gest.model.enumeration.TypeDepartement;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class DepartementDAOTest{
	
	
	@Autowired
	private DepartementDAO departementDAO;

	
	@Before
	public void saveDepartement() {
		
		MoyenContact mc = new MoyenContact();
		mc.setFax("02345678l");
		mc.setTelephone("04567890");
		mc.setGsm("04567890");
		mc.setEmail("bouchra@hotmail.fr");
		
		

		Address add = new Address();
		add.setNomCommune("bxl");
		add.setCodePostal("1000");
		add.setNumero("1");
		add.setRue("alfa");
		
		Fournisseur F = new Fournisseur();
		F.setNom("Fnac");
		F.setAddress(add);
		F.setArticle("123456789");
		F.setNumeroRegistre("12365458858");
		F.setNumTva("1235488799859");
		F.setMoyenContact(mc);
		
		
		Departement departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.ARABEL.name());
		departement.setTypeDepartement(TypeDepartement.ARABEL);
		
		departementDAO.save(departement);
		
		
		departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.AGENCE.name());
		departement.setTypeDepartement(TypeDepartement.AGENCE);
		departementDAO.save(departement);
		
		departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.AMANA.name());
		departement.setTypeDepartement(TypeDepartement.AMANA);
		
		departementDAO.save(departement);
		
		departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.BRICO.name());
		departement.setTypeDepartement(TypeDepartement.BRICO);
		
		departementDAO.save(departement);
		departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.AXA.name());
		departement.setTypeDepartement(TypeDepartement.AXA);
		
		departementDAO.save(departement);
		 departement = new Departement();
		departement.setAddress(add);
		departement.setNom(TypeDepartement.DELHEIZ.name());
		departement.setTypeDepartement(TypeDepartement.DELHEIZ);
		
		departementDAO.save(departement);
		
	}
	
	@Test
	public void findMateriel() {
//		
//		List<Departement> result = departementDAO.getAll(Departement.class);
//		
//		Assert.assertEquals(TypeDepartement.ARABEL, result.get(0).getTypeDepartement());
		
	}
}
