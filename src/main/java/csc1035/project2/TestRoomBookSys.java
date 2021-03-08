package csc1035.project2;

import java.time.LocalDate;
import java.util.List;

public class TestRoomBookSys {

    public static void main(String[] args) {
        RoomBookingSys r = new RoomBookingSys();
       // List<Rooms> temp = r.listOfRooms();
        r.reserveRoom("NUC1632260", "3.473","BKR9132", 14, LocalDate.of(2021, 3, 8), true);
    }
}
