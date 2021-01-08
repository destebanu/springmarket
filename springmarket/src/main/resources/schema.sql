drop database if exists springmarket;
create database springmarket;

use springmarket;
    
        create table usuario (
       id_usuario bigint not null auto_increment,
        apellidos_usuario varchar(255),
        codseg_usuario varchar(255),
        direcfact_usuario varchar(255),
        email_usuario varchar(255),
        fechanac_usuario varchar(255),
        nombre_usuario varchar(255),
        numtarjeta_usuario varchar(255),
        password_usuario varchar(255),
        titular_usuario varchar(255),
        primary key (id_usuario)
    ) ;
    
	    create table rol (
	   id_rol int auto_increment,
	   nombre_rol varchar (40) not null,
	   primary key (id_rol)
	);
	
		create table usuario_rol (
	   id_usuario bigint not null,
	   id_rol int,
	    primary key (
	      id_usuario,
	      id_rol
	   ),
		constraint fk_usuario_rol_1 foreign key (id_usuario) references usuario (id_usuario) on delete cascade,
		constraint fk_usuario_rol_2 foreign key (id_rol) references rol (id_rol)
	);


        create table producto (
       id_producto bigint not null auto_increment,
        cantidad_producto integer,
        descripcion_producto varchar(255),
        descuento_producto double precision,
        nombre_producto varchar(255),
        precio_producto double precision,
        primary key (id_producto)
    ) ;
    
    create table compra (
       id_compra bigint not null auto_increment,
        descuento_compra double precision,
        precio_compra double precision,
        id_usuario bigint,
        primary key (id_compra),
		constraint fk_compra_usuario
        foreign key (id_usuario) references usuario (id_usuario) 
		on update cascade
    ) ;
    
    create table lineas_de_compra (
       id_compra bigint not null,
        id_producto bigint not null,
        primary key (id_compra, id_producto),
        constraint fk_compra_producto_1
        foreign key (id_compra) references compra (id_compra) 
		on delete cascade,
        constraint fk_compra_producto_2
        foreign key (id_producto) references producto (id_producto) 
		
    ) ;