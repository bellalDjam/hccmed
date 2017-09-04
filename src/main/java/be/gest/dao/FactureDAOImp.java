package be.gest.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.gest.model.BankOrder;
import be.gest.model.Facture;
import be.gest.model.Fournisseur;
import be.gest.model.Payement;
import be.gest.model.enumeration.EtatCheque;
import be.gest.model.enumeration.EtatFacture;
@Repository("FactureDAO")
public class FactureDAOImp extends AbstractDaoImpl<Facture> implements FactureDAO, Serializable{
	@Autowired
	public SessionFactory sessionFactory;
	@Autowired
	BankOrderDAO bankOrderDAO;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3865940024189084429L;

	public List<Facture> findAll() {
		return this.getAll(Facture.class);
	}

	@Override
	public Facture findFactureByID(Long id) {
		return getById(Facture.class,id);
	} 
	
	public void save(Facture facture){
		super.save(facture);
		
	}
	/**
	 * Function to sum any list of all invoice with the same date in goal to get parameter of the same provider
	 * the goal is to used as well as needed by many business logic
	 * 
	 *   @param List<factureIds>
	 *   @return
	 * */
	@SuppressWarnings("unchecked")
	public List<Long> findAllImpayedByDate(Date dateEcheanceFacture){
		List<Long> factrs;
		try {
			factrs = sessionFactory.getCurrentSession().createCriteria(Facture.class)
					.add( Restrictions.isNotNull("dateEcheanceFacture") )
				    .add( Restrictions.eq("dateEcheanceFacture", dateEcheanceFacture) )
				    .add( Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) ).setProjection(Projections.property("id"))
				    .list();
			return factrs;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		List<Long> factrs =  sessionFactory.getCurrentSession().createQuery("select fac.id FROM Facture fac where fac.etatFacture ='NONPAYEE' and fac.dateEcheanceFacture= :dateEcheanceFacture ")
//				.setParameter("dateEcheanceFacture", dateEcheanceFacture).list();
//		return factrs;
//	
	}
	/**
	 * Function to  list  all invoice 
	 * the goal is to used as well as needed by many business logic
	 * 
	 *   @param List<factureIds>
	 *   @return
	 * */
	@SuppressWarnings("unchecked")
	public List<Facture> findAllImpayed(){
		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
			    .add( Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) )
			    .addOrder( Order.asc("dateEcheanceFacture") )
			    .list();		
		return factrs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> findAllImpayedByFounisseur(Fournisseur fourn) {
		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
			    .add( Restrictions.eq("fournisseur", fourn) )
			    .add( Restrictions.or(Restrictions.eq("etatFacture", EtatFacture.NONPAYEE)
			    	              , Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) 
			                      , Restrictions.eq("etatFacture", EtatFacture.RETARDEE)
			                      , Restrictions.eq("etatFacture", EtatFacture.REPORTEE)))
			    .list();
//		List<Facture> factrs =  sessionFactory.getCurrentSession().createQuery("FROM Facture fac where fac.etatFacture ='NONPAYEE' and fac.fournisseur.id= :fournId ")
//				.setParameter("fournId", fourn.getId()).list();
		return factrs;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> findAllPayedFactures() {
//		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
//				.createAlias("bankOrders", "b")
//			    .add( Restrictions.eq("b.etatCheque", EtatCheque.SIGNER) )
//			    
//			    .addOrder( Order.asc("dateEcheanceFacture") )
//			    .list();		
//		return factrs;
//		DetachedCriteria dc = DetachedCriteria.forClass(Facture.class);
//		List<Facture> list = new ArrayList<Facture>();
//		dc.createAlias("bankOrders","bankOrder");
//		dc.add(Restrictions.ne("bankOrder.etatCheque",EtatCheque.SIGNER));
//		list = hibernateTemplate.findByCriteria(dc);
//		List<Facture> factrs =new ArrayList<>();
//		List<Facture> factrs =  sessionFactory.getCurrentSession().createQuery(" select fac.id, "
//	    		+ "fac.fournisseur.nom, "
//	    		+ "fac.etatFacture,"
//	    		+ "fac.totFacture"
//	    		+ "  fac.soldFacture,"
//	    		+ "fac.dateFacture  "
//	    		+ "fac FROM Facture fac join fac.bankOrders  order by fac.id")
//		factrs =  sessionFactory.getCurrentSession().createQuery(" select elements(fac.bankOrders) FROM Facture fac")
		List<Facture>	factrs =  sessionFactory.getCurrentSession().createQuery(" select  fac FROM Facture fac join fac.bankOrders")
				.list();
		return factrs;
	}
//	select order from ORDER as order,ITEM as item where item.itemID like 'ITM_01' and item in elements(order.items)FROM fac Facture as ,bankOrder as bankOrder where bankOrder.etatCheque like 'SIGNER'  and bankOrder elements(fac.bankOrders) fac _bankOrder order by fac.bankOrders.id"
	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> findAllPayedFacturesByBo(BankOrder bo){
		List<Facture> factrs =  sessionFactory.getCurrentSession().createQuery(" FROM fac Facture as ,bankOrder as bankOrder where bankOrder.etatCheque like 'SIGNER' ")
				.list();
		return factrs;
	}
	public List<Facture> findPayedBo(BankOrder bo){
		
		@SuppressWarnings("unchecked")
		List<Facture> factrs =  sessionFactory.getCurrentSession().createQuery(" FROM fac Facture as ,bankOrder as bankOrder where bankOrder.etatCheque like 'SIGNER'  and bankOrder elements(fac.bankOrders) ")
				.list();
		return factrs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findAllFacIdImpayedByFounisseur(Fournisseur fourn) {
//		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
//			    .add( Restrictions.eq("fournisseur", fourn) )
//			    .add( Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) )
//			    .list();
		List<Long> factrids =  sessionFactory.getCurrentSession().createQuery("select fac.id FROM Facture fac where fac.etatFacture ='NONPAYEE' and fac.fournisseur.id= :fournId ")
				.setParameter("fournId", fourn.getId()).list();
		return factrids;
	}
	@SuppressWarnings("unchecked")
	public List<Long> findAllFacIdByFounisseur(Fournisseur fourn) {

		List<Long> factrids =  sessionFactory.getCurrentSession().createQuery("select fac.id FROM Facture fac where  fac.fournisseur.id= :fournId ")
				.setParameter("fournId", fourn.getId()).list();
		return factrids;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Facture> findAllImpayedFounisseur() {
		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
				.add( Restrictions.or(Restrictions.eq("etatFacture", EtatFacture.NONPAYEE)
	    	              , Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) 
	                      , Restrictions.eq("etatFacture", EtatFacture.RETARDEE)
	                      , Restrictions.eq("etatFacture", EtatFacture.REPORTEE)))
	    
			   .addOrder( Order.asc("dateEcheanceFacture") )
 .list();
		
		return factrs;
	}
	/**
	 * Function to retrieve Id list of all payed invoices by provider  
	 * this list can be used by other  function
	 *   @param Fournisseur ID
	 *   @return List<factureIds>
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findAllPayedFacIdByFounisseur(Fournisseur fourn) {
//		List<Facture> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
//			    .add( Restrictions.eq("fournisseur", fourn) )
//			    .add( Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) )
//			    .list();
		List<Long> factrids =  sessionFactory.getCurrentSession().createQuery("select fac.id FROM Facture fac where fac.etatFacture ='PAYEE' and fac.fournisseur.id= :fournId ")
				.setParameter("fournId", fourn.getId()).list();
		return factrids;
	}

//	public BigDecimal getSumFactures(List<Long> ids) {
//		
//		@SuppressWarnings("unchecked")
//		List<Long> factrs =  sessionFactory.getCurrentSession().createQuery("FROM Facture  where id in (:ids) ")
//				.setParameter("ids", ids).list();
//		BigDecimal sum = BigDecimal.ZERO;
//		for (Long facture : factrs) {
//			sum = sum.add(findFactureByID(facture).getTotFacture());
//		}
//		return sum;
//	}

	/**
	 * Function to give total of all the non payed invoices of the deadline day
	 * @param Date 
	 * @return total in BigDecimal 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> daylyDeadlineTot(){
//		List <Facture> factList = findAllImpayed();
		List<Object> factrs =  sessionFactory.getCurrentSession().createCriteria(Facture.class)
				
			    .add( Restrictions.eq("etatFacture", EtatFacture.NONPAYEE) )
				.setProjection( Projections.projectionList()
				 .add(Projections.sum("totFacture"))
			    .add( Projections.groupProperty("dateEcheanceFacture") ))
			    .list();
		return factrs;
	}
	/**
	 * Function to give total of all the non payed invoices of the deadline day
	 * @param Date 
	 * @return total in BigDecimal 
	 */
	@SuppressWarnings("null")
	@Override
	public BigDecimal echeanceByDate(Date d) {
		List<Long> factDayList =findAllImpayedByDate(d);
		if(factDayList==null){
			return BigDecimal.ZERO;
			}else
		return getSumFacturesList(factDayList);
	}

	/**
	 * 
	 * Function to sum any list of Facture ID in parameter of the same provider
	 * the goal is to used as well as needed by many business logic
	 * 
	 *   @param List<factureIds>
	 *   @return
	 * */
	public BigDecimal getSumFacturesList(List<Long> ids) {
		if(ids==null || ids.isEmpty() ){
			return BigDecimal.ZERO;
		}
					
		BigDecimal sum = (BigDecimal) sessionFactory.getCurrentSession().createCriteria(Facture.class)
			        .setProjection(Projections.sum("totFacture"))
			        .add(Restrictions.in("id", ids))
			        .uniqueResult();	
		return sum;
	}
	public BigDecimal getSumSoldeFacturesList(List<Long> ids) {
		if(ids==null || ids.isEmpty() ){
			return BigDecimal.ZERO;
		}
					
		BigDecimal sum = (BigDecimal) sessionFactory.getCurrentSession().createCriteria(Facture.class)
			        .setProjection(Projections.sum("soldFacture"))
			        .add(Restrictions.in("id", ids))
			        .uniqueResult();	
		return sum;
	}
	
	public List<Long> findIdsFacturesList(List<Facture> fact) {
		List<Long> factrids =  new ArrayList<Long>();
		for (Facture facture : fact) {
			factrids.add(facture.getId());
		}
		
		return factrids;
	}
	/**
	 * retrieve a List of selected invoice and update them to perform  payment 
	 *   
	 * @param List<Facture> 
	 * @param BigDecimal
	 * 
	 * @return  List<Facture>
	 * */
	public List<Facture> topayListFacture(List<Facture> fact,BankOrder bO){
		List<Facture> factrids =  new ArrayList<Facture>();
		for (Facture facture : fact) {
			facture.setEtatFacture(EtatFacture.PAYEE);
			facture.setSoldFacture(BigDecimal.ZERO);
			facture.getBankOrders().add(bO);
			factrids.add(facture);
		}
		return fact;
	}
	
	/**
	 * retrieve a single invoice perform a part payment 
	 * to be completed in an other payment  
	 * @param Facture 
	 * @param BigDecimal
	 * 
	 * @return Facture Type
	 * */
	public Facture factureToSold(Facture facture, BigDecimal partPayement,Date date,BankOrder bO){
		facture.setEtatFacture(EtatFacture.RETARDEE);
		facture.setSoldFacture(partPayement);
		facture.setDateEcheanceFacture(date);
		facture.getBankOrders().add(bO);
		return facture;
	}

	@Override
	public void paySinglePart(BigDecimal toPayValue, BankOrder bankOrd, Facture fact, Date date) {
		// TODO Auto-generated method stub
		
	}
	
	/**
//	 * From panel payment Model window Invoice List and
//	 * a BankOrder are selected & updated for a partial payment
//	 * 
//	 * @param List<Facture>
//	 * @param BigDecimal  value of amount payment
//	 * @param BankOrder bO 
//	 * @param Facture lastF that we choose for the next payment 
//	 * @param BigDecimal totSumF total amount of the List<Facture>
//	 */
//	public void paySelectedFactureByBOrder(List<Facture> factures, 
//			BigDecimal montant, BankOrder bO,Facture lastF, 
//			BigDecimal totSumF,Date date ) {
//		
//		BankOrder bOUp = bankOrderDAO.bankOrderToUse(bO, montant);
//		
//	
//			if (montant.compareTo(totSumF)==-1 && lastF!=null){
//			
//			Facture fact = factures.remove(factures.indexOf(lastF));
//			Facture factUp = factureToSold(fact, 
//					totSumF.setScale(2).subtract(montant.setScale(2)), date,bO);
//			List<Facture> facturesUp = topayListFacture(factures,bO);
//			facturesUp.add(factUp);
//			bOUp.setFactures(facturesUp);
//			bankOrderDAO.save(bOUp);
//		}
//		
//	}
	
	/**
	 * From panel payment Model window Invoice List and
	 * a BankOrder are selected & updated for a final payment
	 * 
	 * @param List<Facture>
	 * @param BigDecimal amount
	 * @param BankOrder bO
	 * 
	 * see  bankOrderToUse()
	 */
//	public void paySelectedFactureByBOrderEpure(List<Facture> idffs, BigDecimal montant, BankOrder bO) {
//		
//		BankOrder bOUp = bankOrderDAO.bankOrderToUse(bO, montant);
//		
//		if (montant!=null && idffs!=null){
//			List<Facture> facturesUp =topayListFacture(idffs,bO);
//			bOUp.setFactures(facturesUp);
//			bankOrderDAO.save(bOUp);
//		}
//		
//	}
	
//	@SuppressWarnings("null")
//	@Override
//	public BigDecimal getSumFacturesObj(Collection<Facture> ) {
//		 
//		if(factures==null || factures.isEmpty() ){
//			return BigDecimal.ZERO;
//		}
//		
//		
//		BigDecimal sum = (BigDecimal) sessionFactory.getCurrentSession().createCriteria(Facture.class)
//			        .setProjection(Projections.sum("totFacture"))
//			        .add(Restrictions.in("id", findIdsFacturesList(factures)))
//			        .uniqueResult();	
//		return sum;
//	}

}
