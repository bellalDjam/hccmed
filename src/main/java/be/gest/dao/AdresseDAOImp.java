package be.gest.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.gest.model.Address;

@Repository("AdresseDAO")
public class AdresseDAOImp extends AbstractDaoImpl<Address> implements AdresseDAO, Serializable  { 
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8428186583240411607L;
public List<Address> findAll() {
	return this.getAll(Address.class);
}

@Override
public Address  findAdresseByID(Long id) {
	if(id != null) {
		System.out.println("le id doit exister");
	}
	return getById(Address.class,id);

}
@Override
public void save(Address adresse){
	save(adresse);
	
}

}
