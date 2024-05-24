package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Exercices;
import com.jfayad.projetweb2_springboot.repos.ExerciceRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercicesService {

    private static final Logger log = LoggerFactory.getLogger(ExercicesService.class);

    @Autowired
    private ExerciceRepository exerciceRepository;

    public void createExerciceRow(int memberId, String exerciceName){

        try {
            Exercices exercice = new Exercices(memberId, exerciceName, false);
            exerciceRepository.save(exercice);
        }catch (Exception e){
            log.info("Error occured during the creation of a challenge row: {}", e.getMessage());
        }
    }

    @Transactional
    public boolean updateExerciceRow(int memberId, String exerciceName){

        try{
            int updatedRows = exerciceRepository.updateExerciceCompleteStatus(memberId, exerciceName, true);
            if (updatedRows > 0) {
                return true;
            } else {
                log.info("No rows updated for memberId: {}, exerciceName: {}", memberId, exerciceName);
                return false;
            }
        }catch(Exception e){
            log.info("Error occured during the updating of a challenge row: {}", e.getMessage());
            return false;
        }

    }

    public List<Exercices> getAllExercices(int idMembre){

        try{
            return exerciceRepository.findByIdExerciceMembre(idMembre);
        }catch(Exception e){
            log.info("Error occured during the getting all exercices: {}", e.getMessage());
            return new ArrayList<>();
        }
    }



}
