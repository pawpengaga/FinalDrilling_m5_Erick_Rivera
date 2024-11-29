package com.pawpengaga.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  public static final String dbName = "final_drilling_m5";
  public static final String dbUser = "postgres";
  public static final String dbPass = "12345678";

  private static Connection cnx = null;

  private DatabaseConnection(){
    
    try {
      Class.forName("org.postgresql.Driver");

      cnx = DriverManager.getConnection("jdbc:postgresql:"+ dbName +"?user="+ dbUser +"&password=" + dbPass);

      System.out.println("Cargo el driver...");

      if (cnx != null) {
        System.out.println("Conexion establecida");
      } else{
        System.out.println("No se pudo conectar...");
      }

    } catch (SQLException e){
      e.printStackTrace();
    } catch (ClassNotFoundException e){
      System.out.println(e.getMessage());
    }
    
  }

  public static Connection getConnection(){

    if (cnx == null) {
      new DatabaseConnection();
    }

    return cnx;
  }

  public static void main(String[] args) {
    System.out.println("Se paso por aqui");
    DatabaseConnection.getConnection();
   }
}
