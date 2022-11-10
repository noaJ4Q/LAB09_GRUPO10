package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();

        String sql = "select p.*,s.nombre, ss.nombre, a.nombre , e.idEstadio,e.nombre from partido p "+
        "left join seleccion s on p.seleccionLocal = s.idSeleccion "+
        "left join seleccion ss on p.seleccionVisitante = ss.idSeleccion "+
        "left join estadio e on s.estadio_idEstadio = e.idEstadio "+
        "left join arbitro a on p.arbitro = a.idArbitro";

        try(Connection conn = this.getConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                Partido partido = new Partido();
                partido.setIdPartido(rs.getInt(1));
                partido.setNumeroJornada(rs.getInt(6));
                partido.setFecha(rs.getString(5));
                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(rs.getInt(2));
                seleccionLocal.setNombre(rs.getString("s.nombre"));
                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("idEstadio"));
                estadio.setNombre(rs.getString(11));
                seleccionLocal.setEstadio(estadio);
                partido.setSeleccionLocal(seleccionLocal);
                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setIdSeleccion(rs.getInt(3));
                seleccionVisitante.setNombre(rs.getString("ss.nombre"));
                partido.setSeleccionVisitante(seleccionVisitante);
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(4));
                arbitro.setNombre(rs.getString("a.nombre"));
                partido.setArbitro(arbitro);
                partidos.add(partido);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}
