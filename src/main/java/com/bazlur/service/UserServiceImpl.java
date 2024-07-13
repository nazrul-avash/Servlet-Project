package com.bazlur.service;

import com.bazlur.dto.UserDTO;
import com.bazlur.eshoppers.domain.User;
import com.bazlur.eshoppers.domain.UserRepository;
import com.bazlur.eshoppers.domain.UserRepositoryImpl;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        String encrypted = encryptPassword(userDTO.getPassword());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encrypted);
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
    }
    private String encryptPassword(String password){
        return password;
    }
}
