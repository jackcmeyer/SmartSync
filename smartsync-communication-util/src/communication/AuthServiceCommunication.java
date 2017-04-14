package communication;

import model.AuthPOJO;
import util.HttpUtil;

/**
 * Created by trev on 4/1/17.
 */
public class AuthServiceCommunication {
    private static final String AUTH_BASE_URL = "http://localhost:8000/auth/";

    /**
     * access the validation endpoint
     * @param sessionId
     * @return
     */
    public boolean authenticateUser(String sessionId) {
        try {
            String json = HttpUtil.executeGetRequest(AUTH_BASE_URL + "/validate/" + sessionId, sessionId);
            if(json.equals("true")) {
                return true;
            } else {
                return false;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
