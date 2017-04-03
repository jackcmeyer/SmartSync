package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com
 */
public interface ServiceRepository extends JpaRepository<Service, String> {

    /**
     * Finds a service by it's id
     * @param serviceId the service id to find by
     */
    Service findByServiceId(Long serviceId);


}
