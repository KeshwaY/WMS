package com.to.wms.service;

import com.to.wms.controller.dto.GenericArrayPutDto;
import com.to.wms.controller.dto.GenericPutDto;
import com.to.wms.model.Authority;
import com.to.wms.model.Role;
import com.to.wms.repository.AuthorityRepository;
import com.to.wms.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RoleService extends BasicGenericService<RoleRepository>{

    private final AuthorityRepository authorityRepository;

    public RoleService(RoleRepository genericRoleRepository, AuthorityRepository authorityRepository) {
        super(genericRoleRepository);
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public void addRole(Role role, List<String> authorities) {
        authorities = authorities.stream()
                .map(a -> a.toUpperCase(Locale.ROOT))
                .collect(Collectors.toUnmodifiableList());
        List<Authority> authorityList = authorityRepository.findAllByNameIn(authorities);
        role.setName(role.getName().toUpperCase(Locale.ROOT));
        role.setAuthorities(authorityList);
        repository.save(role);
    }

    public Role getRoleByName(String roleName) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        return repository.findByName(roleName);
    }

    public void editAuthorities(String roleName, GenericArrayPutDto genericArrayPutDto) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        List<String> authoritiesNames = genericArrayPutDto.getNewValues().stream()
                .map(a -> a.toUpperCase(Locale.ROOT))
                .collect(Collectors.toUnmodifiableList());
        Role role = repository.findByName(roleName);
        List<Authority> authorities = authorityRepository.findAllByNameIn(authoritiesNames);
        role.setAuthorities(authorities);
        repository.save(role);
    }

    public void editRole(String roleName, String updateType, GenericPutDto genericPutDto) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        Role role = repository.findByName(roleName);
        switch (updateType) {
            case "name":
                role.setName(genericPutDto.getNewValue().toUpperCase(Locale.ROOT));
                break;
            case "grade":
                role.setGrade(Integer.parseInt(genericPutDto.getNewValue()));
                break;
            default:
               return;
        }
        repository.save(role);
    }

    public void deleteRoleByName(String roleName) {
        roleName = roleName.toUpperCase(Locale.ROOT);
        repository.delete(repository.findByName(roleName));
    }

}
