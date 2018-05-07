package com.bobbbaich.service;

import com.bobbbaich.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);
}
