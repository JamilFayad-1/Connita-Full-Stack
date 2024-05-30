package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.ChallengesJouer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChallengesJouerRepository extends JpaRepository<ChallengesJouer,Integer> {

    boolean existsChallengesJouerByIdMembreAndChallengeName(int memberId, String challengeName);

    @Query(value = "SELECT challenge_name, first_set_complete, second_set_complete, third_set_complete FROM challenges cj WHERE cj.id_membre=:idMembre", nativeQuery = true)
    List<Map<String, Object>> getChallengeStatus(int idMembre);

    @Transactional
    @Modifying
    void deleteAllByIdMembre(int memberId);

}
