package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.services.ExercicesService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercices")
public class ExercicesRestController {

    @Autowired
    private ExercicesService exercicesService;

    @GetMapping("/update")
    public ResponseEntity<String> updateExercice(@RequestParam int memberId, @RequestParam String exerciceName) {
        boolean isUpdated = exercicesService.updateExerciceRow(memberId, exerciceName);
        if (isUpdated) {
            return ResponseEntity.ok("Exercice updated successfully.");
        } else {
            return ResponseEntity.status(500).body("Error occurred while updating the exercice.");
        }
    }

}
