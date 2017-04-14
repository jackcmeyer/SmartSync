package com.smartsync.controller;

import com.smartsync.error.ClientNotAuthorizedException;
import com.smartsync.error.FailedToEndSessionException;
import com.smartsync.error.UserNotRegisteredException;
import com.smartsync.model.Auth;
import com.smartsync.service.AuthService;
import model.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import communication.UserServiceCommunication;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by trev on 4/1/17.
 * REST Controller for authorization for endpoints across the
 * system
 */
@RestController
public class AuthContoller {

    private final Logger logger = Logger.getLogger(this.getClass());

    private  UserServiceCommunication userServiceCommunication =
            new UserServiceCommunication();

    @Autowired
    private AuthService authService;

    public AuthContoller() {

    }

    /**
     *Takes a users google ID and returns a session ID if the user is
     * registered for SmartSync
     * @param googleId google id of user
     * @return session id for user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/login", produces = "application/json")
    public ResponseEntity logIn(@RequestHeader String googleId) {
        logger.info("Just recieved login Attempt from: " + googleId);
        UserPOJO authUser = userServiceCommunication.getUserByGoogleId(googleId);

        if(authUser == null) {
            String url = "auth/login";
            String message = "User not registered for SmartSync";

            throw new UserNotRegisteredException(message,url);
        }

        UUID newSessionId = UUID.randomUUID();
        Auth newAuth = new Auth(authUser.getUserId(),
                newSessionId.toString(), authUser.getRole());

        authService.addAuth(newAuth);
        return ResponseEntity.ok(newAuth.getSessionId());
    }


    /**
     * Ends a session for a given user
     * @param sessionId session to end
     * @return user id correlating to terminated session
     */
    @RequestMapping(method = RequestMethod.GET , value = "/logout", produces = "application/json")
    public ResponseEntity logOut(@RequestHeader String sessionId) {
        logger.info("Just recieved logout attmept");
        Auth userAuth = authService.removeAuth(sessionId);

        if(userAuth == null) {
            String message = "Failed to end sesson. Please IT";
            String url = "auth/logout";
            throw new FailedToEndSessionException(message, url);
        }

        logger.info(userAuth.getUserId() + " successfully logged out");
        return ResponseEntity.ok(userAuth.getUserId());

    }

    /**
     * Validates a users session
     * @param id the session id for the user
     * @return True if the user is authorized throws exception otherwise
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validate/{id}", produces = "application/json")
    public ResponseEntity authenticate(@PathVariable String id) {
        logger.info("Trying to authenticate");
        Auth userAuth = authService.getAuthBySessionId(id);
        if(userAuth == null) {
            return ResponseEntity.ok("false");

        }
        logger.info(userAuth.getUserId() + " Authenticated");
        return ResponseEntity.ok("true");

    }

    @ExceptionHandler(value = UserNotRegisteredException.class)
    public ResponseEntity handleUserNotRegisteredException(UserNotRegisteredException e) {
        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("error");
    }

    @ExceptionHandler(value = FailedToEndSessionException.class)
    public ResponseEntity handleFailedToEndSessionException(UserNotRegisteredException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }


}
