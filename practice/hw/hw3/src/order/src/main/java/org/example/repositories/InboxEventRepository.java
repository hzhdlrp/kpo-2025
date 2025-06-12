package org.example.repositories;

import org.example.box.InboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InboxEventRepository extends JpaRepository<InboxEvent, Long> {
    boolean existsByMessageId(String messageId);
    List<InboxEvent> findByProcessedFalse();
}
