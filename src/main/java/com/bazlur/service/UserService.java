package com.bazlur.service;

import com.bazlur.dto.LoginDTO;
import com.bazlur.dto.UserDTO;
import com.bazlur.eshoppers.domain.User;

import java.util.Optional;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    boolean isNotUniqueUsername(UserDTO user);
    User verifyUser(LoginDTO loginDTO);


}
