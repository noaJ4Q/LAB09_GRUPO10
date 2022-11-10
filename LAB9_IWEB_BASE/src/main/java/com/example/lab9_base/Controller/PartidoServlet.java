package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
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

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {

            case "guardar":

                boolean partidoRepetido = false;
                boolean partidoValido = true;
                ArrayList<Partido> listaPartidos = daoPartidos.listaDePartidos();
                Partido partidoNuevo = new Partido();

                try {

                    partidoNuevo.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));
                    partidoNuevo.setFecha(request.getParameter("fecha"));

                    Seleccion seleccionLocal = new Seleccion();
                    seleccionLocal.setIdSeleccion(Integer.parseInt(request.getParameter("local")));

                    Seleccion seleccionVisitante = new Seleccion();
                    seleccionVisitante.setIdSeleccion(Integer.parseInt(request.getParameter("visitante")));

                    for (Partido p: listaPartidos){
                        if ((p.getSeleccionLocal().getIdSeleccion() == seleccionLocal.getIdSeleccion()) && (p.getSeleccionVisitante().getIdSeleccion() == seleccionVisitante.getIdSeleccion())){
                            partidoRepetido = true;
                            break;
                        }
                    }

                    if (seleccionLocal.getIdSeleccion() == seleccionVisitante.getIdSeleccion()){
                        partidoValido = false;
                    }

                    if (partidoValido && !partidoRepetido){
                        partidoNuevo.setSeleccionLocal(seleccionLocal);
                        partidoNuevo.setSeleccionVisitante(seleccionVisitante);

                        Arbitro arbitro = new Arbitro();
                        arbitro.setIdArbitro(Integer.parseInt(request.getParameter("arbitro")));
                        partidoNuevo.setArbitro(arbitro);

                        // GUARDAR
                        daoPartidos.crearPartido(partidoNuevo);
                        response.sendRedirect(request.getContextPath()+"/");
                    }
                    else {
                        // PREFERIBLE ARROJAR EXCEPCION NUEVA
                        throw new NumberFormatException();
                    }

                }catch (NumberFormatException e ){

                    ArrayList<Seleccion> listaSelecciones = daoSelecciones.listarSelecciones();
                    ArrayList<Arbitro> listaArbitros = daoArbitros.listarArbitros();

                    request.setAttribute("listaSelecciones", listaSelecciones);
                    request.setAttribute("listaArbitros", listaArbitros);

                    view = request.getRequestDispatcher("partidos/form.jsp");
                    view.forward(request, response);

                }

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":

                ArrayList<Partido> listaPartidos = daoPartidos.listaDePartidos();
                request.setAttribute("ListaPartidos",listaPartidos);
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;

            case "crear":

                ArrayList<Seleccion> listaSelecciones = daoSelecciones.listarSelecciones();
                ArrayList<Arbitro> listaArbitros = daoArbitros.listarArbitros();

                request.setAttribute("listaSelecciones", listaSelecciones);
                request.setAttribute("listaArbitros", listaArbitros);

                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);

                break;

        }

    }
}
