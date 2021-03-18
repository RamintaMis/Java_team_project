package csc1035.project2;


import javax.persistence.*;

/**
 * This is the entity declaration for the class 'teaches', containing the String Staff ID that
 * belongs to a given teacher, and the String Module ID that the given Teacher takes.
 *
 * @author Oscar
 */
@Entity(name="teaches")
@IdClass(TeachesCompositeKey.class)
public class Teaches {

    @Id
    @Column
    private String staff_id;

    @Id
    @Column
    private String module_id;

    public Teaches(String staff_id, String module_id) {
        this.staff_id = staff_id;
        this.module_id = module_id;
    }

    public Teaches(){}

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }
}
