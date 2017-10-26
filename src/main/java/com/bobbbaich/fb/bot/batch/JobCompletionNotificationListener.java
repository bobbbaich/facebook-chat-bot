package com.bobbbaich.fb.bot.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);


    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOG.info("!!! JOB IS STARTING! Time to verify the results !!! JOB Status: {}", jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOG.info("!!! JOB FINISHED! Time to verify the results !!! JOB Status: {}", jobExecution.getStatus());
    }
}
