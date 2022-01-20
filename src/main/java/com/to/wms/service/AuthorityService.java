package com.to.wms.service;

import com.to.wms.model.Authority;
import com.to.wms.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
