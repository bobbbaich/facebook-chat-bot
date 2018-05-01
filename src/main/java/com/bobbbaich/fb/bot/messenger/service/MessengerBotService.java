package com.bobbbaich.fb.bot.messenger.service;

import com.bobbbaich.fb.bot.messenger.config.MessengerProperty;
import com.bobbbaich.fb.bot.model.User;
import com.bobbbaich.fb.bot.service.api.UserService;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.message.quickreply.TextQuickReply;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.send.recipient.Recipient;
import com.github.messenger4j.userprofile.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bobbbaich.fb.bot.messenger.config.PostbackPayload.START_ANALYSIS_PAYLOAD;
import static com.bobbbaich.fb.bot.model.UserRole.ROLE_MESSENGER;
import static com.bobbbaich.fb.bot.model.UserRole.ROLE_USER;
import static com.github.messenger4j.send.MessagingType.RESPONSE;
import static java.util.Optional.empty;
import static java.util.Optional.of;

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
        Recipient recipient = IdRecipient.create(recipientId);
        TextMessage message = TextMessage.create(props.getFactRequest());

        messenger.send(MessagePayload.create(recipient, RESPONSE, message));
    }

    @Override
    public void getHelp(String recipientId) throws MessengerApiException, MessengerIOException {
        IdRecipient recipient = IdRecipient.create(recipientId);

        TextQuickReply quickStartAnalysis = TextQuickReply.create("Start Analysis", START_ANALYSIS_PAYLOAD);
        TextMessage message = TextMessage.create(props.getBotGeneralInfo(),
                of(Collections.singletonList(quickStartAnalysis)), empty());

        messenger.send(MessagePayload.create(recipient, RESPONSE, message));
    }
}