package be.gest.model;

import java.math.BigDecimal;
import java.util.Date;
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

import be.gest.dao.MaterielDAO;
import be.gest.model.Address;
import be.gest.model.Departement;
import be.gest.model.Fournisseur;
import be.gest.model.Materiel;
import be.gest.model.MoyenContact;
import be.gest.model.enumeration.EtatMateriel;
import be.gest.model.enumeration.TypeDepartement;
import be.gest.model.enumeration.TypeMateriel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:**/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

@Transactional
public class MaterielDAOTest{
	
	
	@Autowired
	private MaterielDAO materielDAO;
	
	
	@Before
	@Rollback(false)
	public void saveMateriel() {
		
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
		F.setNom("SAIDAL");
		F.setArticle("12332145665");
		F.setNumTva("123321456651232");
		F.setNumeroRegistre("123321456651232");
		F.setAddress(add);
		F.setMoyenContact(mc);
		
		
		Departement departement = new Departement();
		departement.setAddress(add);
		departement.setNom("Arabel");
		departement.setTypeDepartement(TypeDepartement.ARABEL);
		
		Materiel m = new Materiel();
		m.setCodeMateriel("1");
		m.setDescription("Ordinateur");
		m.setTypeMateriel(TypeMateriel.INFORMATIQUE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(10));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("2");
		m.setDescription("Clavier");
		m.setTypeMateriel(TypeMateriel.INFORMATIQUE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(20));
		materielDAO.save(m);
		
		 m = new Materiel();
		m.setCodeMateriel("3");
		m.setDescription("Ecran");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(30));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("4");
		m.setDescription("Ecran");
		m.setTypeMateriel(TypeMateriel.INFORMATIQUE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(40));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("5");
		m.setDescription("Armoire");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("6");
		m.setDescription("Souris");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("7");
		m.setDescription("Vélo");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.UTULISE);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("8");
		m.setDescription("Crayon");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.NOUVEAU);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("9");
		m.setDescription("Imprimante");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.UTULISE);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
		m = new Materiel();
		m.setCodeMateriel("10");
		m.setDescription("Dave");
		m.setTypeMateriel(TypeMateriel.JOURNALISTE);
		m.setNumeroSerie("7834878347");
		m.setPrixAchat(new BigDecimal(3));
		m.setNumeroFacture("6784563");
		m.setDateAchat(new Date());
		m.setEtatMateriel(EtatMateriel.UTULISE);
		m.setDepartement(departement);
		m.setFournisseur(F);
		m.setDisponibilite(true);
		m.setPrixLocationJour(new BigDecimal(50));
		materielDAO.save(m);
		
	}
	
	@Test
	public void findMateriel() {
		
//		List<Materiel> result = materielDAO.findAll();
//		
//		Assert.assertEquals(EtatMateriel.NOUVEAU, result.get(0).getEtatMateriel());
//		
//		
//		result = materielDAO.findMaterielsDisponible(TypeMateriel.INFORMATIQUE);
//		
//		Assert.assertNotNull(result);
		
	}
}
