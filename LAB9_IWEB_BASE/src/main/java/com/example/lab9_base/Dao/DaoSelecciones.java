package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase{
    public ArrayList<Seleccion> listarSelecciones() {

        ArrayList<Seleccion> selecciones = new ArrayList<>();

        String sql = "select * from seleccion";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Seleccion seleccion = new Seleccion();

                seleccion.setIdSeleccion(rs.getInt(1));
                seleccion.setNombre(rs.getString(2));
                seleccion.setTecnico(rs.getString(3));

                Estadio e = new Estadio();
                e.setIdEstadio(rs.getInt(4));
                seleccion.setEstadio(e);

                selecciones.add(seleccion);

            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return selecciones;
    }

}