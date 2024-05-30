/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.model.dao;

import com.connita.model.entities.Challenge;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jamil
 */
public interface ChallengeDao {
    boolean updateChallenge(int idMembre, String columnName, String challengeName);
    List<Challenge> getChallengesFromDatabase();
    Map<String, Map<String, Boolean>> getStatus(int idMembre);
    boolean createChallengeRow(int memberId, String challengeName);
    boolean verifyChallengeRowExists(int memberId, String challengeName);
}
