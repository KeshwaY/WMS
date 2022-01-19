package com.to.wms.service;

import com.to.wms.model.Location;
import com.to.wms.repository.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

public class LocalisationService extends BasicGenericService<LocationRepository>{

    private final LocationRepository locationRepository;

    public LocalisationService(LocationRepository genericLocationRepository, LocationRepository locationRepository) {
        super(genericLocationRepository);
        this.locationRepository = locationRepository;
    }

    @Transactional(readOnly = true)
    public Location getLocationByShelf(String shelf) {
        return locationRepository.findLocationByShelf(shelf);
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public void editLocation(String locationId, Location locationToUpdate) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalStateException("Location not found"));
        location.setShelf(locationToUpdate.getShelf());
        locationRepository.save(location);
    }
}
