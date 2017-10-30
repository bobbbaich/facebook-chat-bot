package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.twitter.BatchService;
import com.bobbbaich.fb.bot.service.twitter.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SocialService socialService;

    private BatchService batchService;

    @RequestMapping("/job")
    public void runJob() {
        batchService.runJob();
    }

    @GetMapping("/openStream")
    public String user() {
        socialService.runStream();
        return "method done!";
    }

    @GetMapping("/isAuth")
    public String isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(authentication != null);
    }

    @Autowired
    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }
}
