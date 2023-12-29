package com.example.victimmicroservice.Repository;

import com.example.victimmicroservice.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillsRepo extends JpaRepository<Skills, UUID> {
}
