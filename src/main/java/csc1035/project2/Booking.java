package csc1035.project2;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * the entity declaration for a 'Booking' class, containing the ID (composed of the Strings: the room number the
 * booking takes place in, the module the room is being booked for, and the staff member that is booking
 * the room), the date (stored as a Local Date), an integer of what time the booking takes place in (where
 * '0' is 8:00 and '10' is 6:00), and a boolean value representing whether the room is booked under
 * socially distanced conditions.
 *
 * @author Oscar
 */
@Entity(name = "booking")
@IdClass(BookingCompositePriKey.class)
public class Booking {
    @Id
    @Column
    private String room_number;

    @Id
    @Column
    private String module_id;

    @Id
    @Column
    private String staff_id;

    @Column
    private LocalDate date;

    @Column
    private int time;

    @Column
    private boolean socially_distanced;

    public Booking(String room_number, String module_id, String staff_id, LocalDate date, int time, boolean socially_distanced) {
        this.room_number = room_number;
        this.module_id = module_id;
        this.staff_id = staff_id;
        this.date = date;
        this.time = time;
        this.socially_distanced = socially_distanced;
    }

    public Booking(){}

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean getSocially_distanced() {
        return socially_distanced;
    }

    public void setSocially_distanced(boolean socially_distanced) {
        this.socially_distanced = socially_distanced;
    }
}
