package org.example.box;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.repositories.InboxEventRepository;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentResultListener {
    @Autowired
    private InboxEventRepository inboxEventRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "payments-results")
    @Transactional
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            String messageKey = record.key();
            String payload = record.value();

            JsonNode json = objectMapper.readTree(payload);
            String eventId = json.get("orderId").asText() + "_" + json.get("status").asText();

            if (inboxEventRepository.existsByMessageId(eventId)) {
                ack.acknowledge();
                return;
            }

            InboxEvent event = InboxEvent.builder()
                    .messageId(eventId)
                    .eventType(json.get("status").asText().equals("SUCCESS") ?
                            "PaymentSucceeded" : "PaymentFailed")
                    .payload(payload)
                    .receivedAt(LocalDateTime.now())
                    .build();
            inboxEventRepository.save(event);
            ack.acknowledge();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process payment result", e);
        }
    }
}
