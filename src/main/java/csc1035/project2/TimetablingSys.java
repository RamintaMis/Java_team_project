package csc1035.project2;

import org.hibernate.Session;


import java.util.List;

public class TimetablingSys {

    Session session;

   /* public List<Students> listOfStudentsByModule(String mid){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Students> studentList = null;
        studentList = session.createQuery("SELECT sid FROM takes").list();


        session.getTransaction().commit();
        return studentList;
    }*/

    public List<Staff> listOfStaffByModule(String module_id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Staff> staffList = null;
        staffList = session.createQuery("SELECT staff_id FROM teaches " +
                "WHERE module_id = :module_id").setParameter("module_id", module_id).list();
        session.getTransaction().commit();
        return staffList;
    }
    /*public List<ModuleRequirements> listOfModuleReq(){

    }*/

}
