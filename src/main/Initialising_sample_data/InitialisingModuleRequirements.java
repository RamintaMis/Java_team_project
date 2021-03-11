import csc1035.project2.HibernateUtil;
import csc1035.project2.ModuleRequirements;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class InitialisingModuleRequirements {
    public static void main(String args[]) throws IOException {
        CsvIntoList l = new CsvIntoList();
        List<List> data = l.readData("src/main/resources/module_requirements.csv");
        for (int i = 1; i < data.size(); i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                ModuleRequirements m = new ModuleRequirements();
                m.setId(data.get(i).get(0).toString());
                m.setWeek_commencing(Date.valueOf(data.get(i).get(1).toString()));
                m.setNo_of_lectures(Integer.parseInt(data.get(i).get(2).toString()));
                m.setLecture_length(Integer.parseInt(data.get(i).get(3).toString()));
                m.setNo_of_practicals(Integer.parseInt(data.get(i).get(4).toString()));
                m.setPractical_length(Integer.parseInt(data.get(i).get(5).toString()));
                session.saveOrUpdate(m);
                session.getTransaction().commit();
                }
            catch (HibernateException e) {
                if (session != null) session.getTransaction().rollback();
                e.printStackTrace();
                System.out.println("Rollback");
            }
            finally {
                session.close();
            }
        }
    }
}

