package be.gest.service;

import java.math.BigDecimal;

public interface PayementService {

	BigDecimal totToPayeToFournisseur(Long f);
	BigDecimal totPayedToFournisseur(Long f);

	BigDecimal totFournisseur(Long id);

}
