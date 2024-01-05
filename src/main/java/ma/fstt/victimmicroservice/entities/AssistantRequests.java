package ma.fstt.victimmicroservice.entities;

import ma.fstt.victimmicroservice.enumeration.AssistantState;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AssistantRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String Description;
	// I use enumeration for attribute state(IN_PROGRESS/COMPLETED)
	@Enumerated(EnumType.STRING)
	private AssistantState state;
	private Date date;

	// Create a user-id commun between microservice auth and victim
	private UUID userId;

	// Relationship

	@ManyToMany(mappedBy = "assistancerequest", cascade = CascadeType.ALL)
	private Set<Skills> skills;
	@ManyToMany(mappedBy = "assistancerequest", cascade = CascadeType.ALL)
	private Set<AidType> aidType;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

}
