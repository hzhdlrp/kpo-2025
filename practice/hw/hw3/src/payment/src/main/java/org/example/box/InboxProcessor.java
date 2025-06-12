package org.example.box;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.repositories.InboxEventRepository;
import org.example.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InboxProcessor {
    @Autowired
    private InboxEventRepository inboxEventRepository;
    @Autowired
    private PaymentService paymentService;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processInboxEvents() {
        inboxEventRepository.findByProcessedFalse().forEach(event -> {
            try {
                paymentService.processPaymentEvent(event.getPayload());
                event.setProcessed(true);
                inboxEventRepository.save(event);
            } catch (Exception e) {
                log.error("Failed to process outbox event: {}", event.getId(), e);
            }
        });
    }
}
