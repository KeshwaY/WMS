package com.to.wms.service;

import com.to.wms.model.Department;
import com.to.wms.model.Location;
import com.to.wms.repository.DepartmentRepository;
import com.to.wms.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalisationService extends BasicGenericService<LocationRepository>{

    private final LocationRepository locationRepository;
    private final DepartmentRepository departmentRepository;

    public LocalisationService(LocationRepository genericLocationRepository, LocationRepository locationRepository, DepartmentRepository departmentRepository) {
        super(genericLocationRepository);
        this.locationRepository = locationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public Location getLocationByShelf(String shelf) {
        return locationRepository.findLocationByShelf(shelf);
    }

    @Transactional(readOnly = true)
    public List<Location> getAllLocationsByDepartment(String departmentName) {
        return locationRepository.findAllLocationsByDepartmentName(departmentName);
    }

    public void addLocation(Location location, String departmentName) {
        Department department = departmentRepository.findDepartmentByName(departmentName);
        location.setDepartment(department);
        locationRepository.save(location);
    }

    public void editLocation(String locationId, Location locationToUpdate) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalStateException("Location not found"));
        location.setShelf(locationToUpdate.getShelf());
        locationRepository.save(location);
    }
}
