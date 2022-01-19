package com.to.wms.service;

import com.to.wms.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BasicGenericService<RoleRepository>{

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository, RoleRepository genericRoleRepository) {
        super(genericRoleRepository);
        this.roleRepository = roleRepository;
    }


}
