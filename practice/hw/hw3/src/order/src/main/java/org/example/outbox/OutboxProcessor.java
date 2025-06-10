package org.example.outbox;

import lombok.extern.slf4j.Slf4j;
import org.example.repositories.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OutboxProcessor {

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void processOutboxEvents() {
        List<OutboxEvent> events = outboxEventRepository.findByProcessedFalse();

        for (OutboxEvent event : events) {
            try {
                // Отправляем сообщение в Kafka
                kafkaTemplate.send("orders", event.getPayload());

                // Помечаем как обработанное
                event.setProcessed(true);
                outboxEventRepository.save(event);
            } catch (Exception e) {
                // Логируем ошибку и пробуем в следующий раз
                log.error("Failed to process outbox event: " + event.getId(), e);
            }
        }
    }
}
