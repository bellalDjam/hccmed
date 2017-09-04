package be.gest.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.gest.model.Departement;


@Repository("departementDAO")
public class DepartementDAOImpl extends AbstractDaoImpl<Departement> implements DepartementDAO, Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1444769132140391401L;

	public List<Departement> findAll() {
		return this.getAll(Departement.class);
	} 
	

}