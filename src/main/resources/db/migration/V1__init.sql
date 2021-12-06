CREATE TABLE cliente (
  id serial,
  cedula VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NULL,
  celular VARCHAR(45) NULL,
  PRIMARY KEY (id)
   );

   CREATE TABLE traje (
        id serial,
        descripcion VARCHAR(45) NOT NULL,
        PRIMARY KEY (id)
      );

       CREATE TABLE pedido (
          id serial,
          cantidad INT NOT NULL,
          PRIMARY KEY (id),
          traje_id int,
          foreign key (traje_id) references traje(id),
          cliente_id int,
          foreign key (cliente_id) references cliente(id)

        );




