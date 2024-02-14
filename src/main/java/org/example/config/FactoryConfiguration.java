package org.example.config;

import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfigaration;
    public SessionFactory sessionFactory;

    private FactoryConfiguration(){

        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Author.class).addAnnotatedClass(Book.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfigaration == null? factoryConfigaration = new FactoryConfiguration():factoryConfigaration;

    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
