package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
//
//public class HibernateUtil {
//
// private static final SessionFactory sessionFactory = buildSessionFactory();
//
// private static SessionFactory buildSessionFactory() {
//  try {
//   // Create the SessionFactory from hibernate.cfg.xml
//   return new AnnotationConfiguration().configure().buildSessionFactory();
//  } catch (Throwable ex) {
//   // Make sure you log the exception, as it might be swallowed
//   System.err.println("Initial SessionFactory creation failed." + ex);
//   throw new ExceptionInInitializerError(ex);
//  }
// }
//
// public static SessionFactory getSessionFactory() {
//  return sessionFactory;
// }
//
// public static void shutdown() {
//  // Close caches and connection pools
//  getSessionFactory().close();
// }
//
//}
// 