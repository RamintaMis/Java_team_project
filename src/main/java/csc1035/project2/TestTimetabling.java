package csc1035.project2;


import java.util.List;

/**
 * This class contains all the test data for the timetabling system, calling the functions with
 * given test data.
 *
 * @author Oscar
 */
public class TestTimetabling {
    public static void main(String[] args) {
        TimetablingSys t = new TimetablingSys();

      //  List<Staff> temp = t.listOfStaffByModule("NTT2305");
      //  System.out.println(temp);
         t.listOfModuleReq("NTT2305");
        //System.out.println(temp2);
    }
}
