package com.example.victimmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  long id;
    private String City;
    private  String Street;
    private  String Address;

    @ManyToOne
    @JoinColumn(name = "assistance_request_id")
    private AssistantRequests assistanceRequest;

}
