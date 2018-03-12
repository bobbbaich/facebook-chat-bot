package com.bobbbaich.fb.bot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl
//        implements KafkaService
{
//    private final StreamsConfig config;
//    private final StreamsBuilder streamsBuilder;
//
//    @Override
//    public void run() {
//        KStream<String, String> textLines = streamsBuilder.stream("TextLinesTopic");
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> word)
//                .count(Materialized.as("counts-store"));
//        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
//
//        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), config);
//        streams.start();
//    }
}