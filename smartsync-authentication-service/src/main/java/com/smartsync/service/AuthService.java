package com.smartsync.service;

import com.smartsync.model.Auth;
import com.smartsync.model.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by trev on 4/1/17.
 */
@Component
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public AuthService() {

    }

    public Auth getAuthByUserId(Long id) {
        return this.authRepository.findByUserId(id);
    }

    public Auth getAuthBySessionId(String id) {
        return this.authRepository.findBySessionId(id);
    }

    public Auth addAuth(Auth auth) {
        Auth a = this.authRepository.save(auth);
        return a;
    }

    public Auth removeAuth(String sessionId) {
        Auth a = this.authRepository.findBySessionId(sessionId);

        if(a == null) {
            return null;
        } else {
            this.authRepository.delete(a);
            return a;
        }
    }




}
