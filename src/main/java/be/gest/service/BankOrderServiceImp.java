package be.gest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gest.dao.BankOrderDAO;
import be.gest.model.BankOrder;

@Service("BankOrderService")
public class BankOrderServiceImp implements BankOrderService{
	@Autowired BankOrderDAO bODao;
	BankOrder mat1 = new BankOrder();
	
	public void validateBankOrderBook(BankOrder mat,int numDepart, String serie) {
		if(serie ==null){
			for( int i=0 ; i <50 ;i++){ 
				mat.setDate_validation(new Date());
				mat.setNumero_depart(numDepart);
				mat.setNumero_cheque(numDepart+i);
				bODao.save(mat);
				}
		}else{
			for(int i=0 ; i <50 ;i++){ 
				mat.setDate_validation(new Date());
				mat.setNumero_depart(numDepart);
				mat.setNumero_cheque(numDepart+i);
				mat.setSerie_cheque(serie);
				bODao.save(mat);
				}
		}
	}
}
