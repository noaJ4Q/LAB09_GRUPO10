package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Dao.DaoPartidos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {

            case "guardar":
                /*
                Inserte su código aquí
                */
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos daoPartidos = new DaoPartidos();
        switch (action) {
            case "lista":
                ArrayList<Partido> listaPartidos = daoPartidos.listaDePartidos();
                request.setAttribute("ListaPartidos",listaPartidos);
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                /*
                Inserte su código aquí
                 */
                break;

        }

    }
}
