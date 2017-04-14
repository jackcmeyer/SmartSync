package communication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.UserPOJO;
import util.HttpUtil;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * A wrapper for communicating with the user service
 */
public class UserServiceCommunication {

    private static final String USER_BASE_URL = "http://localhost:8000/users/";

    /**
     * Gets all users
     */
    public List<UserPOJO> getAllUsers(String sessionId) {
        try {
            String json = HttpUtil.executeGetRequest(USER_BASE_URL, sessionId);

            Gson gson = new Gson();
            Type typeList = new TypeToken<ArrayList<UserPOJO>>(){}.getType();
            List<UserPOJO> userList = gson.fromJson(json, typeList);

            return userList;
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
    public UserPOJO getUser(Long id, String sessionId) {

        try {
            String json = HttpUtil.executeGetRequest(USER_BASE_URL + id,sessionId);
            return jsonToUser(json);
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
    public UserPOJO addUser(HashMap<String, String> parameters, String sessionId) {

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
           String json = HttpUtil.executePostRequest(USER_BASE_URL, requestBody, sessionId);
           return jsonToUser(json);
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
    public UserPOJO deleteUser(Long id, String sessionId) {

        try {
            String json = HttpUtil.executeDeleteRequest(USER_BASE_URL + id, sessionId);
            return jsonToUser(json);

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserPOJO getUserByGoogleId(String googleId) {
        try {
            String json = HttpUtil.executeGetRequest(USER_BASE_URL + "/google/"
                    + googleId, googleId);
            return jsonToUser(json);

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
    private UserPOJO jsonToUser(String json) {
        Gson gson = new Gson();
        UserPOJO user = gson.fromJson(json, UserPOJO.class);

        return user;
    }

}