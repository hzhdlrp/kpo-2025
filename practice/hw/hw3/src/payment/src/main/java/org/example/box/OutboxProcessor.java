package org.example.box;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.repositories.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OutboxProcessor {
    @Autowired
    private OutboxEventRepository outboxEventRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processOutboxEvents() {
        outboxEventRepository.findByProcessedFalse().forEach(event -> {
            try {
                String topic = "payments-results";
                String messageKey = event.getAggregateType() + "_" + event.getAggregateId();

                kafkaTemplate.send(topic, messageKey, event.getPayload());
                event.setProcessed(true);
                outboxEventRepository.save(event);
            } catch (Exception e) {
                log.error("Failed to process outbox event: {}", event.getId(), e);
            }
        });
    }
}
