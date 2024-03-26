package com.trg.critter_chronologer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.critter_chronologer.payload.dto.PetDTO;
import com.trg.critter_chronologer.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pets")
public class PetController {
    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDTO> savePet(@Valid @RequestBody PetDTO petDTO) {
        return ResponseEntity.created(null).body(petService.save(petDTO));
    }
    
    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable long ownerId) {
        return ResponseEntity.ok(petService.getPetsByOwner(ownerId));
    }

    //get pet by id
    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable long petId) {
        return ResponseEntity.ok(petService.findById(petId));
    }
}
