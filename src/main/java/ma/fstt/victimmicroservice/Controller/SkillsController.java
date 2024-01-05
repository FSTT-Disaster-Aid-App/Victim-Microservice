package ma.fstt.victimmicroservice.Controller;

import ma.fstt.victimmicroservice.Repository.AidtypeRepo;
import ma.fstt.victimmicroservice.Repository.SkillsRepo;
import ma.fstt.victimmicroservice.entities.AidType;
import ma.fstt.victimmicroservice.entities.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/victim/skills")
public class SkillsController {
	@Autowired
	private SkillsRepo skillsRepository;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllSkills() {
		try {
			List<Skills> allskills = skillsRepository.findAll();
			return ResponseEntity.ok(Map.of("data", allskills));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getSkillsById(@PathVariable UUID id) {
		Skills skils = skillsRepository.findById(id).orElse(null);
		if (skils != null) {
			return ResponseEntity.ok(Map.of("data", skils));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createSkills(@RequestBody Skills skills) {
		try {
			Skills savedskill = skillsRepository.save(skills);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedskill));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateSkills(@PathVariable UUID id,
			@RequestBody Skills updatedSkills) {
		Skills existingskills = skillsRepository.findById(id).orElse(null);
		if (existingskills != null) {
			updatedSkills.setId(id);
			Skills savedskills = skillsRepository.save(updatedSkills);
			return ResponseEntity.ok(Map.of("data", savedskills));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteSkills(@PathVariable UUID id) {
		if (skillsRepository.existsById(id)) {
			skillsRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}
}
