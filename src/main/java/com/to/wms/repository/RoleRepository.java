package com.to.wms.repository;

import com.to.wms.model.Authority;
import com.to.wms.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByName(String name);
    List<Role> findByAuthoritiesIsContaining(List<Authority> authorities);
    List<Role> findByAuthoritiesIsContaining(Authority authority);

}
