package com.bobbbaich.fb.bot.service.twitter.listener;

import com.bobbbaich.fb.bot.service.twitter.events.ItemsCollectedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomSpringEventListener implements ApplicationListener<ItemsCollectedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(CustomSpringEventListener.class);

    private Job job;
    private JobLauncher jobLauncher;

    @Override
    public void onApplicationEvent(ItemsCollectedEvent event) {
        Set<Twitter> items = event.getItems();
        LOG.debug("items size: {}", items.size());
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Autowired
    public void setJob(Job job) {
        this.job = job;
    }
}