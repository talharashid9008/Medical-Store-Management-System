package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.Medicines;
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

public class StoreSalesman_Connection_DB {

    //-------------------------------------- Insert New Salesman In A Database -----------------------------------------
    public static Boolean insertNewSalesMan(StoreSalesman newSalesMan) {
        Boolean salesmanInsertStatus = false;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        if (newSalesMan != null) {
            try {
                session.save(newSalesMan);
                trans.commit();
                salesmanInsertStatus = true;
            } catch (PersistenceException E) {
                System.out.println(E);
            }

        }
        session.close();
        return salesmanInsertStatus;
    }

    //------------------------- Fetch All the Sales Man Records --------------------------------------------------------
    public static ObservableList<StoreSalesman> fetchAllSalesManRecords() {
        ObservableList<StoreSalesman> allStoreSalesManList = FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        List salesmanList = session.createQuery("From StoreSalesman ").list();
        try {
            trans.commit();
        } catch (Exception E) {
            System.out.println(E);
        }
        session.close();

        //-- Observable List Return For The Table View Of The Table
        allStoreSalesManList.setAll(salesmanList);
        return allStoreSalesManList;
    }

    //-------------------------- Find Salesman Record from the database ------------------------------------------------
    public static StoreSalesman findStoreSalesMan(String loginUserName, String loginPassword) {
        StoreSalesman findSalesMan=null;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(StorePharmacist.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        //---------------------------- HQL Qurey -----------------------------------------------------------------------
        String hql = "FROM StoreSalesman  WHERE salesmanLoginUserName=:loginUserName AND salesmanLoginPassword=:loginPassword";
        Query query = session.createQuery(hql);
        query.setParameter("loginUserName",loginUserName);
        query.setParameter("loginPassword",loginPassword);

        List<StoreSalesman> results = ((org.hibernate.query.Query<StoreSalesman>) query).list();

        if(results.size()!=0)
        {
            findSalesMan=results.get(0);
        }
        else
        {
            findSalesMan=null;
        }
        return findSalesMan;
    }


    //------------------------------------ Delete A Particular Medicine From The Record --------------------------------------------------------------------------
    public static boolean deleteAParticularSalmanRecord(StoreSalesman deleteASalesman) {
        boolean deleteStatus = false;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.StoreSalesman.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (deleteASalesman != null) {
            try {
                //------ HQL Hibernate Query --------------------------------------
                String hql = "DELETE FROM StoreSalesman WHERE salesmanCnic=:salesmanCNIC";
                Query query = session.createQuery(hql);
                query.setParameter("salesmanCNIC", deleteASalesman.getSalesmanCnic());

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


//    public static void main(String[] args) {
//        System.out.println(findStoreSalesMan("imran123","1234"));
//    }
}
