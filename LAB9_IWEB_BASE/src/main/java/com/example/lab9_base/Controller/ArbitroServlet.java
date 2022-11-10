package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {

            case "buscar":
                /*
                Inserte su código aquí
                */


                String searchTExt = request.getParameter("buscarId");
                ArrayList<Arbitro> lista = daoArbitros.busquedaNombre("lista", lista);
                request.setAttribute("buscarId");
                break;

            case "guardar":
                /*
                Inserte su código aquí
                */
                String arbitroNombre = request.getParameter("arbitroNombre");
                String arbitroPais = request.getParameter("arbitroPais");

                Arbitro arbitro = new Arbitro();
                arbitro.setNombre(arbitroNombre);
                arbitro.setPais(arbitroPais);
                daoArbitros.crearArbitro(arbitro);

                response.sendRedirect(request.getContextPath()+"/ArbitroServlet");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        Arbitro arbitro;
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":
                /*
                Inserte su código aquí
                 */

                ArrayList<Arbitro> listaArbitro = daoArbitros.listarArbitros();
                request.setAttribute("listaArbitro", listaArbitro);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "crear":
                /*
                Inserte su código aquí
                */
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/arbitros/list.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "borrar":
                /*
                Inserte su código aquí
                */
                String idArbitroStr = request.getParameter("id");
                int idArbitro = Integer.parseInt(idArbitroStr);
                daoArbitros.borrarArbitro(idArbitro);
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");

                break;
        }
    }
}
