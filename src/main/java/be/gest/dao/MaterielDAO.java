package be.gest.dao;

import java.util.List;

import be.gest.model.Materiel;
import be.gest.model.enumeration.TypeMateriel;


public interface MaterielDAO extends AbstractDao<Materiel> {
	List<Materiel> findAll();
	
	List<Materiel> findMaterielsDisponible(TypeMateriel typeMateriel);

}
