package com.bobbbaich.messenger.auth;

import org.springframework.social.SocialException;

public class SocialAuthorizationException extends SocialException {

    public SocialAuthorizationException(String message) {
        super(message);
    }

    public SocialAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
