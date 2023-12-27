package com.example.victimmicroservice.entities;

import com.example.victimmicroservice.enumeration.AssistantState;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AssistantRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Description;
    // I use enumeration for attribute state(IN_PROGRESS/COMPLETED)
    @Enumerated(EnumType.STRING)
    private AssistantState state;
    private Date date;

    //Create a user-id commun between microservice auth and victim
    private UUID userId;

    //Relationship
    @OneToMany(mappedBy = "assistanceRequest", cascade = CascadeType.ALL)
    private List<Location> locations;
    @OneToMany(mappedBy = "assistanceRequest", cascade = CascadeType.ALL)
    private List<Skills> skills;
    @OneToMany(mappedBy = "assistanceRequest", cascade = CascadeType.ALL)
    private List<AidType> aidTypes;





}
