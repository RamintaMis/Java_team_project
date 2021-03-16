package csc1035.project2;

import javax.persistence.*;

@Entity(name = "takes")
@IdClass(TakesCompositeKey.class)
public class Takes {

    @Id
    @Column
    private int sid;

    @Id
    @Column
    private String mid;

    public Takes(int sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    public Takes(){}

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
