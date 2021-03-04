import java.io.IOException;
import java.util.List;

import csc1035.project2.Modules;
import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class InitialisingModules {
    public static void main(String args[]) throws IOException {
        CsvIntoList l = new CsvIntoList();
        List<List> data = l.readData("src/main/resources/modules.csv");
        for (int i = 1; i < data.size(); i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Modules m = new Modules(data.get(i).get(0).toString(),
                        data.get(i).get(1).toString(),
                        Integer.parseInt(data.get(i).get(2).toString()),
                        Integer.parseInt(data.get(i).get(3).toString()));
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

