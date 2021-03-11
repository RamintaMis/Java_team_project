package csc1035.project2;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "module_requirements")
public class ModuleRequirements {

    @Id
    @Column
    private String id;

    @Column
    private Date week_commencing;

    @Column
    private int no_of_lectures;

    @Column
    private int lecture_length;

    @Column
    private int no_of_practicals;

    @Column
    private int practical_length;

    public ModuleRequirements(String id, Date week_commencing, int no_of_lectures
            , int lecture_length, int no_of_practicals, int practical_length) {
        this.id = id;
        this.week_commencing = week_commencing;
        this.no_of_lectures = no_of_lectures;
        this.lecture_length = lecture_length;
        this.no_of_practicals = no_of_practicals;
        this.practical_length = practical_length;
    }
    public ModuleRequirements(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getWeek_commencing() {
        return week_commencing;
    }

    public void setWeek_commencing(Date week_commencing) {
        this.week_commencing = week_commencing;
    }

    public int getNo_of_lectures() {
        return no_of_lectures;
    }

    public void setNo_of_lectures(int no_of_lectures) {
        this.no_of_lectures = no_of_lectures;
    }

    public int getLecture_length() {
        return lecture_length;
    }

    public void setLecture_length(int lecture_length) {
        this.lecture_length = lecture_length;
    }

    public int getNo_of_practicals() {
        return no_of_practicals;
    }

    public void setNo_of_practicals(int no_of_practicals) {
        this.no_of_practicals = no_of_practicals;
    }

    public int getPractical_length() {
        return practical_length;
    }

    public void setPractical_length(int practical_length) {
        this.practical_length = practical_length;
    }
}
