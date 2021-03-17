package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
            if (bookings.contains(book)){
                System.out.println("Booking confirmed");
            }
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

    public void findRoom(LocalDate date, int time, int duration, int people_no) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Booking> booking_query = session.createQuery("FROM booking " +
                    "WHERE date = :date")
                    .setParameter("date", date).list();
            List<Rooms> room_query = session.createQuery("FROM rooms " +
                    "WHERE max_capacity >= :people_no ")
                    .setParameter("people_no", people_no).list();
            List<Rooms> temp_rooms = new ArrayList<>();
            System.out.println("\nFinding available rooms... \n");
            for (Rooms i : room_query) {
                for (Booking j : booking_query) {
                    if (Float.parseFloat(j.getRoom_number()) == Float.parseFloat(i.getRoom_number())) {
                        if (j.getTime() > time - 1 && j.getTime() < time + duration - 1){
                            System.out.println("Room number " + i.getRoom_number() +
                                    " is unavailable between " +
                                    String.valueOf(j.getTime()) + ":00 and " +
                                    String.valueOf(j.getTime()+duration) + ":00" + "\n");
                        }
                        else {
                            System.out.println("Available room number: " + i.getRoom_number());
                            System.out.println("Room type: " + i.getType());
                            System.out.println("Max capacity: " + i.getMax_capacity());
                            System.out.println("Social distancing capacity: " + i.getSocial_distancing_capacity() + "\n");
                        }
                        temp_rooms.add(i);
                    }
                }
            }
            for (Rooms i : temp_rooms){
                room_query.remove(i);
            }
            for (Rooms i : room_query){
                System.out.println("Available room number: " + i.getRoom_number());
                System.out.println("Room type: " + i.getType());
                System.out.println("Max capacity: " + i.getMax_capacity());
                System.out.println("Social distancing capacity: " + i.getSocial_distancing_capacity() + "\n");
            }
            session.getTransaction().commit();
            } catch(HibernateException e){
                if (session != null) session.getTransaction().rollback();
                e.printStackTrace();
            } finally{
                session.close();
            }
        }


    public void timetableForRoom(String room_no){

        List<Booking> bookingByRoom = new ArrayList<>();
        for (Booking b: listOfBookings())
        {
            if (room_no.equals(b.getRoom_number())){
                bookingByRoom.add(b);
            }
        }
        System.out.println("\nTimetable for room number "+room_no+":");
        for(Booking b:bookingByRoom){
            System.out.println("\nDate: "+b.getDate()+"\nTime: "+b.getTime()+"\nModule id: "
                    +b.getModule_id()+"\nStaff id: "+b.getStaff_id()+"\n");
        }
    }

    public List<Booking> roomConfirmation(String room_number, String staff_id, String module_id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Booking> confirmation = session.createQuery(
                "FROM booking WHERE room_number = :room_number AND " +
                "staff_id = :staff_id AND module_id = :module_id").setParameter("room_number", room_number)
                .setParameter("module_id", module_id).setParameter("staff_id", staff_id).list();
        return confirmation;
    }
    public void updateRoomDetails(String room_number, String new_room_no, String new_type, int new_cap, int new_soc_cap){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Rooms r = (session.get(Rooms.class,room_number));
            if (new_room_no != null){
                r.setRoom_number(new_room_no);
            }
            if (new_type != null){
                r.setType(new_type);
            }
            if (new_cap != 0){
                r.setMax_capacity(new_cap);
            }
            if (new_soc_cap != 0){
                r.setSocial_distancing_capacity(new_soc_cap);
            }
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
