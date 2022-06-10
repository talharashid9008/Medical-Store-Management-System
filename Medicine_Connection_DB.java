package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.Medicines;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class Medicine_Connection_DB {

    //------------------------ Insert A New Medicine In the Medicine Table in the Database ------------------------------------------------------
    public static boolean insertNewMedicine(Medicines newMedicine) {
        boolean insertMedicineStatus = false;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (newMedicine != null) {
            try {
                session.save(newMedicine);
                trans.commit();
                insertMedicineStatus = true;
            } catch (PersistenceException E) {
                insertMedicineStatus = false;
                System.out.println(E);
            }

        }
        session.close();
        return insertMedicineStatus;
    }

    //--------------------- Fetch All the Medicines From The Table of Medical Store Database ---------------------------------------------------
    public static ObservableList<Medicines> fetchAllMedicines() throws Exception {
        ObservableList<Medicines> allMedicineList = FXCollections.observableArrayList();

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        List allMedicines = session.createQuery("From Medicines ").list();
        try {
            trans.commit();

        } catch (Exception E) {
            System.out.println(E);
        }
        session.close();

        //-- Observable List Return For The Table View Of The Table
        allMedicineList.setAll(allMedicines);
        return allMedicineList;
    }

    //----------------------------------- Update A Medicine Record In The Database Table ------------------------------------------------------------------------
    public static boolean updateMedicineDetails(Medicines updatedMedicineDetails)
    {
        boolean updateStatus=false;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        //---------------------------- HQL Qurey -----------------------------------------------------------------------
        String hql = "UPDATE Medicines SET medicineBatchNo=:newBatchNO," +
                " medicineName=:newMedName," +
                " medicineRetailPrice=:newRetailPrice," +
                "medicineDealerPrice=:newDealerPrice," +
                "medicineManufacturerName=:newManufactureName," +
                "medicineDosage=:newDossage," +
                "medicineDateOfExpiry=:newExpiryDate," +
                "medicineDateOfManufacture=:newManufactureDate," +
                "medicineQuantity=:newMedicineQuantity," +
                "medicineDescription=:newDescription," +
                "medicineType=:newType," +
                "medicineGenericName=:newGenericName" +
                " WHERE medicineDrugCode=:drugCode";

        Query query = session.createQuery(hql);
        query.setParameter("newBatchNO",updatedMedicineDetails.getMedicineBatchNo());
        query.setParameter("newMedName",updatedMedicineDetails.getMedicineName());
        query.setParameter("newRetailPrice",updatedMedicineDetails.getMedicineRetailPrice());
        query.setParameter("newDealerPrice",updatedMedicineDetails.getMedicineDealerPrice());
        query.setParameter("newManufactureName",updatedMedicineDetails.getMedicineManufacturerName());
        query.setParameter("newDossage",updatedMedicineDetails.getMedicineDosage());
        query.setParameter("newManufactureDate",updatedMedicineDetails.getMedicineDateOfManufacture());
        query.setParameter("newMedicineQuantity",updatedMedicineDetails.getMedicineQuantity());
        query.setParameter("newDescription",updatedMedicineDetails.getMedicineDescription());
        query.setParameter("newType",updatedMedicineDetails.getMedicineType());
        query.setParameter("newGenericName",updatedMedicineDetails.getMedicineGenericName());
        query.setParameter("drugCode",updatedMedicineDetails.getMedicineDrugCode());
        query.setParameter("newExpiryDate",updatedMedicineDetails.getMedicineDateOfExpiry());

        int status= query.executeUpdate();
        if (status >0)
            updateStatus=true;
        else
            updateStatus=false;

        trans.commit();
        session.close();


        System.out.println(status);
        return updateStatus;
    }


    //------------------------------------ Delete A Particular Medicine From The Record --------------------------------------------------------------------------
    public static boolean deleteParticularMedicine(Medicines deletedMedicine) {
        boolean deleteStatus = false;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (deletedMedicine != null) {
            try {
                //------ HQL Hibernate Query --------------------------------------
                String hql = "DELETE FROM Medicines WHERE medicineDrugCode=:drugCode";
                Query query = session.createQuery(hql);
                query.setParameter("drugCode", deletedMedicine.getMedicineDrugCode());

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

    //------------------------------------ Find A Medicine -------------------------------------------------------------------------------------------------------
    public static Medicines getMedicineRecord(String medicineDrugCode)
    {
        Medicines medicineRecord=null;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        String hql = "FROM Medicines  WHERE medicineDrugCode=:medDrugCode";
        Query query = session.createQuery(hql);
        query.setParameter("medDrugCode",medicineDrugCode);

        List<Medicines> searchedMedicineRecord = ((org.hibernate.query.Query<Medicines>) query).list();

        try {
            trans.commit();

        } catch (Exception E) {
            System.out.println(E);
        }
        session.close();

        if(searchedMedicineRecord.size() !=0)
        {
            medicineRecord=searchedMedicineRecord.get(0);
        }
        return medicineRecord;
    }

    //------------------------------------------------ Update Medicine Quantity ---------------------------------------------------------------------
    public static boolean updateMedicineQuantity(String medicineDrugCode, int requiredQuantity)
    {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Medicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Medicines requiredMedicine=getMedicineRecord(medicineDrugCode);

        int newQuantityOfMedicine=requiredMedicine.getMedicineQuantity()-requiredQuantity;

        String hql = "UPDATE Medicines set medicineQuantity=:updatedQuantity WHERE medicineDrugCode=:medDrugCode";
        Query query = session.createQuery(hql);
        query.setParameter("medDrugCode",medicineDrugCode);
        query.setParameter("updatedQuantity",newQuantityOfMedicine);

        int status= query.executeUpdate();
        trans.commit();
        session.close();

        return status > 0;
    }

}
