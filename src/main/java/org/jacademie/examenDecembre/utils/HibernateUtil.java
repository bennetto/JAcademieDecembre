package org.jacademie.examenDecembre.utils;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
 
/**
*
* @author leonidas
*/
public class HibernateUtil {
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	 
	static {
		try{
			Configuration cfg = new Configuration().configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();        
		    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Log the exception.
			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void openSession(){
		sessionFactory.openSession();
	}
	
	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
	 
	public static Session beginTransaction() {
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	 
	public static void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
		
	}

	public static void flush() {
		HibernateUtil.getSession().flush();
		
	}
	
	public static void rollbackTransaction() {
		HibernateUtil.getSession().getTransaction().rollback();
	}
	 
	public static void closeSession() {
		HibernateUtil.getSession().close();
	}
	 
}