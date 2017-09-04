package be.gest.dao;

import java.util.List;

import be.gest.model.Departement;


public interface DepartementDAO extends AbstractDao<Departement> {

	List<Departement> findAll();

}
