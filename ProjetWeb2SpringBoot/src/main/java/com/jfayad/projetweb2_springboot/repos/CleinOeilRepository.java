package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.CleinOeil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jfayad.projetweb2_springboot.entities.Membre;
import java.util.ArrayList;

@Repository
public interface CleinOeilRepository extends JpaRepository<CleinOeil, Integer> {

    ArrayList<CleinOeil> findAllByIdRecevant(Membre idRecevant);

    void deleteByIdRecevantAndIdEnvoyant(Membre idRecevant, Membre idEnvoyant);

}
