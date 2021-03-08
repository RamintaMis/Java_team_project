package csc1035.project2;

import javax.persistence.*;
import java.time.LocalDate;

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
