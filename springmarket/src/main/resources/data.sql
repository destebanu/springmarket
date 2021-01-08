insert into usuario (nombre_usuario, password_usuario, apellidos_usuario, email_usuario, fechanac_usuario, numtarjeta_usuario, titular_usuario, codseg_usuario, direcfact_usuario) 
values ('admin','$2a$10$yuwJlv1ZYIEYRwNROc6Gke5x.5bHzSZvfVzVupb.56IAeES47sBPm.', 'admin', 'admin@admin.es', '1/enero/2020', '666', 'administrator', '666', 'deepweb');


insert into rol (id_rol, nombre_rol) values (1, 'rol_usuarioregistrado');
insert into rol (id_rol, nombre_rol) values (2,'rol_admin');

insert into usuario_rol (id_usuario, id_rol) values (1,1);
insert into usuario_rol (id_usuario, id_rol) values (1,2);


insert into producto (nombre_producto, descripcion_producto, precio_producto, descuento_producto) 
values ('Guitarra Acústica', 'Guitarra bonica', '499.0', '21.0');
insert into producto (nombre_producto, descripcion_producto, precio_producto, descuento_producto) 
values ('Ukelele', 'Ukelele Soprano', '49.0', '15.0');
insert into producto (nombre_producto, descripcion_producto, precio_producto, descuento_producto) 
values ('Guitarra Flamenca', 'Guitarra Made in Spain', '899.0', '21.0');
insert into producto (nombre_producto, descripcion_producto, precio_producto, descuento_producto) 
values ('Flauta Dulce', 'Todo hijo de vecino te da la turra', '19.0', '50.0');
insert into producto (nombre_producto, descripcion_producto, precio_producto, descuento_producto) 
values ('Triángulo', 'Tilín tilín', '30.0', '0.0');
