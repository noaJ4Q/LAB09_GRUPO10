package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
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

                arbitros.add(arbitro);
            }

        }catch(SQLException e){
            throw new RuntimeException();
        }
        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {

        String sql = "INSERT INTO arbitro (nombre, pais) VALUES (?,?)";
        try(Connection connection = this.getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2,arbitro.getPais());
            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where pais like ?";

        try(Connection connection = this.getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, "%"+pais+"%");

            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Arbitro arbitro = new Arbitro();
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                    arbitros.add(arbitro);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where nombre like ?";

        try(Connection connection = this.getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, "%"+nombre+"%");
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Arbitro arbitro = new Arbitro();
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                    arbitros.add(arbitro);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();

        String sql = "select * from arbitro where idArbitro = ?";
        try(Connection connection = this.getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return arbitro;
    }

    public void borrarArbitro(int idArbitro) {
        String sql = "DELETE FROM arbitro WHERE idArbitro = ? ";
        try(Connection connection = this.getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,idArbitro);
            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
