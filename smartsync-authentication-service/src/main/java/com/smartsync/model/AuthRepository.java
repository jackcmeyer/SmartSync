package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by trev on 4/1/17.
 */
public interface AuthRepository extends JpaRepository<Auth,Long> {

    Auth findByUserId(Long id);

    Auth findBySessionId(String id);
}
