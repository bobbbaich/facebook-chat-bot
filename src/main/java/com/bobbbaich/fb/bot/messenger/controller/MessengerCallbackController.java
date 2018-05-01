package com.bobbbaich.fb.bot.messenger.controller;

import com.bobbbaich.fb.bot.messenger.processor.delegate.MessengerEventDelegate;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.messenger4j.Messenger.*;
import static java.util.Optional.empty;

/**
 * This is the main class for inbound and outbound communication with the Facebook Messenger Platform.
 * The callback controller is responsible for the webhook verification and processing of the inbound messages and event.
 * It showcases the features of the Messenger Platform.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/callback")
public class MessengerCallbackController {
    private final Messenger messenger;
    private final MessengerEventDelegate eventDelegate;

    /**
     * Webhook verification endpoint.
     * <p>
     * The passed verification token (as query parameter) must match the configured verification token.
     * In case this is true, the passed challenge string must be returned by this endpoint.
     */
    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
        log.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}",
                mode, verifyToken, challenge);
        try {
            this.messenger.verifyWebhook(mode, verifyToken);
            log.info("Webhook verification done");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e) {
            log.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    /**
     * Callback endpoint responsible for processing the inbound messages and event.
     */
    @PostMapping
    public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
                                               @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        log.debug("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
        try {
            messenger.onReceiveEvents(payload, empty(), eventDelegate::onEvent);
            log.debug("Processed callback payload successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e) {
            log.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}