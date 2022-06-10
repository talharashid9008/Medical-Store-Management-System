package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.StoreAdmin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class StoreAdmin_Connection_DB {

    //------------------------------------- Find Store Admin ---------------------------------------------------------------------------------------------------
    public static StoreAdmin findStoreAdmin(String loginUserName, String loginPassword) {
        StoreAdmin findAdmin = null;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(StoreAdmin.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        //---------------------------- HQL Qurey -----------------------------------------------------------------------
        String hql = "FROM StoreAdmin  WHERE adminLoginUserName=:loginUserName AND adminLoginPassword=:loginPassword";
        Query query = session.createQuery(hql);
        query.setParameter("loginUserName", loginUserName);
        query.setParameter("loginPassword", loginPassword);

        List<StoreAdmin> results = ((org.hibernate.query.Query<StoreAdmin>) query).list();

        if (results.size() !=0) {
            findAdmin = results.get(0);
        } else {
            findAdmin = null;
        }

        return findAdmin;
    }

    //------------------------------------- Find Store Admin ---------------------------------------------------------------------------------------------------
    public static Boolean insertAdmin(StoreAdmin newAdmin) {
        Boolean insertStatus=false;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(StoreAdmin.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        if (newAdmin != null) {
            try {
                session.save(newAdmin);
                trans.commit();
                insertStatus = true;
            } catch (PersistenceException E) {
                insertStatus = false;
                System.out.println(E);
            }
        }
        session.close();
        return insertStatus;
    }


}
