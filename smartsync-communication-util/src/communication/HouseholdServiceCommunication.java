package communication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.HouseholdPOJO;
import model.HouseholdServiceLookUpPOJO;
import model.HouseholdUserLookUpPOJO;
import util.HttpUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * A wrapper for communicating with the household service of smart sync.
 */
public class HouseholdServiceCommunication {

    private static final String HOUSEHOLD_BASE_URL = "http://localhost:8000/households/";

    public HouseholdServiceCommunication() {

    }

    /**
     * Gets all households
     */
    public List<HouseholdPOJO> getAllHouseholds() {
        try {
            String json = HttpUtil.executeGetRequest(HOUSEHOLD_BASE_URL);
            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<HouseholdPOJO>>(){}.getType();
            List<HouseholdPOJO> householdList = gson.fromJson(json, typeList);
            return householdList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets household by id
     * @param id the id to find by
     */
    public HouseholdPOJO getHouseholdByHouseholdId(Long id) {
        try {
            String json = HttpUtil.executeGetRequest(HOUSEHOLD_BASE_URL + id);
            return jsonToHousehold(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the household for the user id
     * @param userId
     * @return
     */
    public HouseholdPOJO getHouseholdForUserId(Long userId) {
        try {
            String json = HttpUtil.executeGetRequest(HOUSEHOLD_BASE_URL + "users/" + userId);
            return jsonToHousehold(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Creates a new household
     *
     * @param parameters the parameters in the form of a hashmap
     */
    public HouseholdPOJO addHousehold(HashMap<String, String> parameters) {
        // create request parameter string
        Iterator iterator = parameters.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            builder.append("\"" + pair.getKey() + "\" : " + "\"" +pair.getValue() + "\", ");
        }

        String requestBody = builder.toString();

        requestBody = requestBody.substring(0, requestBody.length()-2);
        requestBody = requestBody + "}";

        try {
            String json = HttpUtil.executePostRequest(HOUSEHOLD_BASE_URL, requestBody);
            return jsonToHousehold(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a household
     *
     * @param id the id to delete
     */
    public HouseholdPOJO deleteHousehold(Long id) {
        try {
            String json = HttpUtil.executeDeleteRequest(HOUSEHOLD_BASE_URL + id);
            return jsonToHousehold(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a user to household
     * @param parameters
     * @return
     */
    public HouseholdUserLookUpPOJO addUserToHousehold(HashMap<String, String> parameters) {
        // create request parameter string
        Iterator iterator = parameters.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            builder.append("\"" + pair.getKey() + "\" : " + "\"" +pair.getValue() + "\", ");
        }    String requestBody = builder.toString();

        requestBody = requestBody.substring(0, requestBody.length()-2);
        requestBody = requestBody + "}";

        try {
            String json = HttpUtil.executePostRequest(HOUSEHOLD_BASE_URL + "users", requestBody);
            return jsonToHouseholdUserLookup(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Removes a user from the household
     * @param parameters the parameter hashmap
     * @return the household user lookup pojo
     */
    public HouseholdUserLookUpPOJO removeUserFromHousehold(HashMap<String, String> parameters) {

        // create request parameter string
        Iterator iterator = parameters.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            builder.append("\"" + pair.getKey() + "\" : " + "\"" +pair.getValue() + "\", ");
        }    String requestBody = builder.toString();

        requestBody = requestBody.substring(0, requestBody.length()-2);
        requestBody = requestBody + "}";

        try {
            String json = HttpUtil.executeDeleteRequestWithBody(HOUSEHOLD_BASE_URL + "users", requestBody);
            return jsonToHouseholdUserLookup(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * Adds a service to household
     * @param parameters
     * @return
     */
    public HouseholdServiceLookUpPOJO addServiceToHousehold(HashMap<String, String> parameters) {
        // create request parameter string
        Iterator iterator = parameters.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            builder.append("\"" + pair.getKey() + "\" : " + "\"" +pair.getValue() + "\", ");
        }    String requestBody = builder.toString();

        requestBody = requestBody.substring(0, requestBody.length()-2);
        requestBody = requestBody + "}";

        try {
            String json = HttpUtil.executePostRequest(HOUSEHOLD_BASE_URL + "services", requestBody);
            return jsonToHouseholdServiceLookup(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Removes a service from the household
     * @param parameters the parameter hashmap
     * @return the household service lookup pojo
     */
    public HouseholdServiceLookUpPOJO removeServiceFromHousehold(HashMap<String, String> parameters) {

        // create request parameter string
        Iterator iterator = parameters.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            builder.append("\"" + pair.getKey() + "\" : " + "\"" +pair.getValue() + "\", ");
        }    String requestBody = builder.toString();

        requestBody = requestBody.substring(0, requestBody.length()-2);
        requestBody = requestBody + "}";

        try {
            String json = HttpUtil.executeDeleteRequestWithBody(HOUSEHOLD_BASE_URL + "services", requestBody);
            return jsonToHouseholdServiceLookup(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * Converts a json string into a HouseholdPOJO object
     * @param json the json string
     * @return the household pojo object
     */
    private HouseholdPOJO jsonToHousehold(String json) {
        Gson gson = new Gson();
        HouseholdPOJO household = gson.fromJson(json, HouseholdPOJO.class);

        return household;
    }

    /**
     * Converts a json string into a HouseholdUserLookupPOJO object
     * @param json the json string
     * @return the HouseholdUserLOokupPojo
     */
    private HouseholdUserLookUpPOJO jsonToHouseholdUserLookup(String json) {
        Gson gson = new Gson();
        HouseholdUserLookUpPOJO householdUserLookUpPOJO = gson.fromJson(json, HouseholdUserLookUpPOJO.class);
        return householdUserLookUpPOJO;
    }

    /**
     * Converts a json string into a HouseholdServiceLookupPOJO object
     * @param json the json string
     * @return the HouseholdUserLOokupPojo
     */
    private HouseholdServiceLookUpPOJO jsonToHouseholdServiceLookup(String json) {
        Gson gson = new Gson();
        HouseholdServiceLookUpPOJO householdServiceLookUpPOJO = gson.fromJson(json, HouseholdServiceLookUpPOJO.class);
        return householdServiceLookUpPOJO;
    }
}