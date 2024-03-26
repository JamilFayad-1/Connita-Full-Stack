package com.connita.model.dao;
import com.connita.model.entities.Publication;
import com.connita.model.entities.Publication;
import com.connita.model.singleton.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PublicationImplDao implements PublicationDao{
    private String SQL_SELECT_ALL = "SELECT * FROM publication";
    private String SQL_SELECT_BY_ID_UTILISATEUR = "SELECT * FROM publication WHERE idMembre = ?";
    
    
   @Override
    public List<Publication> findAll() {
        Connection connection;
        Publication publication;
        List<Publication> publications = new ArrayList<>();
        try{
            connection = ConnexionDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                publication =  new Publication(result.getInt("id"), result.getString("titre"), result.getString("description"), result.getString("image"), result.getInt("likes"), result.getInt("idMembre"));
                publications.add(publication);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationImplDao.class.getName()).log(Level.SEVERE, null, ex);
}         finally{
            ConnexionDB.closeConnection();
        }
        return publications;
    }


    @Override
    public List<Publication> findByIdUtilisateur(int id) {
        Connection connection;
        Publication publication;
        List<Publication> publications = new ArrayList<>();
        try{
            connection = ConnexionDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID_UTILISATEUR);
            ps.setInt(1,id);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                publication =  new Publication(result.getInt("id"), result.getString("titre"), result.getString("description"), result.getString("image"), result.getInt("likes"), result.getInt("idMembre"));
                publications.add(publication);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationImplDao.class.getName()).log(Level.SEVERE, null, ex);
}         finally{
            ConnexionDB.closeConnection();
        }
        return publications;
    }
    }

