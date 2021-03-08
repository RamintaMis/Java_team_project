package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class RoomBookingSys {

    Session session;

    public List<Rooms> listOfRooms(){
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

    public void reserveRoom(String staff_id, String room_no, String module_id,
                            int time, LocalDate date, boolean socially_distanced){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Booking book = new Booking();
            book.setStaff_id(staff_id);
            book.setRoom_number(room_no);
            book.setModule_id(module_id);
            book.setTime(time);
            book.setDate(date);
            book.setSocially_distanced(socially_distanced);
            session.save(book);
            session.getTransaction().commit();
        }catch(HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void cancelRoomReservation(String staff_id, String room_no, String module_id,
                                      int time, LocalDate date, boolean socially_distanced){

    }
}
