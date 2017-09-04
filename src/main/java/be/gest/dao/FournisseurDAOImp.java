package be.gest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import be.gest.model.Facture;
import be.gest.model.Fournisseur;

@Repository("FournisseurDAO")
public class FournisseurDAOImp extends AbstractDaoImpl<Fournisseur> implements FournisseurDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<Fournisseur> findAll() {
		return this.getAll(Fournisseur.class);
	}

	@Override
	public Fournisseur findFournisseurById(Long id) {
		return getById(Fournisseur.class, id);
	}
	
	public void save(Fournisseur fournisseur){
	
		super.save(fournisseur);
		
	}
	
	@Transactional
	public List<Fournisseur> findImpayedFournisseur(Facture factur){
		@SuppressWarnings({ "unchecked", "static-access" })
		List<Fournisseur> impayedFournisseurs= sessionFactory.getCurrentSession().createCriteria(Fournisseur.class)
				.add( Restrictions.eq("etatfacture", factur.getEtatFacture().NONPAYEE) )
			    .list();
		return impayedFournisseurs;
		
	}

}
