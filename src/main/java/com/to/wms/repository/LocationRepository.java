package com.to.wms.repository;

import com.to.wms.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationByShelf(String shelf);

    List<Location> findAllLocationsByDepartmentName(String departmentName);
}
