package com.pawpengaga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.pawpengaga.modelo.Horoscopo;
import com.pawpengaga.modelo.Usuario;
import com.pawpengaga.modelo.ZodiacoEnum;
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
      
      if (stmt.executeUpdate() > 0) {
        System.out.println("Usuario registrado!");
        return true;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return false;
  }

  /**
   * Segundo paso. Obtencion de un solo usuario en base a sus credenciables para el login
   * @param correo
   * @param clave
   * @return
   * @throws SQLException
   */
  public Usuario getUsuariobyCredentials(String correo, String clave) throws SQLException{

    String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

    try {
      Connection conn = DatabaseConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);

      stmt.setString(1, correo);
      stmt.setString(2, clave);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Usuario user = new Usuario(
          rs.getInt("id"),
          rs.getString("nombre"),
          rs.getString("username"),
          rs.getString("email"),
          rs.getDate("fecha_nacimiento").toLocalDate(),
          "[FILTERED]", // Se deja fuera la contraseña del objeto publico
          rs.getString("animal")
        );
        return user;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }

  


  /* ************************************************ METODOS PRIVADOS ************************************************ */

  /**
   * Metodo interno que decide que animal del zodiaco le corresponde a un usuario en funcion de su fecha de nacimiento.
   * @param fecha La fecha de nacimiento del usuario
   * @return El animal que corresponda
   */
  private String registroAnimal(LocalDate fecha){

    HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
    List<Horoscopo> listaHoroscopo = horoscopoDAO.getHoroscopo();

    for (Horoscopo temp : listaHoroscopo) {
      // Metodo obteniendo de la base de datos en base a rangos
      if (fecha.isAfter(temp.getFecha_inicio()) && fecha.isBefore(temp.getFecha_final())) {
        return temp.getHoroscopo();
      } else if (fecha.equals(temp.getFecha_inicio()) || fecha.equals(temp.getFecha_final())) {
        return temp.getHoroscopo();
      } else {
        // Metodo alternativo para fechas fuera del rango... potencialmente menos preciso
        int anio = fecha.getYear();
        int resto = anio % 12;
        String zodiaco = String.valueOf(ZodiacoEnum.values()[resto]);
        
        return zodiaco;
      }
    }
    return "NO POSEE";
  }

}
