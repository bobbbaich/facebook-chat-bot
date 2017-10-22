package com.bobbbaich.fb.bot.service;

import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.dao.UserDao;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao;
    private MessengerSendClient messengerSendClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        LOG.debug("userRepository.findByUsername(username): {}", user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new SocialUser(
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
//        if (userDao.exists(user)) {
            return null;
//        } else {
//            return userDao.create(user);
//        }
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setMessengerSendClient(MessengerSendClient messengerSendClient) {
        this.messengerSendClient = messengerSendClient;
    }
}
