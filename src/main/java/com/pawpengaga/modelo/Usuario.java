package com.pawpengaga.modelo;

import java.time.LocalDate;

public class Usuario {

  private int id;
  private String nombre;
  private String username;
  private String email;
  private LocalDate fecha_nacimiento;
  private String password;
  private String animal;
  
  public Usuario(){

  }

  public Usuario(String nombre, String username, String email, LocalDate fecha_nacimiento, String password, String animal) {
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.fecha_nacimiento = fecha_nacimiento;
    this.password = password;
    this.animal = animal; // Sera definido a partir de un metodo externo durante el registro, pero ingresarlo directamente sera posible
  }

  public Usuario(String nombre, String username, String email, LocalDate fecha_nacimiento, String password) {
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.fecha_nacimiento = fecha_nacimiento;
    this.password = password;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getFecha_nacimiento() {
    return this.fecha_nacimiento;
  }

  public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
    this.fecha_nacimiento = fecha_nacimiento;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAnimal() {
    return this.animal;
  }

  public void setAnimal(String animal) {
    this.animal = animal;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", nombre='" + getNombre() + "'" +
      ", username='" + getUsername() + "'" +
      ", email='" + getEmail() + "'" +
      ", fecha_nacimiento='" + getFecha_nacimiento() + "'" +
      ", animal='" + getAnimal() + "'" +
      " }";
  }

}
