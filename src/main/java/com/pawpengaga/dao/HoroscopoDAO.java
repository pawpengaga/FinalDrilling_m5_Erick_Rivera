package com.pawpengaga.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pawpengaga.modelo.Horoscopo;
import com.pawpengaga.procesaconexion.DatabaseConnection;

public class HoroscopoDAO {

  public List<Horoscopo> getHoroscopo(){

    List<Horoscopo> horoscopos = new ArrayList<>();

    String sql = "SELECT * FROM horoscopo";

    try(Connection conn = DatabaseConnection.getConnection();
    Statement stmt = conn.createStatement()){
      
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        
        // Hecho todo en un solo paso.. refactorizar luego...
        horoscopos.add(new Horoscopo(
          rs.getString("animal"),
          rs.getDate("fecha_inicio").toLocalDate(),
          rs.getDate("fecha_fin").toLocalDate()
        ));
      }
      conn.close();
      stmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return horoscopos;
  }

}
