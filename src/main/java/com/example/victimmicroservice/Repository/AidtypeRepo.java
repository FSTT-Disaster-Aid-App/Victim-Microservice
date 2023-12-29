package com.example.victimmicroservice.Repository;

import com.example.victimmicroservice.entities.AidType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AidtypeRepo extends JpaRepository<AidType, UUID> {
}
