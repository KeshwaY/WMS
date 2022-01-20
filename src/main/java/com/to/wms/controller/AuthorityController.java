package com.to.wms.controller;

import com.to.wms.model.Authority;
import com.to.wms.service.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Authority>> getAll() {
        List<Authority> authorities = authorityService.getAll();
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }

}