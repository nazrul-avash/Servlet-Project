package com.nazrul.service;

import com.nazrul.dto.LoginDTO;
import com.nazrul.dto.UserDTO;
import com.nazrul.eshoppers.domain.User;
import com.nazrul.eshoppers.domain.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
    public boolean isNotUniqueUsername(UserDTO user){
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }


    private String encryptPassword(String password){
        try{
            var digest = MessageDigest.getInstance("SHA-256");
            var bytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(bytes);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Unable to encrypt password", e);
        }

    }
    private static String bytesToHex(byte[] hash){
        StringBuilder hexString = new StringBuilder();
        for(byte b: hash){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    @Override
    public User verifyUser(LoginDTO loginDTO){

        var user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(()->new UserNotFoundException("User not found"+loginDTO.getUsername()));
        var encrypted = encryptPassword(loginDTO.getPassword());
        if(user.getPassword().equals(encrypted)){
            return user;
        }
        else{
            throw new UserNotFoundException("Incorrect Password");
        }
    }

}
