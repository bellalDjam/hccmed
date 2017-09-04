package be.gest.service;

import be.gest.model.BankOrder;

public interface BankOrderService {
	 void validateBankOrderBook(BankOrder bo,int num, String ser);

}
