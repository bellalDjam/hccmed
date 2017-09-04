package be.gest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import be.gest.model.Personne;

@Repository("personneDAO")
public class PersonneDAOImpl extends AbstractDaoImpl<Personne> implements PersonneDAO {

	public List<Personne> findAll() {
		return this.getAll(Personne.class);
	}


}
