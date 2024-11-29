-- Conjunto de consultas para crear todos los datos necesarios

-- Crear base de datos
CREATE DATABASE final_drilling_m5;

-- Crear las tablas de horoscopo y usuarios
CREATE TABLE horoscopo(
  animal VARCHAR(30),
  fecha_inicio DATE,
  fecha_fin DATE
);

CREATE TABLE usuarios(
  id serial NOT NULL PRIMARY KEY,
  nombre VARCHAR(30),
  username VARCHAR(30),
  email VARCHAR(30),
  fecha_nacimiento DATE,
  password VARCHAR(30),
  animal VARCHAR(30)
);

-- Carga de datos (Cambiar la ruta absoluta por la usada en el proyecto)
COPY horoscopo(animal, fecha_inicio, fecha_fin)
FROM 'C:\Users\Erick Rivera\Desktop\java-traini\MODULO 5\session 16\FinalDrilling_m5_Erick_Rivera\data\horoscopo.csv'
DELIMITER ';'
HEADER;

INSERT INTO usuarios(nombre, username, email, fecha_nacimiento, password, animal) VALUES
('Erick Rivera', 'admin', 'admin@admin.com', '22-03-1997', '1234', 'Buey');

-- Para emergencias
-- DROP DATABASE final_drilling_m5;