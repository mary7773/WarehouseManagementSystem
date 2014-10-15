package entity;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/*
 * test.java
 *
 * created at Feb 11, 2014 by T.Neykov t.neykov@seeburger.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
public class hibernateTest implements Serializable
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Session s = null;
    Transaction tx = null;
    Configuration cfg =null;
    SessionFactory factory = null;
    public static void main(String[] args)
    {
        hibernateTest dbCon = new hibernateTest();
        dbCon.initDbConn();
        dbCon.insert(3, "Neykov", "test", 1, 1);
       // dbCon.delete();
       // dbCon.selectUserbyId(2);
    }
/**
 * Select user by UserID from DB
 * @param id - integer
 */
   public void selectUserbyId(int id)
   {
       Users tUser = new Users();
       tUser =(Users)s.get(Users.class,id);
       System.out.println(tUser.getUserName());
   }
/**
 * DB connecntion initialisation
 */
    @SuppressWarnings("deprecation")
	public void initDbConn()
    {
         cfg = new Configuration();
         cfg.configure("hibernate.cfg.xml");
         factory = cfg.buildSessionFactory();
         s = factory.openSession();
         tx = s.beginTransaction();
    }
/**
 *
 */
    public void insert(int id, String name, String password, int personalDetailID,int userGroupID )
    {

        UsersGroups userGroups = new UsersGroups();
        userGroups.setUsersGroupId(userGroupID);
        //Users user1 = new Users("test", "test");
        Users us = new Users();
        us.setUserId(id);
        us.setUserName(name);
        us.setPassword(password);
       // s.save(userGroups);
       //   s.save(personsDetails);
        s.save(us);
        s.flush();
        tx.commit();
        s.close();
        System.out.println("saved");
    }
    public void delete()
    {
        Users userForDelete = new Users();
        userForDelete =(Users)s.get(Users.class,new Integer(1));
        s.delete(userForDelete);
        s.flush();
        tx.commit();
        s.close();
        System.out.println("Deleted");
    }
}

