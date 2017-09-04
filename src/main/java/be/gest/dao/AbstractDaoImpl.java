package be.gest.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import be.gest.model.BankOrder;

@Transactional
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public T update(T object) {
		sessionFactory.getCurrentSession().update(object);
		return object;

	}

	@Override
	@Transactional
	public void save(T object) {
		sessionFactory.getCurrentSession().save(object);

	}

	@Override
	@Transactional
	public void delete(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T getById(Class<T> type, long id){
		T result = null;
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(type).add(Restrictions.eq("id", id));
			result = (T)c.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<T> getAll(Class<T> type) {
		List<T> result = null;
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(type);
			result = Collections.checkedList(c.list(), type);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void deleteAll(Class<T> type) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM "+ type.getName());
		
	}
	
}
