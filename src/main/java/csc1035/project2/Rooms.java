package csc1035.project2;

import javax.persistence.*;

/**
 * This is the entity declaration for the class 'rooms', containing the String room number,
 * the String type of room that it is, the integer value for what the room's maximum capacity is,
 * and the integer value for what the room's maximum capacity is under socially distant conditions.
 *
 * @author oscar
 */
@Entity(name="rooms")
public class Rooms {

    @Id
    @Column
    private String room_number;

    @Column
    private String type;

    @Column
    private int max_capacity;

    @Column
    private int social_distancing_capacity;

    public Rooms(String room_number, String type, int max_capacity, int social_distancing_capacity) {
        this.room_number = room_number;
        this.type = type;
        this.max_capacity = max_capacity;
        this.social_distancing_capacity = social_distancing_capacity;
    }
    public Rooms(){}

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getSocial_distancing_capacity() {
        return social_distancing_capacity;
    }

    public void setSocial_distancing_capacity(int social_distancing_capacity) {
        this.social_distancing_capacity = social_distancing_capacity;
    }
}
