package csc1035.project2;

import org.hibernate.Session;


import java.util.Iterator;
import java.util.List;

/**
 * This class contains all the functions for the timetabling system, that would allow you to return a
 * list of students that take a module, return a list of staff that work on a module, return the
 * list of module requirements, create a timetable for the school under both normal and socially
 * distanced conditions, and return a timetable for any given member of staff or student.
 *
 * @author Oscar
 */
public class TimetablingSys {

    Session session;

    /**
     * this function would access the 'takes' table and return all the students that take
     * a user given module
     * @param mid a String of the ID of a user provided module
     * @return Returns a List of the Students that take a given module
     */
    public List<Students> listOfStudentsByModule(String mid){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Students> studentList = null;
        studentList = session.createQuery("SELECT sid FROM takes" +
                "WHERE mid = :mid").setParameter("mid", mid).list();
        session.getTransaction().commit();
        return studentList;
    }

    /**
     * Accesses the 'teaches' table and returns a list of staff members that teach a given
     * module
     * @param module_id a user inputted String the uniquely represents the module you're looking for
     * @return Returns a list of Staff objects that teach the given module
     */
    public List<Staff> listOfStaffByModule(String module_id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Staff> staffList = null;
        staffList = session.createQuery("SELECT staff_id FROM teaches " +
                "WHERE module_id = :module_id").setParameter("module_id", module_id).list();
        session.getTransaction().commit();
        return staffList;
    }

    /**
     * Accesses the 'module_requirements' table and finds and returns the details of a user inputted
     * module
     * @param id a user inputted String module ID that uniquely represents the module
     * @return Returns a list of ModuleRequirements objects corresponding to the user inputted module
     */
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
