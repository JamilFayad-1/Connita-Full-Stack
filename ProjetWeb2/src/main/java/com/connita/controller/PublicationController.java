package com.connita.controller;
import com.connita.model.dao.PublicationImplDao;
import com.connita.model.entities.Publication;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.jasser.formatif.model.entities.Publication;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class PublicationController extends HttpServlet {
    private PublicationImplDao publicationImplDao;
    private Publication publication;
    @Override
    public void init() throws ServletException{
        super.init();
        this.publicationImplDao = new PublicationImplDao();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            List<Publication> publications = publicationImplDao.findByIdUtilisateur(id);
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("publications", publications);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pageAccueilUtilisateur.jsp");
            dispatcher.forward(request, response);  
            
    }
}

