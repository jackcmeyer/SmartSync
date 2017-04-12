package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com
 */
public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {

    /**
     * Finds a service by it's id
     * @param serviceTypeId the service id to find by
     */
    ServiceType findByServiceTypeId(Long serviceTypeId);


}
