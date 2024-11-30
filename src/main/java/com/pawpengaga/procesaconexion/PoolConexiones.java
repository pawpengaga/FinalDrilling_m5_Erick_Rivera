package com.pawpengaga.procesaconexion;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class PoolConexiones {
  
  private static final BasicDataSource dataSource = new BasicDataSource();

  static {
    dataSource.setUrl("jdbc:postgresql:final_m5_erick_rivera");
    dataSource.setUsername("postgres");
    dataSource.setPassword("12345678");
    dataSource.setDriverClassName("org.postgresql.Driver");

    dataSource.setInitialSize(5);
    dataSource.setMaxTotal(20);
    dataSource.setMaxIdle(10);
    dataSource.setMinIdle(5);
  }

  public static DataSource getDataSource(){
    return dataSource;
  }
}
