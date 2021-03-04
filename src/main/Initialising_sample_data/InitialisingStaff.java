import csc1035.project2.HibernateUtil;
import csc1035.project2.Staff;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public class InitialisingStaff {
    public static void main(String args[]) throws IOException {
        CsvIntoList l = new CsvIntoList();
        List<List> data = l.readData("src/main/resources/staff.csv");
        for (int i = 1; i < data.size(); i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Staff s = new Staff();
                s.setId(data.get(i).get(0).toString());
                s.setFirst_name(data.get(i).get(1).toString());
                s.setLast_name(data.get(i).get(2).toString());
                session.saveOrUpdate(s);
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

