package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Integer> {

    @Query("SELECT u FROM Membre u WHERE u.email=:email and u.password=:password")
    public Membre findMembreByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    Optional<Membre> findByEmail(String email);

    Optional<Membre> findByIdMembre(int idMembre);

}
