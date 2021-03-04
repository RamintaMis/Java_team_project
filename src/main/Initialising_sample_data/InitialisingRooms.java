import csc1035.project2.HibernateUtil;
import csc1035.project2.Rooms;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public class InitialisingRooms {
    public static void main(String args[]) throws IOException {
        CsvIntoList l = new CsvIntoList();
        List<List> data = l.readData("src/main/resources/rooms.csv");
        for (int i = 1; i < data.size(); i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Rooms r = new Rooms();
                r.setRoom_number(data.get(i).get(0).toString());
                r.setType(data.get(i).get(1).toString());
                r.setMax_capacity(Integer.parseInt(data.get(i).get(2).toString()));
                r.setSocial_distancing_capacity(Integer.parseInt(data.get(i).get(3).toString()));
                session.saveOrUpdate(r);
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

