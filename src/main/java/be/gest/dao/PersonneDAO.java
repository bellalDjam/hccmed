package be.gest.dao;

import java.util.List;

import be.gest.model.Personne;

public interface PersonneDAO extends AbstractDao<Personne> {

	List<Personne> findAll();

}
