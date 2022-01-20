package com.to.wms.controller;

import com.to.wms.controller.dto.GenericArrayPutDto;
import com.to.wms.controller.dto.GenericPutDto;
import com.to.wms.model.Role;
import com.to.wms.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addRole(
            @RequestBody @Valid Role role,
            @RequestParam("authority") List<String> authorities
    ) {
        roleService.addRole(role, authorities);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{role_name}")
    public ResponseEntity<Role> getRole(
            @PathVariable("role_name") String roleName
    ) {
        Role role = roleService.getRoleByName(roleName);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{role_name}/data")
    public ResponseEntity<Void> editRole(
            @PathVariable("role_name") String roleName,
            @RequestParam("update") String updateType,
            @RequestBody @Valid GenericPutDto genericPutDto
    ) {
        roleService.editRole(roleName, updateType, genericPutDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{role_name}/authorities")
    public ResponseEntity<Void> editRole(
            @PathVariable("role_name") String roleName,
            @RequestBody @Valid GenericArrayPutDto genericArrayPutDto
    ) {
        roleService.editAuthorities(roleName, genericArrayPutDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{role_name}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable("role_name") String roleName
    ) {
        roleService.deleteRoleByName(roleName);
        return ResponseEntity.ok().build();
    }

}
