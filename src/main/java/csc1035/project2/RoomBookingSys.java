package csc1035.project2;

import org.hibernate.Session;
import java.util.Iterator;
import java.util.List;

public class RoomBookingSys {

    Session session;

    public List<Rooms> ListOfRooms(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Rooms> roomsList = null;
        roomsList = session.createQuery("FROM rooms").list();
        for (Iterator<Rooms> iterator = roomsList.iterator(); iterator.hasNext();){
            Rooms room = iterator.next();
            System.out.print("Room no.: " + room.getRoom_number()+"\n");
            System.out.print("Type: " + room.getType()+"\n");
            System.out.println("Max capacity: " + room.getMax_capacity());
            System.out.println("Max social dist. capacity: "+room.getSocial_distancing_capacity()+"\n");
        }
        session.getTransaction().commit();
        return roomsList;

    }
}
