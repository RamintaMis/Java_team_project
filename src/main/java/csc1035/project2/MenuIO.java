package csc1035.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class MenuIO {


    public static void Say(String sentence){
        System.out.println(sentence);
    }

    public static void roomMenu(Scanner reader){

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
            RoomBookingSys r = new RoomBookingSys();

            switch (roomChoice) {

                case "1":
                    List<Rooms> roomsList = r.listOfRooms();
                    for (Rooms i : roomsList){
                        System.out.println("Room number: " + i.getRoom_number());
                        System.out.println("Room type: " + i.getType());
                        System.out.println("Max capacity: " + i.getMax_capacity());
                        System.out.println("Max social dist. capacity: " + i.getSocial_distancing_capacity() + "\n");
                    }
                    break;

                case "2":
                    boolean correct_choice = false;
                    System.out.println("Enter staff id: ");
                    String r_staff_id = reader.nextLine();
                    System.out.println("Enter room number: ");
                    String r_room_num = reader.nextLine();
                    System.out.println("Enter module id: ");
                    String r_module_id = reader.nextLine();
                    System.out.println("Enter reservation time (0-24): ");
                    int r_time = Integer.parseInt(reader.nextLine());
                    LocalDate date = LocalDate.now();
                    while (!correct_choice) {
                        System.out.println("Will the room be socially distanced? y/n: ");
                        String r_decision = reader.nextLine();
                        if (r_decision.equals("y")) {
                            r.reserveRoom(r_staff_id, r_room_num, r_module_id, r_time, date, true);
                            correct_choice = true;
                        } else if (r_decision.equals("n")) {
                            r.reserveRoom(r_staff_id, r_room_num, r_module_id, r_time, date, false);
                            correct_choice = true;
                        } else {
                            System.out.println("Please enter a valid option \n");
                        }
                    }
                    break;

                case "3":
                    System.out.println("Enter staff id: ");
                    String c_staff_id = reader.nextLine();
                    System.out.println("Enter room number: ");
                    String c_room_num = reader.nextLine();
                    System.out.println("Enter module id: ");
                    String c_module_id = reader.nextLine();
                    System.out.println("Enter reservation time (0-24): ");
                    int c_time = Integer.parseInt(reader.nextLine());
                    System.out.println("Enter reservation date " +
                            "in the format YYYY-MM-DD: ");
                    LocalDate c_date = LocalDate.parse(reader.nextLine());
                    r.cancelRoomReservation(c_staff_id, c_room_num, c_module_id, c_time, c_date);
                    break;

                case "4":
                    System.out.println("Enter date in the format YYYY-MM-DD: ");
                    LocalDate f_date = LocalDate.parse(reader.nextLine());
                    System.out.println("Enter time (0-24): ");
                    int f_time = Integer.parseInt(reader.nextLine());
                    System.out.println("Enter duration: ");
                    int f_duration = Integer.parseInt(reader.nextLine());
                    System.out.println("Enter number of people: ");
                    int f_people = Integer.parseInt(reader.nextLine());
                    r.findRoom(f_date, f_time, f_duration, f_people);
                    break;

                case "5":
                    System.out.println("Enter room number: ");
                    String con_room_num = reader.nextLine();
                    System.out.println("Enter staff id: ");
                    String con_staff_id = reader.nextLine();
                    System.out.println("Enter module id: ");
                    String con_mod_id = reader.nextLine();
                    List<Booking> con_list = r.roomConfirmation(con_room_num, con_staff_id, con_mod_id);
                    for (Booking i : con_list){
                        i.getStaff_id();
                        i.getRoom_number();
                        i.getModule_id();
                        i.getDate();
                        i.getTime();
                    }
                    break;

                case "6":
                    System.out.print("Enter room number: ");
                    String t_room_num = reader.nextLine();
                    r.timetableForRoom(t_room_num);
                    break;

                case "7":
                    System.out.println("Enter the room number: ");
                    String u_room_num = reader.nextLine();
                    System.out.println("\nWhat would you like to edit? \n" +
                            "1 - Room number \n" +
                            "2 - Room type \n" +
                            "3 - Max Capacity \n" +
                            "4 - Social distancing capacity");
                    String u_choice = reader.nextLine();
                    switch (u_choice){
                        case "1":
                            System.out.println("Enter new room number: ");
                            String u_new_room_num = reader.nextLine();
                            r.updateRoomDetails(u_room_num, u_new_room_num, null, 0, 0);
                            break;
                        case "2":
                            System.out.println("Enter new room type: ");
                            String u_new_room_type = reader.nextLine();
                            r.updateRoomDetails(u_room_num, null, u_new_room_type, 0, 0);
                            break;
                        case "3":
                            System.out.println("Enter new room capacity: ");
                            int u_new_room_cap = Integer.parseInt(reader.nextLine());
                            r.updateRoomDetails(u_room_num, null, null, u_new_room_cap, 0);
                            break;
                        case "4":
                            System.out.println("Enter new social distancing capacity: ");
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

                    System.out.println("Please input a valid option between 1-8");

            }
        }
    }

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

            //exit while loop and halt program
            switch (choice) {
                case "1" -> roomMenu(reader);
                case "2" -> timetableMenu(reader);
                case "3" -> exit = true;
                default -> System.out.println("Please input a valid option between 1-3");
            }
        }
    }
}
