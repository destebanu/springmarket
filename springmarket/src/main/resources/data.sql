insert into usuario (nombre_usuario, password_usuario, apellidos_usuario, email_usuario, fechanac_usuario, numtarjeta_usuario, titular_usuario, codseg_usuario, direcfact_usuario) 
values ('admin','$2a$10$w3gWFIrpd9tBjWqSuBk5POL/0nbLbNgtUdrZqkU3e4./4uuGt7mZy', 'admin', 'admin@admin.es', '1/enero/2020', '666', 'administrator', '666', 'deepweb');
-- Pass1234

insert into usuario (nombre_usuario, password_usuario, apellidos_usuario, email_usuario, fechanac_usuario, numtarjeta_usuario, titular_usuario, codseg_usuario, direcfact_usuario)
values ('Danielus', '$2a$10$PFrD3YbfQj4JY0FmQjfROOOrT.v5CrZ8UuCeGlEat4ZbP2wlciEly','Master','meme@meme.es','16/noviembre/1994','000','XXX','619','Andalasia');
-- Passlolo

insert into usuario (nombre_usuario, password_usuario, apellidos_usuario, email_usuario, fechanac_usuario, numtarjeta_usuario, titular_usuario, codseg_usuario, direcfact_usuario)
values ('Amanduski', '$2a$10$lNvF8jqyBas9JX7lSoVctONA8HgzOh7NvJbDifjs7oNLFHNOJho8a','Chachi','mecago@entoh.es','19/mayo/1992','888','Amanduski','666','Calle Desengano 21');
-- Pass1234


insert into rol (id_rol, nombre_rol) values (1,'admin');
insert into rol (id_rol, nombre_rol) values (2, 'registrado');

insert into usuario_rol (id_usuario, id_rol) values (1,1);
insert into usuario_rol (id_usuario, id_rol) values (2,2);
insert into usuario_rol (id_usuario, id_rol) values (3,2);



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
