package com.bobbbaich.fb.bot.service.impl;

import com.bobbbaich.fb.bot.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final StreamsConfig config;
    private final StreamsBuilder streamsBuilder;

    @Override
    public void run() {
        KStream<String, String> textLines = streamsBuilder.stream("TextLinesTopic");
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                .count(Materialized.as("counts-store"));
        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), config);
        streams.start();
    }
}