package com.example.victimmicroservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AidType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String  name;

    //Relationship
    @ManyToOne
    @JoinColumn(name = "assistance_request_id")
    private AssistantRequests assistanceRequest;

}
