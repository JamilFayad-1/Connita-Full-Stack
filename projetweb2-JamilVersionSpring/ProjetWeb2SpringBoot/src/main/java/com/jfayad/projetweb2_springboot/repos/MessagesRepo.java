package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessagesRepo extends JpaRepository<Messages, Integer> {
    @Query(value="(SELECT * FROM messages WHERE id_membre = :id_membre AND id_ami = :id_ami) UNION ALL ( SELECT * FROM messages WHERE id_membre = :id_ami AND id_ami = :id_membre) ORDER BY created_at ASC", nativeQuery = true)
    List<Messages> findConversation(int id_membre, int id_ami);

}
