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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
    private String City;
    private  String Street;
    private  String Address;

    @ManyToOne
    @JoinColumn(name = "assistance_request_id")
    private AssistantRequests assistanceRequest;
    @OneToMany(mappedBy = "location")
    private Set<AssistantRequests> assistanceRequests;

}
