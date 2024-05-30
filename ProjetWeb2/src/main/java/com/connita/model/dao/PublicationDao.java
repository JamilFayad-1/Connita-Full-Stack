package com.connita.model.dao;
import com.connita.model.entities.Publication;
import java.util.List;

public interface PublicationDao {
   List<Publication> findAll();
   List<Publication> findByIdUtilisateur(int id);
}
