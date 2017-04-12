package com.smartsync.service;

import com.smartsync.dto.UpdateServiceTypeDTO;
import com.smartsync.model.Service;
import com.smartsync.model.ServiceType;
import com.smartsync.model.ServiceTypeRepository;
import communication.HouseholdServiceCommunication;
import model.HouseholdPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Service Service
 */
@Component
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService() {

    }

    /**
     * Get a service by their id
     * @param serviceTypeId the user Id
     * @return
     */
    public ServiceType getServiceTypeById(Long serviceTypeId) {
        return serviceTypeRepository.findByServiceTypeId(serviceTypeId);
    }


    /**
     * Gets all of the user's in the database
     * @return all of the user's
     */
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    /**
     * Adds a serviceType to the database
     * @param serviceType the serviceType to add
     * @return the new serviceType that is saved
     */
    public ServiceType addServiceType(ServiceType serviceType) {
        ServiceType savedServiceType = this.serviceTypeRepository.save(serviceType);

        return savedServiceType;
    }

    /**
     * Updates the user
     * @param serviceType the service to update
     * @return the new service
     */
    public ServiceType updateServiceType(UpdateServiceTypeDTO serviceType) {

        ServiceType savedServiceType = this.serviceTypeRepository.findByServiceTypeId(serviceType.getServiceTypeId());

        savedServiceType.setDescription(serviceType.getDescription());
        savedServiceType.setName(serviceType.getName());
        savedServiceType.setIsActive(serviceType.getIsActive());


        this.serviceTypeRepository.save(savedServiceType);

        return savedServiceType;

    }

    /**
     * Deletes a user from the database
     *
     * @param id the id to delete
     *
     * @return the deleted user
     */
    public ServiceType deleteServiceType(Long id) {
        ServiceType deletedServiceType = this.serviceTypeRepository.findByServiceTypeId(id);

        if(deletedServiceType == null) {
            return null;
        }

        else {

            this.serviceTypeRepository.delete(deletedServiceType);

            return deletedServiceType;
        }
    }

}
