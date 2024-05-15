package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.CleinOeil;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.CleinOeilRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CleinOeilService {

    @Autowired
    private CleinOeilRepository cleinOeilRepository;

    public ArrayList<Membre> getCleinOeil(Membre membre){

        ArrayList<CleinOeil> listeCleinOeil = cleinOeilRepository.findAllByIdRecevant(membre);
        ArrayList<Membre> listeMembre = new ArrayList<>();

        for(CleinOeil cleinOeil : listeCleinOeil) {
            listeMembre.add(cleinOeil.getIdEnvoyant());
        }
        return listeMembre;
    }

    public ArrayList<LocalDateTime> getTempsCleinOeil(Membre membre){

        ArrayList<CleinOeil> listeCleinOeil = cleinOeilRepository.findAllByIdRecevant(membre);
        ArrayList<LocalDateTime> listeTempsCleinOeil = new ArrayList<>();

        for(CleinOeil cleinOeil: listeCleinOeil) {
            listeTempsCleinOeil.add(cleinOeil.getSentDateTime());
        }
        return listeTempsCleinOeil;
    }

    @Transactional
    public void sendCleinOeil(Membre idEnvoyant, Membre idRecevant){
        CleinOeil cleinOeil = new CleinOeil(idEnvoyant, idRecevant);
        cleinOeilRepository.save(cleinOeil);
    }

    @Transactional
    public void deleteCleinOeil(Membre idEnvoyant, Membre idRecevant){
        cleinOeilRepository.deleteByIdRecevantAndIdEnvoyant(idRecevant, idEnvoyant);
    }

}
