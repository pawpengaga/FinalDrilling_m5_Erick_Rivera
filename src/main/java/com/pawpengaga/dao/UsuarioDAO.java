package com.pawpengaga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.pawpengaga.modelo.Usuario;
import com.pawpengaga.procesaconexion.DatabaseConnection;

public class UsuarioDAO {

  /**
   * Primer paso. Registro del usuario
   * @param user Un objeto usuario venido desde el DAO
   * @return Un valor booleano true o false
   * @throws SQLException
   */
  public boolean registrarUsuario(Usuario user) throws SQLException{

    String sql = "INSERT INTO usuarios(nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?)";    

    try {
      Connection conn = DatabaseConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, user.getNombre());
      stmt.setString(2, user.getUsername());
      stmt.setString(3, user.getEmail());
      stmt.setDate(4, java.sql.Date.valueOf(user.getFecha_nacimiento()));
      stmt.setString(5, user.getPassword());
      stmt.setString(6, registroAnimal(user.getFecha_nacimiento()));

      if (stmt.executeUpdate() < 0) {
        System.out.println("Usuario registrado!");
        return true;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Metodo interno que decide que animal del zodiaco le corresponde a un usuario en funcion de su fecha de nacimiento.
   * @param fecha La fecha de nacimiento del usuario
   * @return El animal que corresponda
   */
  private String registroAnimal(LocalDate fecha){
    return "";
  }

}
