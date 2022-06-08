package com.southsystem.votingsytem.repository.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
public abstract class AbstractRepositoryKafka<T> {

    private static final String TOPIC_NAME = "topicName";
    private static final String MESSAGE_BODY = "messageBody";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    protected abstract String getTopicName();

    public void notify(T entity){

        var topicName = this.getTopicName();
        var message = entity.toString();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("KAFKA notification error", kv(TOPIC_NAME, topicName), kv("exception", Optional.of(ex).map(Throwable::getMessage)
                        .orElse("")));
            }
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("KAFKA notification success", kv(TOPIC_NAME, topicName));
            }
        });
    }
}
