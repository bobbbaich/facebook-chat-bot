package com.bobbbaich.fb.bot.service.impl;

import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.repository.UserRepository;
import com.bobbbaich.fb.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        User inserted = userRepository.insert(user);
        log.debug("inserted: {}", user);
        return inserted;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        log.debug("userRepository.findByUsername(username): {}", user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new SocialUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserRoles());
        }
    }
}