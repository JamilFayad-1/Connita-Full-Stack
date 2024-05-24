package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercices")
public class Exercices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercice")
    private Integer idExercice;

    @Column(name = "id_membre")
    private Integer idExerciceMembre;

    @Column(name = "exercice_name", length = 100, nullable = false)
    private String exerciceName;

    @Column(name = "complete_status", nullable = false)
    private boolean completeStatus;

    public Exercices(Integer idMembre, String exerciceName, boolean completeStatus) {
        this.idExerciceMembre = idMembre;
        this.exerciceName = exerciceName;
        this.completeStatus = completeStatus;
    }

    public Exercices() {

    }

    public Integer getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Integer idExercice) {
        this.idExercice = idExercice;
    }

    public Integer getExerciceIdMembre() {
        return idExerciceMembre;
    }

    public void setExerciceIdMembre(Integer idMembre) {
        this.idExerciceMembre = idMembre;
    }

    public String getExerciceName() {
        return exerciceName;
    }

    public void setExerciceName(String exerciceName) {
        this.exerciceName = exerciceName;
    }

    public boolean isCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(boolean completeStatus) {
        this.completeStatus = completeStatus;
    }

    @Override
    public String toString() {
        return "Exercices{" +
                "idExercice=" + idExercice +
                ", idExerciceMembre=" + idExerciceMembre +
                ", ExerciceName='" + exerciceName + '\'' +
                ", completeStatus=" + completeStatus +
                '}';
    }
}
