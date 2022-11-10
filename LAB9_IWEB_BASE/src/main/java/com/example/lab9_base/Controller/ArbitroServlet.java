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

                String filtro = request.getParameter("tipo");
                ArrayList<Arbitro> listaArbitros = new ArrayList<>();

                switch (filtro) {
                    case "nombre":
                        listaArbitros = daoArbitros.busquedaNombre(request.getParameter("buscar"));
                        break;
                    case "pais":
                        listaArbitros = daoArbitros.busquedaPais(request.getParameter("buscar"));
                        break;
                }

                request.setAttribute("listaArbitros", listaArbitros);
                request.setAttribute("opciones", opciones);
                request.setAttribute("busqueda", request.getParameter("buscar"));

                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);

                break;

            case "guardar":


                boolean arbitroRepetido = false;
                listaArbitros = daoArbitros.listarArbitros();

                String arbitroNombre = request.getParameter("nombre");
                String arbitroPais = request.getParameter("pais");

                for (Arbitro a: listaArbitros){
                    if (a.getNombre().equalsIgnoreCase(arbitroNombre)){
                        arbitroRepetido = true;
                        break;
                    }
                }

                if (!arbitroRepetido){
                    Arbitro arbitro = new Arbitro();
                    arbitro.setNombre(arbitroNombre);
                    arbitro.setPais(arbitroPais);
                    daoArbitros.crearArbitro(arbitro);

                    response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                }
                else {
                    ArrayList<String> paises = new ArrayList<>();
                    paises.add("Peru");
                    paises.add("Chile");
                    paises.add("Argentina");
                    paises.add("Paraguay");
                    paises.add("Uruguay");
                    paises.add("Colombia");

                    request.setAttribute("paises", paises);

                    view = request.getRequestDispatcher("arbitros/form.jsp");
                    view.forward(request, response);

                }

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

        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":

                ArrayList<Arbitro> listaArbitros = daoArbitros.listarArbitros();
                request.setAttribute("listaArbitros", listaArbitros);
                request.setAttribute("opciones", opciones);
                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);

                break;

            case "crear":

                request.setAttribute("paises", paises);

                view = request.getRequestDispatcher("arbitros/form.jsp");
                view.forward(request, response);

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
