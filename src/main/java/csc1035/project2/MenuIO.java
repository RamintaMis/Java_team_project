package csc1035.project2;

import java.util.ArrayList;
import java.util.Scanner;


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

            switch (roomChoice) {

                case "1":
                    //SELECT * FROM ROOMS

                    break;

                case "2":
                    //INSERT INTO bookings(columns) (booking details)

                    break;

                case "3":
                    //DELETE * FROM bookings WHERE staffID = x AND date = y AND TIME = z

                    break;

                case "4":
                        //SELECT room_number FROM rooms, bookings WHERE (inputted date/time) != bookings.date/time
                    break;

                case "5":
                        //SELECT * FROM bookings WHERE staffID = x AND room_number = y AND moduleID = z
                    break;

                case "6":
                        //SELECT * FROM bookings WHERE room_number = (inputted room_number) ORDER BY date
                    break;

                case "7":
                        //UPDATE rooms SET (room columns) = (inputted data) WHERE room_number = (inputted data)
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

            switch (choice) {

                case "1":
                    roomMenu(reader);
                    break;

                case "2":

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
