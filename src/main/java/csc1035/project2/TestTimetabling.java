package csc1035.project2;


import java.util.List;

public class TestTimetabling {
    public static void main(String[] args) {
        TimetablingSys t = new TimetablingSys();

      //  List<Staff> temp = t.listOfStaffByModule("NTT2305");
      //  System.out.println(temp);
         t.listOfModuleReq("NTT2305");
        //System.out.println(temp2);
    }
}
