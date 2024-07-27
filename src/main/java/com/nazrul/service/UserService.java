package com.nazrul.service;

import com.nazrul.dto.LoginDTO;
import com.nazrul.dto.UserDTO;
import com.nazrul.eshoppers.domain.User;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    boolean isNotUniqueUsername(UserDTO user);
    User verifyUser(LoginDTO loginDTO);


}
