package be.gest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gest.dao.FournisseurDAO;
import be.gest.model.Fournisseur;


@Service("FournisseurService")
public class FournisseurServiceImpl implements FournisseurService {

	@Autowired FournisseurDAO fournisseurDao;

	@Override
	public void save(Fournisseur f) {

		fournisseurDao.save(f);
	}

	@Override
	public void delete(Fournisseur f) {

		fournisseurDao.delete(f);
	}
	
	

}
