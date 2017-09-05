package com.bobbbaich.fb.bot.service;

import com.bobbbaich.fb.bot.repository.UserRepository;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private MessengerSendClient messengerSendClient;

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

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMessengerSendClient(MessengerSendClient messengerSendClient) {
        this.messengerSendClient = messengerSendClient;
    }
}
