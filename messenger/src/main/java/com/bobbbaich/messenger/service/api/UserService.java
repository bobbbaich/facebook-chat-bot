package com.bobbbaich.messenger.service.api;

import com.bobbbaich.messenger.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);
}
