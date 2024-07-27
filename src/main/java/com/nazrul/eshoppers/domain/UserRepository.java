package com.nazrul.eshoppers.domain;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    public Optional<User> findByUsername(String username);
}
