package com.example.victimmicroservice.Controller;

import com.example.victimmicroservice.Repository.AidtypeRepo;
import com.example.victimmicroservice.Repository.Locationrepo;
import com.example.victimmicroservice.entities.AidType;
import com.example.victimmicroservice.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/microserviceVictim/Locations")
public class LocationController {



    @Autowired
        private Locationrepo locationRepository;

        @GetMapping
        public ResponseEntity<Map<String, Object>> getAllLocation() {
            try {
                List<Location> allALocation = locationRepository.findAll();
                return ResponseEntity.ok(Map.of("data", allALocation));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Error while processing the request"));
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Map<String, Object>> getAidTypeById(@PathVariable UUID id) {
            Location location = locationRepository.findById(id).orElse(null);
            if (location != null) {
                return ResponseEntity.ok(Map.of("data", location));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "location not found"));
            }
        }

        @PostMapping
        public ResponseEntity<Map<String, Object>> createAidType(@RequestBody Location location) {
            try {
                Location savedlocation = locationRepository.save(location);
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedlocation));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Error while processing the request"));
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Map<String, Object>> updateAidType(@PathVariable UUID id,
                                                                 @RequestBody Location updatedlocation) {
           Location existinglocation = locationRepository.findById(id).orElse(null);
            if (existinglocation != null) {
                updatedlocation.setId(id);
               Location savedlocation= locationRepository.save(updatedlocation);
                return ResponseEntity.ok(Map.of("data", savedlocation));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Location not found"));
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, Object>> deleteAidType(@PathVariable UUID id) {
            if (locationRepository.existsById(id)) {
                locationRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Location not found"));
            }
        }

    }
