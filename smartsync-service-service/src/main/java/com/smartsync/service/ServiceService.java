package com.smartsync.service;

import com.smartsync.dto.UpdateServiceDTO;
import com.smartsync.model.Service;
import com.smartsync.model.ServiceRepository;
import communication.HouseholdServiceCommunication;
import model.HouseholdPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Service Service
 */
@Component
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public ServiceService() {

    }

    /**
     * Get a service by their id
     * @param serviceId the user Id
     * @return
     */
    public Service getServiceById(Long serviceId) {
        return serviceRepository.findByServiceId(serviceId);
    }


    /**
     * Gets all of the user's in the database
     * @return all of the user's
     */
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    /**
     * Adds a service to the database
     * @param service the service to add
     * @return the new service that is saved
     */
    public Service addService(Service service) {
        Service savedService = this.serviceRepository.save(service);

        return savedService;
    }

    /**
     * Updates the user
     * @param service the service to update
     * @return the new service
     */
    public Service updateService(UpdateServiceDTO service) {

        Service savedService = this.serviceRepository.findByServiceId(service.getServiceId());

        savedService.setDescription(service.getDescription());
        savedService.setName(service.getName());
        savedService.setIsActive(service.getIsActive());
        savedService.setWide(service.getWide());
        savedService.setTall(service.getTall());
        savedService.setCreated(service.getCreated());
        savedService.setLastUpdated(new Date());

        this.serviceRepository.save(savedService);

        return savedService;

    }

    /**
     * Deletes a service from the database
     *
     * @param id the id to delete
     *
     * @return the deleted service
     */
    public Service deleteService(Long id) {
        Service deletedService = this.serviceRepository.findByServiceId(id);

        if(deletedService == null) {
            return null;
        }

        else {

            this.serviceRepository.delete(deletedService);

            return deletedService;
        }
    }

}
