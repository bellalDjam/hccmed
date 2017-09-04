package be.gest.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import be.gest.model.Materiel;
import be.gest.model.enumeration.TypeMateriel;


@Repository("materielDAO")
public class MaterielDAOImpl extends AbstractDaoImpl<Materiel> implements MaterielDAO { 
	
@Override
public List<Materiel> findAll() {
	return this.getAll(Materiel.class);
}


@SuppressWarnings("unchecked")
public List<Materiel> findMaterielsDisponible(TypeMateriel typeMateriel) {

	List<Materiel> mats = sessionFactory.getCurrentSession().createCriteria(Materiel.class)
		    .add( Restrictions.eq("typeMateriel", typeMateriel) )
		    .add( Restrictions.eq("disponibilite", Boolean.TRUE) )
		    .list();
	
	return mats;
}

}
