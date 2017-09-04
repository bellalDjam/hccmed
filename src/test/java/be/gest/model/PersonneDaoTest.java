package be.gest.model;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.gest.dao.PersonneDAO;
import be.gest.model.Address;
import be.gest.model.Departement;
import be.gest.model.Personne;
import be.gest.model.enumeration.TypeDepartement;
import be.gest.model.enumeration.TypePersonne;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class PersonneDaoTest{
	
	@Autowired
	private PersonneDAO personneDAO;

	
	@Test
	@Rollback(false)
	public void savePersonne() {
		
		Address add = new Address();
		add.setNomCommune("bxl");
		add.setCodePostal("1000");
		add.setNumero("1");
		add.setRue("bxl");
		
		Departement departement = new Departement();
		departement.setAddress(add);
		departement.setNom("brico");
		departement.setTypeDepartement(TypeDepartement.ARABEL);
		
		Personne p = new Personne();
		p.setDateNaissance(new Date());
		p.setNom("Bouchra");
		p.setPrenom("Bouchra");
		p.setTypePersonne(TypePersonne.INTERNE);
		
		p.setDepartement(departement);
		p.setAddress(add);
		
		personneDAO.save(p);

		
		p = new Personne();
		p.setDateNaissance(new Date());
		p.setNom("Pierre");
		p.setPrenom("Olivier");
		p.setTypePersonne(TypePersonne.EXTERNE);
		
		p.setDepartement(departement);
		p.setAddress(add);
		
		personneDAO.save(p);

		
		p = new Personne();
		p.setDateNaissance(new Date());
		p.setNom("Delhaye");
		p.setPrenom("Marc");
		p.setTypePersonne(TypePersonne.INTERNE);
		
		p.setDepartement(departement);
		p.setAddress(add);
		
		personneDAO.save(p);
		
		p = new Personne();
		p.setDateNaissance(new Date());
		p.setNom("Jean");
		p.setPrenom("Marado");
		p.setTypePersonne(TypePersonne.EXTERNE);
		
		p.setDepartement(departement);
		p.setAddress(add);
		
		personneDAO.save(p);
		
		p = new Personne();
		p.setDateNaissance(new Date());
		p.setNom("Isablee");
		p.setPrenom("Hondtis");
		p.setTypePersonne(TypePersonne.INTERNE);
		
		p.setDepartement(departement);
		p.setAddress(add);
		
		personneDAO.save(p);
	}
	@Test
	public void findAll() {
		
//		List<Personne> result = personneDAO.getAll(Personne.class);
//		
//		Assert.assertNotNull(result);
		
	}
}
