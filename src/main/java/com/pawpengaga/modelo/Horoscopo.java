package com.pawpengaga.modelo;

import java.time.LocalDate;

/**
 * La clase horoscopo tiene fines de solo lectura
 */
public class Horoscopo {

  private String horoscopo;
  private LocalDate fecha_inicio;
  private LocalDate fecha_final;

  public Horoscopo(){
    // Constructor vacio
  }

  /**
   * @param horoscopo Un animal
   * @param fecha_inicio Autoexplicativo
   * @param fecha_final Autoexplicativo
   */
  public Horoscopo(String horoscopo, LocalDate fecha_inicio, LocalDate fecha_final) {
    this.horoscopo = horoscopo;
    this.fecha_inicio = fecha_inicio;
    this.fecha_final = fecha_final;
  }

  public String getHoroscopo() {
    return horoscopo;
  }

  public LocalDate getFecha_inicio() {
    return fecha_inicio;
  }

  public LocalDate getFecha_final() {
    return fecha_final;
  }

  public void setHoroscopo(String horoscopo) {
    this.horoscopo = horoscopo;
  }

  public void setFecha_inicio(LocalDate fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
  }

  public void setFecha_final(LocalDate fecha_final) {
    this.fecha_final = fecha_final;
  }

  // Metodo toString generado de manera temporal para pruebas internas
  @Override
  public String toString() {
    return "{" +
      " horoscopo='" + getHoroscopo() + "'" +
      ", fecha_inicio='" + getFecha_inicio() + "'" +
      ", fecha_final='" + getFecha_final() + "'" +
      " }";
  }

}
