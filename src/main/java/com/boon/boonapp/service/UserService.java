package com.boon.boonapp.service;

import com.boon.boonapp.dao.UserRepository;
import com.boon.boonapp.exception.UserNotFoundException;
import com.boon.boonapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User createNewUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User Id must not be present on create");
        }
        return save(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

}
