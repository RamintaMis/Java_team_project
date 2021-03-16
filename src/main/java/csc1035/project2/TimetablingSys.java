package csc1035.project2;

import org.hibernate.Session;


import java.util.Iterator;
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

    public List<ModuleRequirements> listOfModuleReq(String id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ModuleRequirements> reqList = null;
        reqList = session.createQuery( "FROM module_requirements " +
                "WHERE id = :id").setParameter("id", id).list();
        for (Iterator<ModuleRequirements> iterator = reqList.iterator(); iterator.hasNext();){
            ModuleRequirements m = iterator.next();
            System.out.println("Module id: "+m.getId() + "\n");
            System.out.println("Lecture length: "+m.getLecture_length() + "\n");
            System.out.println("No. of lectures: "+m.getNo_of_lectures() + "\n");
            System.out.println("No. of practicals: "+m.getNo_of_practicals() + "\n");
            System.out.println("Practical length: "+m.getPractical_length() + "\n");
            System.out.println("Week commencing: "+m.getWeek_commencing());
        }

        session.getTransaction().commit();
        return reqList;
    }

}
