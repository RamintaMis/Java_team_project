[Version 0 - 12/02/2021 - Oscar Corlett-Moss]
* Created MenuIO.java and added menu functionality, all methods empty
        except for notes explaining what they are going to do or pseudo-SQL
        for what they are going to do
* Created CHANGELOG.md

[Version 1 - 04/03/2021 - Pawel Gertner]
* Created the testing directory initialising_sample_data
  for uploading test data in the csv files to the database
  * Created CsvIntoList class
  * Created InitialisingModules class
  * Created InitialisingRooms class
  * Created InitialisingStaff class
  * Created InitialisingStudents class
    
[Version 2 - 09/03/2021 - Raminta Misiunaite]
* Created Rooms,Staff,Students, Modules, Booking classes  
* Created BookingCompositeKey class for 
  composite primary key in Booking table
* Started creating RoomBookingSys 
  * added listOfRooms method 
  * added reserveRoom method
  * started creating cancelRoomReservation method - not finished yet 
  * added listOfBookings method for easier access to all bookings 
  * started creating findRoom method - not finished yet 
  * added timetableForRoom method 
  * added updateRoomDetails method 
  * added booking confirmation
* Created class for testing RoomBookingSys, will delete/replace later

[Version 3 - 15/03/2021 - Pawel Gertner]
* Updated RoomBookingSys
  * Completed cancelRoomReservation method
  * Completed findRoom method
* Added ModuleRequirements class
* Added InitialisingModuleRequirements class for 
  testing directory
* Removed duplicate no-arg constructors in
  Entity classes
  

[Version 4 - 16/03/2021 - Raminta Misiunaite]
* Added takes.csv, teaches.csv with assigned data
* Created InitialisingTakes, InitialisingTeaches to transfer data to DB
* Created Takes, Teaches classes
* Created TakesCompositeKey, TeachesCompositeKey for their primary keys respectively 
* Started Timetabling System
  * created a template for listOfStudentsByModule method
  * created listOfStaffByModule method
  * created listOfModuleReq method