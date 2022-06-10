package com.medical_store_management_system.Database_Connectivity;

import com.medical_store_management_system.Business_Logic.Bills;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.awt.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class Bills_Connection_DB {
    //--------------------------------- Insert A New Bill In The Database ----------------------------------------------
    public static boolean insertNewBill(Bills newBill)
    {
        boolean newBillInsertStatus = false;
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.Bills.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        if (newBill != null) {
            try {
                session.save(newBill);
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

    public static int getTotalSalesOfToday(LocalDate todayDate)
    {
        int totalSalesToday=0;

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(com.medical_store_management_system.Business_Logic.Bills.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        List allBills=session.createQuery("FROM Bills ").list();
        Iterator it=allBills.iterator();

        while (it.hasNext())
        {
            Bills currentBill=(Bills) it.next();
            if(currentBill.getDateTimeOfBill().toString().contains(todayDate.toString()))
            {
                System.out.println(currentBill);
                totalSalesToday += currentBill.getTotalPayableBill();
            }
        }
        trans.commit();
        session.close();
        return totalSalesToday;

    }
}
