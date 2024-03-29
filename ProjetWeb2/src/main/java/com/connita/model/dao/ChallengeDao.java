/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.model.dao;

/**
 *
 * @author Jamil
 */
public interface ChallengeDao {
    boolean updateChallenge(int idMembre, String columnName);
    boolean isChallengeCompleted(int idMembre, int setNumber);
}
