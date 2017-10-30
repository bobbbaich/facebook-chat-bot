package com.bobbbaich.fb.bot.config;

import com.bobbbaich.fb.bot.batch.TweetItemProcessor;
import com.bobbbaich.fb.bot.batch.TwitterItemReader;
import com.bobbbaich.fb.bot.batch.TwitterItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Tweet;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private static final int CHUNK_SIZE_TWEETS = 10;

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public TwitterItemReader twitterReader() {
        return new TwitterItemReader();
    }

    @Bean
    public TweetItemProcessor processor() {
        return new TweetItemProcessor();
    }

    @Bean
    public TwitterItemWriter writer() {
        return new TwitterItemWriter();
    }

    @Bean
    public Job job(Step step1) {
        return jobBuilderFactory
                .get("myJob")
                .start(step1)
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Tweet, Tweet>chunk(CHUNK_SIZE_TWEETS)
                .reader(twitterReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Autowired
    public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Autowired
    public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }
}