package csc1035.project2;

import java.io.Serializable;
import java.util.Objects;

public class TakesCompositeKey implements Serializable {

    private int sid;
    private String mid;

    public TakesCompositeKey(){}

    public TakesCompositeKey(int sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        TakesCompositeKey composite = (TakesCompositeKey) o;
        return Objects.equals(sid, composite.sid) &&
                Objects.equals(mid, composite.mid);
    }

    @Override
    public int hashCode(){
        return Objects.hash(sid,mid);
    }
}
