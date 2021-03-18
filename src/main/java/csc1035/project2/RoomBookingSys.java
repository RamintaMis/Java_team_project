package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains the hibernate instructions that calls for the data or creates the data that
 * the user has requested.
 * Once any function within this has ended, it returns to MenuIO.roomMenu. The object contains a
 * hibernation Session called session, and a Arraylist of Bookings called bookings, used in many of
 * its methods.
 */
public class RoomBookingSys {

    Session session = HibernateUtil.getSessionFactory().openSession();
    ArrayList<Booking> bookings = new ArrayList<>();

    /**
     * accesses the 'Rooms' table and returns a full list of rooms contained within an arraylist
     * of 'Rooms'
     * @return a table called roomsList which contains the data on every room in the database
     */
    public List<Rooms> listOfRooms(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List roomsList = null;
        roomsList = session.createQuery("FROM rooms").list();
        session.getTransaction().commit();
        return roomsList;
    }

    /**
     * Accesses the 'Bookings' table and adds a new 'booking' to it. The bookings are an abstract
     * representation of a room reservation, containing who booked it, where it's booked, what
     * module it's booked for, when it's going to take place and under what conditions it will
     * take place.
     *
     * @param staff_id the unique String id of the staff-member doing the booking
     * @param room_no the unique String room number that identifies an individual room
     * @param module_id the unique String id of the module that the room is being booked to teach
     * @param time the time of dat that the room is being booked for stored as an int
     * @param date the day that the room is being booked for stored as a 'LocalDate'
     * @param socially_distanced a boolean that represents whether the booking is under socially
     *                           distanced conditions
     */
    public void reserveRoom(String staff_id, String room_no, String module_id,
                            int time, LocalDate date, boolean socially_distanced){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{

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

    /**
     * A function that accesses the 'Bookings' table and finds a booking that matches the inputted data,
     * and then removes it from the table
     * @param staff_id the unique String ID that identifies a staff member
     * @param room_no the unique String room number that identifies a room number
     * @param module_id the unique String id that identifies an individual module
     * @param time the time of day that the reservation is stored under, stored as an integer
     *             with 0 representing 8:00 and 10 representing 6:00
     * @param date a 'LocalDate' datatype for what day the booking takes place on
     */
    public void cancelRoomReservation(String staff_id, String room_no, String module_id,
                                      int time, LocalDate date){

        Session session = HibernateUtil.getSessionFactory().openSession();
        try{

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

    /**
     * Accesses the 'Bookings' table and returns a list of all the bookings within it.
     * @return returns an Arraylist of Bookings from the database
     */
    public List<Booking> listOfBookings(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Booking> bookingList = null;
        bookingList = session.createQuery("FROM booking").list();
        session.getTransaction().commit();
        return bookingList;
    }

    /**
     * This function finds rooms that do not have any 'Booking' or reservation at a user provided time.
     * It accesses the 'Bookings' table, searches for a user inputted time, checks whether the
     * duration of other bookings means that the room would still be booked in the given time, and
     * then returns the list of free rooms.
     * @param date a LocalDate that stores what day the booking takes place on
     * @param time an integer representing the time of day that the booking will take place on,
     *             where '0' represents 8:00 and '10' represents 6:00
     * @param duration an integer representing the duration of the booking, how many hours the booking
     *                 will take
     * @param people_no the number of people that are attending the booked session
     */
    public void findRoom(LocalDate date, int time, int duration, int people_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
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
                            System.out.println("Social distancing capacity: " +
                                    i.getSocial_distancing_capacity() + "\n");
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

    /**
     * Accesses the 'Bookings' table, and checks for all bookings with a given room number, before
     * returning all the bookings that that room is reserved for
     * @param room_no the user given String room number that is searched for
     */
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


    /**
     * takes a user given room number, staff id and module id and returns the bookings within the
     * 'Bookings' table with matching details, in order to prove you have successfully booked a room.
     * @param room_number the user given room number that is searched for in the table
     * @param staff_id the user given staff ID that is searched for
     * @param module_id the user given module ID that is searched for
     * @return an Arraylist of Bookings that contains all the bookings with those details.
     */
    public List<Booking> roomConfirmation(String room_number, String staff_id, String module_id){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Booking> confirmation = session.createQuery(
                "FROM booking WHERE room_number = :room_number AND " +
                "staff_id = :staff_id AND module_id = :module_id").setParameter("room_number", room_number)
                .setParameter("module_id", module_id).setParameter("staff_id", staff_id).list();
        return confirmation;
    }

    /**
     * Updates the 'Rooms' table with the updated details for a given room
     * @param room_number the old room's String room number
     * @param new_room_no the new String room number for the given Room
     * @param new_type the String type of room that the room will be
     * @param new_cap the integer maximum capacity of the room
     * @param new_soc_cap the integer maximum capacity of the room under socially distant conditions
     *
     * @author Oscar
     */
    public void updateRoomDetails(String room_number, String new_room_no, String new_type,
                                  int new_cap, int new_soc_cap){

        Session session = HibernateUtil.getSessionFactory().openSession();
        try{

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
