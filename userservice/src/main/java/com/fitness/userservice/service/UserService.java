package com.fitness.userservice.service;


import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserResponse register(RegisterRequest req) {

        if(userRepository.existsByEmail(req.getEmail()))
            throw  new RuntimeException("Email Already Exists");

            User user = new User();
            user.setEmail(req.getEmail());
            user.setPassword(req.getPassword());
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());

            User savedUser = userRepository.save(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(savedUser.getId());
            userResponse.setEmail(savedUser.getEmail());
            userResponse.setPassword(savedUser.getPassword());
            userResponse.setFirstName(savedUser.getFirstName());
            userResponse.setLastName(savedUser.getLastName());
            userResponse.setCreatedAt(savedUser.getCreatedAt());
            userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        return  userResponse;

    }

    public UserResponse getUserProfile(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User does not Exists"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return  userResponse;


    }
}
