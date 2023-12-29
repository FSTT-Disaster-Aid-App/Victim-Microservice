package com.example.victimmicroservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assistance_request_skill_type", joinColumns = @JoinColumn(name = "skils_id"), inverseJoinColumns = @JoinColumn(name = "assistancee_request_id"))
    private Set<AssistantRequests> assistancerequest;

}
