package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.PurchasedMedicines;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;

public class  Purchased_Medicines_Connection_DB {

    //---------------------------- Insert New Medicine Sold Record With Bill Number As A Foreign Key -----------------------
    public static boolean insertNew_MedicinesInCartRecord(PurchasedMedicines newMedicinesInCart) {
        boolean newBillInsertStatus = false;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.PurchasedMedicines.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (newMedicinesInCart != null) {
            try {
                newMedicinesInCart.setBillNo(newMedicinesInCart.getBillNo());
                session.save(newMedicinesInCart);
                trans.commit();
                newBillInsertStatus = true;
            } catch (PersistenceException E) {
                newBillInsertStatus = false;
                System.out.println(E);
            }
        }
        session.close();
        return newBillInsertStatus;
    }
}
