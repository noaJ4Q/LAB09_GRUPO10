package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase{
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        try(Connection connection = this.getConection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from arbitro")){

            while(rs.next()){
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));
            }

        }catch(SQLException e){
            throw new RuntimeException();
        }
        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {
        /*
        Inserte su código aquí
        */
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
    }
}
