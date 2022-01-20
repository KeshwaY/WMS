package com.to.wms.repository;

import com.to.wms.model.Location;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationByShelf(String shelf);

    @Aggregation(pipeline = {
            "{$lookup: {from: 'department', localField: 'department', foreignField: '_id', as: 'departmentDoc'}}, " +
                    "{$match: {'departmentDoc.name': '?0'}}, {$project: {departmentDoc: 0}}"
    })
    List<Location> findAllLocationsByDepartmentName(String departmentName);
}
