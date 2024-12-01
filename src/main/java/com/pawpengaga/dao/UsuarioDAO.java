package com.pawpengaga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pawpengaga.modelo.Horoscopo;
import com.pawpengaga.modelo.Usuario;
import com.pawpengaga.modelo.ZodiacoEnum;
import com.pawpengaga.procesaconexion.PoolConexiones;

public class UsuarioDAO {

  /**
   * Registro del usuario
   * @param user Un objeto usuario venido desde el DAO
   * @return Un valor booleano true o false
   * @throws SQLException
   */
  public boolean registrarUsuario(Usuario user) throws SQLException{

    String sql = "INSERT INTO usuarios(nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?)";    

    try (Connection conn = PoolConexiones.getDataSource().getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)) {

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

      // stmt.close();
      // conn.close();

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return false;
  }

  /**
   * Obtencion de un solo usuario en base a sus credenciables para el login
   * @param correo
   * @param clave
   * @return
   * @throws SQLException
   */
  public Usuario getUsuariobyCredentials(String correo, String clave) throws SQLException{

    String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

    try (Connection conn = PoolConexiones.getDataSource().getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)) {

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

      // stmt.close();
      // conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }

  /**
   * Consigue todos los usuarios, se filtran las claves
   * @return Una lista con todos los usuarios
   * @throws SQLException
   */
  public List<Usuario> getAllUsuarios() throws SQLException{

    String sql = "SELECT * FROM usuarios ORDER BY id";
    List<Usuario> users = new ArrayList<>();

    try (Connection conn = PoolConexiones.getDataSource().getConnection();
    Statement stmt = conn.createStatement()) {

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        users.add(new Usuario(
          rs.getInt("id"),
          rs.getString("nombre"),
          rs.getString("username"),
          rs.getString("email"),
          rs.getDate("fecha_nacimiento").toLocalDate(),
          "[FILTERED]", // Se deja fuera la contraseña del objeto publico
          rs.getString("animal")
        ));
      }

      // stmt.close();
      // conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return users;

  }

  /**
   * Hacer busquedas parciales del usuario en base a su nombre
   * @param searchQuery Un String parcial cualquiera
   * @return Una lista con todas las coincidencias
   */
  public List<Usuario> getUserByName(String searchQuery) throws SQLException {

    // Devolver una lista vacia si no hay nada para evitar hacer la consulta
    if (searchQuery == null || searchQuery.trim().isEmpty()) {
      return new ArrayList<>();
    }

    String sql = "SELECT * FROM usuarios WHERE LOWER(nombre) LIKE ? ORDER BY id";
    List<Usuario> matchSet = new ArrayList<>(); // Lista para guardar las coincidencias

    try {

      Connection conn = PoolConexiones.getDataSource().getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, "%" + searchQuery.toLowerCase() + "%");
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        matchSet.add(new Usuario(
          rs.getInt("id"),
          rs.getString("nombre"),
          rs.getString("username"),
          rs.getString("email"),
          rs.getDate("fecha_nacimiento").toLocalDate(),
          "[FILTERED]",
          rs.getString("animal")
        ));
      }

      // conn.close();
      // stmt.close();
    } catch (Exception e){
      System.err.println("Ha ocurrido una excepcion" + e.getMessage());
      e.printStackTrace();
    }

    return matchSet;
  
  }

  /**
   * Updatea un usuario y devuelve un valor booleando dependiendo de su tuvo exito o no. Puede manejar distintos escenarios en la edicion
   * @param user Un objeto usuario construido desde el formulario
   * @param currentPassword La contraseña actual del usuario con fines de comparacion de seguridad
   * @return
   */
  public boolean updateUser(Usuario user, String currentPassword){

    String sql;

    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      // Caso 1: Contrasenia vacia
      sql = "UPDATE usuarios SET nombre=?, username=?, email=?, fecha_nacimiento=?, animal=? WHERE id=? AND password=?";
    } else {
      // Caso 2: Contrasenia suplementada
      sql = "UPDATE usuarios SET nombre=?, username=?, email=?, fecha_nacimiento=?, password=?, animal=? WHERE id=? AND password=?";
    }

    try(Connection conn = PoolConexiones.getDataSource().getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, user.getNombre());
      stmt.setString(2, user.getUsername());
      stmt.setString(3, user.getEmail());
      stmt.setDate(4, java.sql.Date.valueOf(user.getFecha_nacimiento()));

      if (user.getPassword() == null || user.getPassword().isEmpty()) {
        // Caso 1: Contrasenia vacia
        stmt.setString(5, registroAnimal(user.getFecha_nacimiento()));
        stmt.setInt(6, user.getId());
        stmt.setString(7, currentPassword);
    } else {
        // Caso 2: Contrasenia suplementada
        stmt.setString(5, user.getPassword());
        stmt.setString(6, registroAnimal(user.getFecha_nacimiento()));
        stmt.setInt(7, user.getId());
        stmt.setString(8, currentPassword);
    }

      if (stmt.executeUpdate() > 0) {
        System.out.println("Usuario actualizado con exito");
        return true;
      } else {
        System.out.println("Fallo la actualizacion...");
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  /**
   * Elimina un usuario en base a su ID
   * @param idUser Conseguido desde un campo hidden ligado al current_user
   * @return
   */
  public boolean deleteUser(int idUser){
    
    String sql = "DELETE FROM usuarios WHERE id = ?";

    try(Connection conn = PoolConexiones.getDataSource().getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql)) {
    
      stmt.setInt(1, idUser);

      if (stmt.executeUpdate() > 0) {
        System.out.println("Usuario eliminado con exito");
        return true;
      } else {
        System.out.println("Fallo la eliminacion...");
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }


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
