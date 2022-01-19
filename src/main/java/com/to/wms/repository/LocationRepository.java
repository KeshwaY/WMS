package com.to.wms.repository;

import com.to.wms.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationByShelf(String shelf);
}
