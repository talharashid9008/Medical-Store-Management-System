package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.StorePharmacist;
import com.medical_store_management_system.Business_Logic.StoreSalesman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import java.util.List;


/*
*   This class provides the interface to the table of store pharmacists in the database and provides necessary queries that will be used in the m
*   medical store management system
*/

public class StorePharmacist_Connection_DB {

    //------------------------------------ Add A New Pharmacist Object in The Database VIA Hibernate ---------------------------------------------
    public static boolean insertPharmacist(StorePharmacist newPharmacist) {
        boolean insertStatus = false;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        if (newPharmacist != null) {
            try {
                session.save(newPharmacist);
                trans.commit();
                insertStatus = true;
            } catch (PersistenceException E) {
                System.out.println(E);
            }

        }

        session.close();
        return insertStatus;
    }

    public static ObservableList<StorePharmacist> fetchAllPharmacistRecords() {

        ObservableList<StorePharmacist> allPharmacists= FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        List pharmacists = session.createQuery("From StorePharmacist ").list();
        try {
            trans.commit();
        } catch (Exception E) {
            System.out.println(E);
        }
        session.close();

        //-- Observable List Return For The Table View Of The Table
        allPharmacists.setAll(pharmacists);
        return allPharmacists;
    }


    public static StorePharmacist findStorePharmacist(String pharmacistloginUserName, String pharmacistloginPassword) {

        StorePharmacist findPharmacist=null;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        //---------------------------- HQL Qurey -----------------------------------------------------------------------
        String hql = "FROM StorePharmacist  WHERE pharmacistLoginUserName=:loginUserName AND pharmacistLoginPassword=:loginPassword";
        Query query = session.createQuery(hql);
        query.setParameter("loginUserName",pharmacistloginUserName);
        query.setParameter("loginPassword",pharmacistloginPassword);

        List<StorePharmacist> results = ((org.hibernate.query.Query<StorePharmacist>) query).list();

        if(results.size()!=0)
        {
            findPharmacist=results.get(0);
        }
        else
        {
            findPharmacist=null;
        }
        return findPharmacist;

    }


    public static boolean deleteParticularPharmacistRecord(StorePharmacist deletePharmacist) {
        boolean deleteStatus = false;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (deletePharmacist != null) {
            try {
                //------ HQL Hibernate Query --------------------------------------
                String hql = "DELETE FROM StorePharmacist WHERE pharmacistCnic=:pharmacistCNIC";
                Query query = session.createQuery(hql);
                query.setParameter("pharmacistCNIC", deletePharmacist.getPharmacistCnic());

                //---- Perform Execute -------------------------------------------------
                int result = query.executeUpdate();

                //Update Result Status
                if (result > 0)
                    deleteStatus = true;
                trans.commit();
            } catch (PersistenceException E) {
                deleteStatus = false;
                System.out.println(E);
            }

        }
        session.close();
        return deleteStatus;
    }



}
