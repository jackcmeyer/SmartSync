package communication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ServicePOJO;
import util.HttpUtil;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * A wrapper for communicating with the service service
 */
public class ServiceServiceCommunication {

    private static final String SERVICE_BASE_URL = "http://localhost:8000/services/";

    /**
     * Gets all users
     */
    public List<ServicePOJO> getAllServices() {
        try {
            String json = HttpUtil.executeGetRequest(SERVICE_BASE_URL);

            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<ServicePOJO>>(){}.getType();
            List<ServicePOJO> serviceList = gson.fromJson(json, typeList);

            return serviceList;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a user by their id
     *
     * @param id the id to find by
     */
    public ServicePOJO getService(Long id) {

        try {
            String json = HttpUtil.executeGetRequest(SERVICE_BASE_URL + id);
            return jsonToService(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add a user to the database
     *
     * @param parameters a hashmap which holds the parameters
     */
    public ServicePOJO addService(HashMap<String, String> parameters) {

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
           String json = HttpUtil.executePostRequest(SERVICE_BASE_URL, requestBody);
           return jsonToService(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a user by their id
     *
     * @param id the id to delete by
     */
    public ServicePOJO deleteService(Long id) {

        try {
            String json = HttpUtil.executeDeleteRequest(SERVICE_BASE_URL + id);
            return jsonToService(json);

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a json string to a UserPOJO object
     * @param json the json string
     * @return the user pojo object
     */
    private ServicePOJO jsonToService(String json) {
        Gson gson = new Gson();
        ServicePOJO service = gson.fromJson(json, ServicePOJO.class);

        return service;
    }

}