import communication.HouseholdServiceCommunication;

import java.util.HashMap;

/**
 * Created by jack on 3/9/17.
 */
public class Test {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", "1");
        hashMap.put("householdId", "17");

        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        System.out.println(householdServiceCommunication.addUserToHousehold(hashMap));

    }
}
