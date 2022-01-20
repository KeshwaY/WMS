package com.to.wms.service;

import com.to.wms.controller.dto.GenericPutDto;
import com.to.wms.model.Authority;
import com.to.wms.model.Role;
import com.to.wms.model.User;
import com.to.wms.repository.AuthorityRepository;
import com.to.wms.repository.RoleRepository;
import com.to.wms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService  extends BasicGenericService<UserRepository> {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public void addUser(User user) {
        user.setRole(getUserRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Role getUserRole() {
        if (repository.getAllCount() == 0) {
            Authority userManagement = authorityRepository.findByName("OP_USER_MANAGEMENT");
            Authority roleManagement = authorityRepository.findByName("OP_ROLE_MANAGEMENT");
            return roleRepository.findByAuthoritiesIsContaining(List.of(userManagement, roleManagement)).get(0);
        }
        return roleRepository.findByAuthoritiesIsContaining(authorityRepository.findByName("BASIC_USER")).get(0);
    }

    @Transactional(readOnly = true)
    public User getUserByName(String login) {
        return repository.findUserByLogin(login).orElseThrow();
    }

    public User updateUser(String login, String changeType, GenericPutDto genericPutDto) {
        User user = repository.findUserByLogin(login).orElseThrow(() -> new IllegalStateException("No such user found"));
        switch (changeType) {
            case "email":
                user.setEmail(genericPutDto.getNewValue());
                break;
            case "password":
                user.setPassword(passwordEncoder.encode(genericPutDto.getNewValue()));
                break;
            case "login":
                String newLogin = genericPutDto.getNewValue();
                Optional<User> userWithLogin = repository.findUserByLogin(newLogin);
                if (userWithLogin.isPresent()) {
                    throw new IllegalStateException("Login is not unique!");
                }
                user.setLogin(newLogin);
                break;
            case "name":
                user.setName(genericPutDto.getNewValue());
                break;
            case "surname":
                user.setSurname(genericPutDto.getNewValue());
                break;
            case "phone_number":
                user.setPhoneNumber(genericPutDto.getNewValue());
                break;
            case "role":
                Role newRole = roleRepository.findByName(genericPutDto.getNewValue().toUpperCase(Locale.ROOT));
                if (newRole == null) {
                    throw new IllegalStateException("Role does not exist!");
                }
                user.setRole(newRole);
                break;
            default:
                return user;
        }
        return repository.save(user);
    }

    public void deleteUser(String userId) {
        User user = repository.findUserByLogin(userId).orElseThrow(() -> new IllegalStateException("No such user found"));
        repository.delete(user);
    }
}
