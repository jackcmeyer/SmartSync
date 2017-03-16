import communication.HouseholdServiceCommunication;

import java.util.HashMap;

/**
 * Created by jack on 3/9/17.
 */
public class Test {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", "3");
        hashMap.put("householdId", "15");

        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        System.out.println(householdServiceCommunication.removeUserFromHousehold(hashMap));

    }
}
