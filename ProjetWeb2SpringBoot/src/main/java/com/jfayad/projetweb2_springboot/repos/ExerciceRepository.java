package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Exercices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercices,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Exercices e SET e.completeStatus = :completeStatus WHERE e.idExerciceMembre = :memberId AND e.exerciceName = :exerciceName")
    int updateExerciceCompleteStatus(int memberId, String exerciceName, boolean completeStatus);

    List<Exercices> findByIdExerciceMembre(int memberId);

    void deleteAllByIdExerciceMembre(int memberId);
}
