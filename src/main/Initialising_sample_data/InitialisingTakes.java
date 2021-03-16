import csc1035.project2.HibernateUtil;
//import csc1035.project2.Takes;
import csc1035.project2.Takes;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public class InitialisingTakes {
    public static void main(String args[]) throws IOException {
        CsvIntoList l = new CsvIntoList();
        List<List> data = l.readData("src/main/resources/takes.csv");
        for (int i = 1; i < data.size(); i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Takes t = new Takes();
                t.setSid(Integer.parseInt(data.get(i).get(0).toString()));
                t.setMid(data.get(i).get(1).toString());
                session.saveOrUpdate(t);
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

