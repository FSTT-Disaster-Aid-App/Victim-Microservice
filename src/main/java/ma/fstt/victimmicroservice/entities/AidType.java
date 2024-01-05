package ma.fstt.victimmicroservice.entities;

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
public class AidType {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String name;

	// Relationship
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "assistance_request_aid_type", joinColumns = @JoinColumn(name = "aid_type_id"), inverseJoinColumns = @JoinColumn(name = "assistance_request_id"))
	private Set<AssistantRequests> assistancerequest;
}
