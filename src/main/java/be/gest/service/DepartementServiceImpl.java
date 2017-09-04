package be.gest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gest.dao.DepartementDAO;
import be.gest.model.Departement;

@Service("DepartementService")
public class DepartementServiceImpl implements DepartementService {
	
	@Autowired DepartementDAO departementDAO;
	
	public void save(Departement d) {
	
		 departementDAO.save(d);
		
	}
	
}
