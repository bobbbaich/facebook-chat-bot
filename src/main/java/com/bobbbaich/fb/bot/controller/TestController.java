package com.bobbbaich.fb.bot.controller;

import com.bobbbaich.fb.bot.service.twitter.SocialService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
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

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @RequestMapping("/job")
    public void handle() throws Exception {
        jobLauncher.run(job, new JobParameters());
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
}
