package csc1035.project2;

import java.io.Serializable;
import java.util.Objects;


public class BookingCompositePriKey implements Serializable {

    private String staff_id;
    private String room_number;
    private String module_id;

    public BookingCompositePriKey(){}

    public BookingCompositePriKey(String staff_id, String room_number, String module_id) {
        this.staff_id = staff_id;
        this.room_number = room_number;
        this.module_id = module_id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BookingCompositePriKey composite = (BookingCompositePriKey) o;
        return Objects.equals(staff_id, composite.staff_id) &&
                Objects.equals(room_number, composite.room_number) &&
                Objects.equals(module_id, composite.module_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(staff_id,room_number,module_id);
    }
}
