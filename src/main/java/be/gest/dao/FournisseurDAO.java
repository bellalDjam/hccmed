package be.gest.dao;

import java.util.List;

import be.gest.model.Facture;
import be.gest.model.Fournisseur;


public interface FournisseurDAO extends AbstractDao<Fournisseur> {
	
List<Fournisseur> findAll();	
	Fournisseur findFournisseurById(Long id);
	 List<Fournisseur> findImpayedFournisseur(Facture factur);

}
