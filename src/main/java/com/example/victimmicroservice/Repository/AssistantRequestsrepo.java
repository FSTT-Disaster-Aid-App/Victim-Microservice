package com.example.victimmicroservice.Repository;

import com.example.victimmicroservice.entities.AssistantRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssistantRequestsrepo extends JpaRepository<AssistantRequests, UUID> {
    List<AssistantRequests> findByuserId(UUID userId);
}
