package be.gest.dao;

import java.util.List;

import be.gest.model.Address;

public interface AdresseDAO extends AbstractDao<Address> {
	List<Address> findAll();
	Address findAdresseByID(Long id);
	
	

}
