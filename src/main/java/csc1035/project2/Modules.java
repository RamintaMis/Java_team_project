package csc1035.project2;

import javax.persistence.*;

@Entity(name = "modules")
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private int credits;

    @Column
    private int weeks;

    @Column
    private int lecture_length;

    @Column
    private int no_of_lectures;

    @Column
    private int no_of_practicals;

    @Column
    private int practical_length;

    //@Column
    // private ... week_commencing


    public Modules(String id, String name, int credits, int weeks, int lecture_length,
                   int no_of_lectures, int no_of_practicals, int practical_length) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
        this.lecture_length = lecture_length;
        this.no_of_lectures = no_of_lectures;
        this.no_of_practicals = no_of_practicals;
        this.practical_length = practical_length;
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

    public int getLecture_length() {
        return lecture_length;
    }

    public void setLecture_length(int lecture_length) {
        this.lecture_length = lecture_length;
    }

    public int getNo_of_lectures() {
        return no_of_lectures;
    }

    public void setNo_of_lectures(int no_of_lectures) {
        this.no_of_lectures = no_of_lectures;
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
