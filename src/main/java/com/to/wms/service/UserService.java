package com.to.wms.service;

import com.to.wms.model.User;
import com.to.wms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user, String roleName) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByName(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String id, User userToUpdate) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("No such user found"));
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        user.setLogin(userToUpdate.getLogin());
        user.setName(userToUpdate.getName());
        user.setSurname(userToUpdate.getSurname());
        user.setPhoneNumber(userToUpdate.getPhoneNumber());
        userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("No such user found"));
        userRepository.delete(user);
    }
}
