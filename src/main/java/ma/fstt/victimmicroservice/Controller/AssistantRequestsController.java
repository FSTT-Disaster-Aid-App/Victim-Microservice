package ma.fstt.victimmicroservice.Controller;

import ma.fstt.victimmicroservice.Repository.AssistantRequestsrepo;

import ma.fstt.victimmicroservice.entities.AssistantRequests;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/victim/AssistantRequests")
public class AssistantRequestsController {

	@Autowired
	private AssistantRequestsrepo assistantrequestsrepo;

	@PostMapping
	public ResponseEntity<Map<String, Object>> createAssistanceOffer(@RequestBody AssistantRequests assistanceOffer) {
		try {
			AssistantRequests savedAssistanceORequest = assistantrequestsrepo.save(assistanceOffer);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedAssistanceORequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
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
