package com.to.wms.service;

import com.to.wms.model.Address;
import com.to.wms.model.Department;
import com.to.wms.repository.AddressRepository;
import com.to.wms.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService extends BasicGenericService<DepartmentRepository>{

    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    public DepartmentService(DepartmentRepository genericDepartmentRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        super(genericDepartmentRepository);
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    public Department getDepartmentByName(String name) {
        return departmentRepository.findDepartmentByName(name);
    }

    @Transactional(readOnly = true)
    public Department getDepartmentByCity(String city) {
        return departmentRepository.findDepartmentByAddressCity(city);
    }

    public void addDepartment(Department department, String city) {
        Address address = addressRepository.findAddressByCity(city);
        department.setAddress(address);
        departmentRepository.save(department);
    }

    public void editDepartment(String departmentId, Department departmentToUpdate) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalStateException("No department found"));
        department.setName(departmentToUpdate.getName());
        departmentRepository.save(department);
    }

}
