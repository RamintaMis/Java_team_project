package csc1035.project2;

import javax.persistence.*;

@Entity(name="staff")
public class Staff {

    @Id
    @Column
    private String id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    public Staff(String id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public Staff(){}

    public Staff(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
