package csc1035.project2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity declaration for the class 'Modules'. It contains the String module ID, the
 * String module name, the integer value of how many credits the module is worth and the integer
 * for how many weeks the module goes on for
 *
 * @author oscar
 */
@Entity(name = "modules")
public class Modules {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private int credits;

    @Column
    private int weeks;

    @ManyToMany(mappedBy = "modules")
    private Set<Students> students = new HashSet<>();

    public Modules(String id, String name, int credits, int weeks) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
    }
    public Modules(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
