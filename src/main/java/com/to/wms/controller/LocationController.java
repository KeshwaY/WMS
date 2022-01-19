package com.to.wms.controller;

import com.to.wms.model.Location;
import com.to.wms.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllLocations() {
        List<?> locations = locationService.getAll();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/shelf")
    public ResponseEntity<Location> getLocationByShelf(@RequestParam String shelf) {
        Location location = locationService.getLocationByShelf(shelf);
        return ResponseEntity.ok(location);
    }


    @GetMapping("/department")
    public ResponseEntity<List<Location>> getAllLocationsByDepartment(@RequestParam String departmentName) {
        List<Location> locations = locationService.getAllLocationsByDepartment(departmentName);
        return ResponseEntity.ok(locations);
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addLocation(@RequestBody @Valid Location location, @RequestParam String departmentName) {
        locationService.addLocation(location, departmentName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Void> editLocationById(@RequestBody Location locationToUpdate, @PathVariable String locationId) {
        locationService.editLocationById(locationId, locationToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
