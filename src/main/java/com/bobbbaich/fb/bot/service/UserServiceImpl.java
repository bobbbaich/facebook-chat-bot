package com.bobbbaich.fb.bot.service;

import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.repository.UserRepository;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private MessengerSendClient messengerSendClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserRoles());
        }
    }

    @Override
    public String create(String facebookId) {
        LOG.debug("User with facebookId: {} created", facebookId);
        try {
            messengerSendClient.sendTextMessage(facebookId, "https://dev-chat-bot.herokuapp.com/");
        } catch (MessengerApiException e) {
            e.printStackTrace();
        } catch (MessengerIOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String register(User user) {
        if (userRepository.exists(Example.of(user))) {
            return null;
        } else {
            String userId = userRepository.save(user).getId();
            return userId;
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMessengerSendClient(MessengerSendClient messengerSendClient) {
        this.messengerSendClient = messengerSendClient;
    }
}
