package org.jacademie.examenDecembre;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 * Hello world!
 *
 */
public class Main 
{
	private static Logger logger = Logger.getLogger(Main.class);
	
    public static void main( String[] args )
    {
logger.info("test");
    	
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    	Session session = sessionFactory.openSession();
    	
    	session.beginTransaction();
    	
    	
    	
    	
    	//session.save();
    	//session.save(lc2);
    	
    	session.getTransaction().commit();
    	
    	session.close();
    	sessionFactory.close();
    }
}
