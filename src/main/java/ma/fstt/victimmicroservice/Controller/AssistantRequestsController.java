package ma.fstt.victimmicroservice.Controller;

import ma.fstt.victimmicroservice.Repository.AidtypeRepo;
import ma.fstt.victimmicroservice.Repository.AssistantRequestsrepo;
import ma.fstt.victimmicroservice.Repository.Locationrepo;
import ma.fstt.victimmicroservice.Repository.SkillsRepo;
import ma.fstt.victimmicroservice.entities.AidType;
import ma.fstt.victimmicroservice.entities.AssistantRequests;
import ma.fstt.victimmicroservice.entities.Location;
import ma.fstt.victimmicroservice.entities.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/victim/AssistantRequests")
public class AssistantRequestsController {
	@Autowired
	private AssistantRequestsrepo assistantrequestsrepo;
	@Autowired
	private SkillsRepo skillsrepo;
	@Autowired
	private AidtypeRepo aidtyperepo;
	@Autowired
	private Locationrepo locationrepo;

	@PostMapping
	public ResponseEntity<Map<String, Object>> createAssistanceOffer(@RequestBody AssistantRequests assistanceOffer) {
		try {
			// Save Location entity
			Location location = assistanceOffer.getLocation();
			locationrepo.save(location);

			// Save the AssistantRequests entity
			AssistantRequests savedAssistanceRequest = assistantrequestsrepo.save(assistanceOffer);

			// Set the association in Skills and save
			Set<Skills> skills = savedAssistanceRequest.getSkills();
			skills.forEach(skill -> {
				Set<AssistantRequests> assistanceRequestsSet = new HashSet<>();
				assistanceRequestsSet.add(savedAssistanceRequest);
				skill.setAssistancerequest(assistanceRequestsSet);
			});

			// Set the association in AidType and save
			Set<AidType> aidTypes = savedAssistanceRequest.getAidType();
			aidTypes.forEach(aidType -> {
				Set<AssistantRequests> assistanceRequestsSet = new HashSet<>();
				assistanceRequestsSet.add(savedAssistanceRequest);
				aidType.setAssistancerequest(assistanceRequestsSet);
			});

			// Save all Skills entities
			skillsrepo.saveAll(skills);

			// Save all AidType entities
			aidtyperepo.saveAll(aidTypes);

			// Build the response map
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("data", savedAssistanceRequest);

			return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
		} catch (Exception e) {
			// Handle the exception and provide an error response
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
		}
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllAssistanceOffers() {
		try {
			List<AssistantRequests> allAssistanceRequest = assistantrequestsrepo.findAll();
			return ResponseEntity.ok(Map.of("data", allAssistanceRequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getAssistanceOfferById(@PathVariable UUID id) {
		AssistantRequests assistanceRequest = assistantrequestsrepo.findById(id).orElse(null);
		if (assistanceRequest != null) {
			return ResponseEntity.ok(Map.of("data", assistanceRequest));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceOffer not found"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateAssistanceOffer(@PathVariable UUID id,
			@RequestBody AssistantRequests updatedAssistanceRequest) {
		AssistantRequests existingAssistanceRequest = assistantrequestsrepo.findById(id).orElse(null);
		if (existingAssistanceRequest != null) {
			updatedAssistanceRequest.setId(id);
			AssistantRequests savedAssistanceOffer = assistantrequestsrepo.save(updatedAssistanceRequest);
			return ResponseEntity.ok(Map.of("data", savedAssistanceOffer));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceOffer not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteAssistanceRequest(@PathVariable UUID id) {
		if (assistantrequestsrepo.existsById(id)) {
			assistantrequestsrepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceRequest not found"));
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> getAssistanceOffersByUserId(@PathVariable UUID userId) {
		try {
			List<AssistantRequests> userAssistanceRequest = assistantrequestsrepo.findByuserId(userId);
			return ResponseEntity.ok(Map.of("data", userAssistanceRequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}
}
