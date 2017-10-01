package com.bobbbaich.fb.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacebookService {
    private static final Logger LOG = LoggerFactory.getLogger(FacebookService.class);

    @Autowired
    private Facebook facebook;

    public void test() {
        LOG.debug("FacebookService test()");

        List<Post> feed = facebook.feedOperations().getFeed();

        System.out.println();
    }
}
