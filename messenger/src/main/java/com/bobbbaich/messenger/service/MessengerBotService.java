package com.bobbbaich.messenger.service;

import com.bobbbaich.messenger.config.MessengerProperty;
import com.bobbbaich.messenger.service.api.BotService;
import com.bobbbaich.model.User;
import com.bobbbaich.service.UserService;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.message.TemplateMessage;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.message.template.ButtonTemplate;
import com.github.messenger4j.send.message.template.button.PostbackButton;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.userprofile.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bobbbaich.messenger.config.PostbackPayload.START_ANALYSIS_PAYLOAD;
import static com.bobbbaich.model.UserRole.ROLE_MESSENGER;
import static com.bobbbaich.model.UserRole.ROLE_USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessengerBotService implements BotService {
    private final MessengerProperty props;
    private final Messenger messenger;
    private final UserService userService;

    @Override
    public void createUser(String facebookUserId) throws MessengerApiException, MessengerIOException {
        UserProfile userProfile = messenger.queryUserProfile(facebookUserId);

        userService.create(User.builder()
                .facebookId(facebookUserId)
                .username(facebookUserId)
                .userRoles(Stream.of(ROLE_USER, ROLE_MESSENGER).collect(Collectors.toSet()))
                .firstName(userProfile.firstName())
                .lastName(userProfile.lastName())
                .locale(userProfile.locale())
                .build());
    }

    @Override
    public void startAnalysis(String recipientId) throws MessengerApiException, MessengerIOException {
        IdRecipient recipient = IdRecipient.create(recipientId);

        TextMessage message = TextMessage.create(props.getFactRequest());
        
        messenger.send(MessagePayload.create(recipient, MessagingType.RESPONSE, message));
    }

    @Override
    public void sendTextResponse(String recipientId, String response) throws MessengerApiException, MessengerIOException {
        IdRecipient recipient = IdRecipient.create(recipientId);
        TextMessage message = TextMessage.create(response);
        messenger.send(MessagePayload.create(recipient, MessagingType.RESPONSE, message));
    }

    @Override
    public void getHelp(String recipientId) throws MessengerApiException, MessengerIOException {
        IdRecipient recipient = IdRecipient.create(recipientId);

        PostbackButton buttonStartAnalysis = PostbackButton.create("Start Analysis", START_ANALYSIS_PAYLOAD);
        ButtonTemplate buttonTemplate = ButtonTemplate.create(props.getBotGeneralInfo(), Collections.singletonList(buttonStartAnalysis));
        TemplateMessage templateMessage = TemplateMessage.create(buttonTemplate);

        messenger.send(MessagePayload.create(recipient, MessagingType.RESPONSE, templateMessage));
    }
}