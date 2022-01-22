package com.to.wms.repository;

import com.to.wms.model.Department;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    @Aggregation(pipeline = {
            "{$lookup: {from: 'address', localField: 'address', foreignField: '_id', as: 'addressDoc'}}, " +
                    "{$match: {'addressDoc.city': '?0'}}, {$project: {addressDoc: 0}}"
    })
    Department findDepartmentByAddressCity(String city);

    Department findDepartmentByName(String departmentName);
}
