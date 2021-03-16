package csc1035.project2;


import java.io.Serializable;
import java.util.Objects;

public class TeachesCompositeKey implements Serializable {

    private String staff_id;
    private String module_id;

    public TeachesCompositeKey(){}

    public TeachesCompositeKey(String staff_id, String module_id) {
        this.staff_id = staff_id;
        this.module_id = module_id;
    }

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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        TeachesCompositeKey composite = (TeachesCompositeKey) o;
        return Objects.equals(staff_id, composite.staff_id) &&
                Objects.equals(module_id, composite.module_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(staff_id,module_id);
    }
}
