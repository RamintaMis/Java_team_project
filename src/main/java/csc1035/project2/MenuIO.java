package csc1035.project2;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

/**
 * This is the main function that contains the interface that the user will interact with.
 * it contains several separate menu functions to ease readability
 * @author Oscar
 */
public class MenuIO {

    /**
     * prints a string
     * @param sentence an inputted string that the user wants outputted
     */
    public static void Say(String sentence){
        System.out.println(sentence);
    }

    /**
     * a menu that gives the user access to separate parts of the room booking system,
     * including: returning a list of rooms, reserving a room, cancelling a reservation,
     * finding a free room at a given time, getting a booking confirmation, returning a room
     * timetable and updating the details of a room.
     * Upon the exit of this function, it returns to the main menu.
     *
     * @param reader a scanner inputted here to reduce the number of new scanners
     */
    public static void roomMenu(Scanner reader){

        RoomBookingSys r = new RoomBookingSys();
        boolean roomExit = false;
        String roomChoice;
        while (!roomExit) {
            Say("Please select an option:");
            Say("1 - Get Room List");
            Say("2 - Reserve a Room");
            Say("3 - Cancel a Reservation");
            Say("4 - Find Free Rooms");
            Say("5 - Get Booking Confirmation");
            Say("6 - Return Timetable for Room");
            Say("7 - Update Room Details");
            Say("8- Exit");

            roomChoice = reader.nextLine();

            switch (roomChoice) {

                case "1":
                    //SELECT * FROM ROOMS
                    List<Rooms> roomsList = r.listOfRooms();
                    for (Rooms i : roomsList){
                        System.out.println("Room number: " + i.getRoom_number());
                        System.out.println("Room type: " + i.getType());
                        System.out.println("Max capacity: " + i.getMax_capacity());
                        System.out.println("Max social dist. capacity: " + i.getSocial_distancing_capacity() + "\n");
                    }

                    break;

                case "2":
                    //INSERT INTO bookings(columns) (booking details)
                    boolean correct_choice = false;

                    Say("Enter staff id: ");
                    String r_staff_id = reader.nextLine();

                    Say("Enter room number: ");
                    String r_room_num = reader.nextLine();

                    Say("Enter module id: ");
                    String r_module_id = reader.nextLine();

                    Say("Enter reservation time (where 8:00 is '0' and 18:00 is '10'): ");
                    int r_time = Integer.parseInt(reader.nextLine());
                    //make sure the user puts in a valid date
                    do {

                        while(!reader.hasNextInt()) {
                            System.out.println("Please input a valid number");
                            reader.next();
                        }

                        r_time = reader.nextInt();
                        reader.nextLine(); //remove leftover new line

                        /*the time cannot exceed this value so we can keep this to say the value
                        will be below it */
                    }while(r_time < 11);
                    LocalDate date = LocalDate.now();
                    while (!correct_choice) {
                        Say("Will the room be socially distanced? y/n: ");
                        String r_decision = reader.nextLine();
                        if (r_decision.equals("y")) {
                            r.reserveRoom(r_staff_id, r_room_num, r_module_id, r_time, date, true);
                            correct_choice = true;
                        } else if (r_decision.equals("n")) {
                            r.reserveRoom(r_staff_id, r_room_num, r_module_id, r_time, date, false);
                            correct_choice = true;
                        } else {
                            Say("Please enter a valid option \n");
                        }
                    }

                    break;

                case "3":
                    //DELETE * FROM bookings WHERE staffID = x AND date = y AND TIME = z
                    Say("Enter staff id: ");
                    String c_staff_id = reader.nextLine();

                    Say("Enter room number: ");
                    String c_room_num = reader.nextLine();

                    Say("Enter module id: ");
                    String c_module_id = reader.nextLine();

                    Say("Enter reservation time (where 8:00 is '0' and 18:00 is '10'): ");
                    int c_time = Integer.parseInt(reader.nextLine());
                    do {

                        while(!reader.hasNextInt()) {
                            System.out.println("Please input a valid number");
                            reader.next();
                        }

                        c_time = reader.nextInt();
                        reader.nextLine(); //remove leftover new line

                        /*the time cannot exceed this value so we can keep this to say the value
                        will be below it */
                    }while(c_time < 11);
                    Say("Enter reservation date " +
                            "in the format YYYY-MM-DD: ");
                    LocalDate c_date = LocalDate.parse(reader.nextLine());
                    r.cancelRoomReservation(c_staff_id, c_room_num, c_module_id, c_time, c_date);

                    break;

                case "4":
                        //SELECT room_number FROM rooms, bookings WHERE (inputted date/time) != bookings.date/time
                    Say("Enter date in the format YYYY-MM-DD: ");
                    LocalDate f_date = LocalDate.parse(reader.nextLine());

                    Say("Enter reservation time (where 8:00 is '0' and 18:00 is '10'): ");
                    int f_time = Integer.parseInt(reader.nextLine());
                    do {

                        while(!reader.hasNextInt()) {
                            System.out.println("Please input a valid number");
                            reader.next();
                        }

                        f_time = reader.nextInt();
                        reader.nextLine(); //remove leftover new line

                        //the time cannot exceed this value so it must be below it
                    }while(f_time < 11);

                    Say("Enter duration: ");
                    int f_duration = Integer.parseInt(reader.nextLine());

                    Say("Enter number of people: ");
                    int f_people = Integer.parseInt(reader.nextLine());
                    r.findRoom(f_date, f_time, f_duration, f_people);

                    break;

                case "5":
                        //SELECT * FROM bookings WHERE staffID = x AND room_number = y AND moduleID = z
                    Say("Enter room number: ");
                    String con_room_num = reader.nextLine();

                    Say("Enter staff id: ");
                    String con_staff_id = reader.nextLine();

                    Say("Enter module id: ");
                    String con_mod_id = reader.nextLine();

                    List<Booking> con_list = r.roomConfirmation(con_room_num, con_staff_id, con_mod_id);
                    for (Booking i : con_list){
                        System.out.println("\nBooking confirmation:");
                        System.out.println("\nStaff id: " + i.getStaff_id());
                        System.out.println("Room number: " + i.getRoom_number());
                        System.out.println("Module id: " + i.getModule_id());
                        System.out.println("Date booked: " + i.getDate());
                        System.out.println("Time booked: " + i.getTime() + "\n");
                    }

                    break;

                case "6":
                        //SELECT * FROM bookings WHERE room_number = (inputted room_number) ORDER BY date
                    Say("Enter room number: ");
                    String t_room_num = reader.nextLine();
                    r.timetableForRoom(t_room_num);
                    break;

                case "7":
                        //UPDATE rooms SET (room columns) = (inputted data) WHERE room_number = (inputted data)
                    Say("Enter the room number: ");
                    String u_room_num = reader.nextLine();
                    System.out.println("\nWhat would you like to edit? \n" +
                            "1 - Room number \n" +
                            "2 - Room type \n" +
                            "3 - Max Capacity \n" +
                            "4 - Social distancing capacity");
                    String u_choice = reader.nextLine();
                    switch (u_choice){
                        case "1":
                            Say("Enter new room number: ");
                            String u_new_room_num = reader.nextLine();
                            r.updateRoomDetails(u_room_num, u_new_room_num, null, 0, 0);
                            break;
                        case "2":
                            Say("Enter new room type: ");
                            String u_new_room_type = reader.nextLine();
                            r.updateRoomDetails(u_room_num, null, u_new_room_type, 0, 0);
                            break;
                        case "3":
                            Say("Enter new room capacity: ");
                            int u_new_room_cap = Integer.parseInt(reader.nextLine());
                            r.updateRoomDetails(u_room_num, null, null, u_new_room_cap, 0);
                            break;
                        case "4":
                            Say("Enter new social distancing capacity: ");
                            int u_new_room_soc_cap = Integer.parseInt(reader.nextLine());
                            r.updateRoomDetails(u_room_num, null, null, 0, u_new_room_soc_cap);
                            break;
                    }

                    break;

                case "8":
                    //exit while loop and halt program
                    roomExit = true;
                    break;

                default:

                    Say("Please input a valid option between 1-8");

            }
        }
    }

    /**
     * This function manages a menu containing all the things the user can access about the
     * timetable system, such as: students on a module, staff that teach a module, module
     * requirements, create a timetable, create a timetable under socially distanced conditions,
     * produce a timetable for a student, produce a timetable for a staff member.
     * Upon the ending of the function it returns to the main menu function
     *
     * @param reader a Scanner inputted to reduce the number of new Scanners
     */
    public static void timetableMenu(Scanner reader){

        boolean timeExit = false;

        String timeChoice;

        while (!timeExit) {
            Say("Please select an option:");
            Say("1 - Get Students That Take a Module");
            Say("2 - Get Staff That Teach a Module");
            Say("3 - Get Module Requirements");
            Say("4 - Create Timetable");
            Say("5 - Create Socially Distanced Timetable");
            Say("6 - Produce Student Timetable");
            Say("7 - Produce Staff Timetable");
            Say("8- Exit");

            timeChoice = reader.nextLine();

            switch (timeChoice) {

                case "1":
                    //SELECT * FROM students, takes WHERE takes.moduleID = (inputted data)
                    // AND takes.studentID = students.studentID


                    break;

                case "2":
                    //SELECT * FROM staff, bookings WHERE bookings.moduleID = (inputted data)
                    // AND bookings.staffID = staff.staffID

                    break;

                case "3":
                    //SELECT * FROM module_requirements WHERE moduleID = (inputted data)

                    break;

                case "4":
                    //assign a module a booking for all the spaces it needs, and then fill in
                    //the database with these bookings, and then return all the bookings for
                    //the modules taken for a given student.
                    break;

                case "5":
                    //do the same as case 4 but instead restrict the class size by 'socially distanced'
                    //class numbers
                    break;

                case "6":
                    //return all the bookings containing modules taken by a given student
                    break;

                case "7":
                    //return all the bookings containing a given staffID
                    break;

                case "8":
                    //exit while loop and halt program
                    timeExit = true;
                    break;

                default:

                    System.out.println("Please input a valid option between 1-8");

            }
        }
    }

    /**
     * this is the main function that declares a scanner and creates a menu. The menu acts as
     * a director to other menu functions as to avoid this function getting too full of clutter.
     *
     * @param args
     */
    public static void main(String[] args) {
        //declare the scanner and the user's input as reader and choice
        Scanner reader = new Scanner(System.in);
        String choice;

        //declare a boolean that checks whether the user wants to exit the program
        boolean exit = false;

        while (!exit) {
            Say("Please select an option:");
            Say("1 - Rooms and Bookings");
            Say("2 - Timetables");
            Say("3- Exit");

            choice = reader.nextLine();

            switch (choice) {

                case "1":
                    roomMenu(reader);

                    break;

                case "2":
                    timetableMenu(reader);

                    break;

                case "3":
                    //exit while loop and halt program
                    exit = true;

                    break;

                default:

                    System.out.println("Please input a valid option between 1-3");

            }
        }
    }
}
