/*
 * GetConnectionFactory.java
 *
 * created at Mar 14, 2014 by Todor e-mail: TodorNeykov@gmail.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package guiComponents;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class GetConnectionFactory
{
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    protected GetConnectionFactory()
    {

    }
    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                                                                  .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        }

        return sessionFactory;

    }

}
