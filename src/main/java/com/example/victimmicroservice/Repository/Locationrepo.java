package com.example.victimmicroservice.Repository;

import com.example.victimmicroservice.entities.AssistantRequests;
import com.example.victimmicroservice.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Locationrepo extends JpaRepository<Location, UUID> {
}
