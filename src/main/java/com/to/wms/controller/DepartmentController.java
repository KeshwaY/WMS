package com.to.wms.controller;

import com.to.wms.model.Department;
import com.to.wms.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllDepartments() {
        List<?> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Department> getDepartmentByName(@RequestParam String name) {
        Department department = departmentService.getDepartmentByName(name);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/by-city")
    public ResponseEntity<Department> getDepartmentByCity(@RequestParam String city) {
        Department department = departmentService.getDepartmentByCity(city);
        return ResponseEntity.ok(department);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addDepartment(@Valid @RequestBody Department department, @RequestParam String city) {
        departmentService.addDepartment(department, city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editDepartment(@Valid @RequestBody Department departmentToUpdate, @PathVariable String id) {
        departmentService.editDepartment(id, departmentToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
