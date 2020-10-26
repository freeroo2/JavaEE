package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import domains.User;
import domains.Mapping;
import domains.SessionStorage;


public class HibernateUtil {
	 private static final SessionFactory sessionFactory;
	  

	    static {
	    	
	    	//获取hibernate.properties或hibernate.cfg.xml的配置信息
	    	final Configuration configuration = new Configuration();
			configuration.addAnnotatedClass( User.class );
			configuration.addAnnotatedClass( Mapping.class );
			configuration.addAnnotatedClass( SessionStorage.class );
			//创建sessionFactory
			sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );
	    }
	   
	    //从SessionFactory中获取Session
	    public static Session getSession(){
	        return sessionFactory.openSession();
	    }

	  

}
