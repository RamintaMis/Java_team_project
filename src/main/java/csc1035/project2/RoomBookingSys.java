package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomBookingSys {

    Session session;
    List<Booking> bookings= new ArrayList();

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
            bookings.add(book);
        }catch(HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void cancelRoomReservation(String staff_id, String room_no, String module_id,
                                      int time, LocalDate date){

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM booking WHERE " +
                    "staff_id = :sid AND room_number = :rno AND module_id = :mid " +
                    "AND time = :t AND date = :dat")
                    .setParameter("sid", staff_id).setParameter("rno", room_no)
                    .setParameter("mid", module_id).setParameter("t", time)
                    .setParameter("dat", date).executeUpdate();
            session.getTransaction().commit();
        }catch(HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            System.out.println("Rollback");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<Booking> listOfBookings(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Booking> bookingList = null;
        bookingList = session.createQuery("FROM booking").list();
        session.getTransaction().commit();
        return bookingList;
    }


// SQL should be correct, but the problem is returning the results

// Since this query uses two tables, defining a list using "List<Booking> availableRooms"
// or "List<Rooms> availableRooms" doesn't work as it defines the list for only one table

/*    public void findRoom(LocalDate date, int time, int duration, int people_no){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List availableRooms = session.createNativeQuery("SELECT booking.time, booking.date, " +
                    "rooms.room_number, rooms.type, rooms.max_capacity," +
                    "rooms.social_distancing_capacity FROM booking, rooms " +
                    "WHERE booking.room_number = rooms.room_number " +
                    "AND rooms.max_capacity >= :people_no " +
                    "AND booking.time != :time AND booking.date = :date")
                    .setParameter("people_no", people_no)
                    .setParameter("time", time).setParameter("date", date).list();
            for (Object i : availableRooms){
            }
        }
        catch(HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }*/


    public void timetableForRoom(String room_no){

        List<Booking> bookingByRoom = new ArrayList<>();
        for (Booking b: listOfBookings())
        {
            if (room_no.equals(b.getRoom_number())){
                bookingByRoom.add(b);
            }
        }
        System.out.println("Timetable for room number "+room_no+":");
        for(Booking b:bookingByRoom){
            System.out.println(b.getDate() + " "+b.getTime()+" "+b.getModule_id()+" "+b.getStaff_id());
        }
    }

    void updateRoomDetails(String room_number, String new_room_no, String new_type, int new_cap, int new_soc_cap){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Rooms r = (session.get(Rooms.class,room_number));
            r.setRoom_number(new_room_no);
            r.setType(new_type);
            r.setMax_capacity(new_cap);
            r.setSocial_distancing_capacity(new_soc_cap);
            session.update(r);
            session.getTransaction().commit();
        }catch(HibernateException e){
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
