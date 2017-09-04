package be.gest.service;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gest.dao.FactureDAO;
import be.gest.dao.FournisseurDAO;
import be.gest.dao.PayementDAO;
import be.gest.model.Fournisseur;

@Service("PayementService")
public class PayementServiceImpl implements PayementService,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5188289066718330851L;
	@Autowired
	PayementDAO payementDao;
	@Autowired 
	FactureDAO facturDao;
	@Autowired 
	FournisseurDAO fournissuerDao;
	
	@Override
	public BigDecimal totToPayeToFournisseur(Long  id){
	Fournisseur	f= fournissuerDao.findFournisseurById(id);
		return facturDao.getSumFacturesList(facturDao.findAllFacIdImpayedByFounisseur(f));
			}
	public BigDecimal totFournisseur(Long  id){
	Fournisseur	f= fournissuerDao.findFournisseurById(id);
		return facturDao.getSumFacturesList(facturDao.findAllFacIdByFounisseur(f));
			}
	@Override
	public BigDecimal totPayedToFournisseur(Long id) {
		Fournisseur	f= fournissuerDao.findFournisseurById(id);
		return facturDao.getSumFacturesList(facturDao.findAllPayedFacIdByFounisseur(f));
	}
	
	

}
