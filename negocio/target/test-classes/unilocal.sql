insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Calarcá");
insert into ciudad values (3, "Salento");

insert into usuario(id, nombre, email, nickname, password, ciudad_id) values (1, "Luis", "luis@email.com", "luis12", "262ac", 2);
insert into usuario(id, nombre, email, nickname, password, ciudad_id) values (2, "Maria", "maria@email.com", "maria91", "asa2", 2);
insert into usuario(id, nombre, email, nickname, password, ciudad_id) values (3, "Lucia", "lucia@email.com", "lucia_", "4343", 2);
insert into usuario(id, nombre, email, nickname, password, ciudad_id) values (4, "Carlos", "carlos@email.com", "carlos_", "1234", 2);
insert into usuario(id, nombre, email, nickname, password, ciudad_id) values (5, "Luis", "luisp@email.com", "lu92", "6432", 3);

insert into administrador(id, nombre, email, nickname, password) values (1, "Admin", "admin@email.com", "admin1", "6432");

insert into moderador(id, nombre, email, nickname, password, admin_id) values (1, "Moderador", "moderador@email.com", "moder1", "dfsdv", 1);

insert into tipo_lugar(id, nombre) values (1, "Restaurante");
insert into tipo_lugar(id, nombre) values (2, "Pizza");

insert into lugar(id, descripcion, estado, fecha_aprobacion, fecha_creacion, latitud, longitud, nombre, ciudad_id, moderador_id, tipo_id, usuario_creador_id) values (1, "La mejor pizza", 1, "2021/01/22", "2021/01/22", 4.76222, 72.34344, "Pizza Bambini", 1, 1, 2, 2);
insert into lugar(id, descripcion, estado, fecha_aprobacion, fecha_creacion, latitud, longitud, nombre, ciudad_id, moderador_id, tipo_id, usuario_creador_id) values (2, "Restaurante paisa los mejores frijoles", 1, "2020/12/04", "2020/12/04", 4.76222, 72.34344, "El paisa", 2, 1, 1, 3);
insert into lugar(id, descripcion, estado, fecha_aprobacion, fecha_creacion, latitud, longitud, nombre, ciudad_id, moderador_id, tipo_id, usuario_creador_id) values (3, "Una pizza muy interesante", 1, "2021/02/22", "2021/03/22", 4.76222, 72.34344, "Pizza 2x3", 1, 1, 2, 4);
insert into lugar(id, descripcion, estado, fecha_aprobacion, fecha_creacion, latitud, longitud, nombre, ciudad_id, moderador_id, tipo_id, usuario_creador_id) values (4, "Restaurante cool", 1, "2021/02/22", "2021/03/22", 4.76222, 72.34344, "Restaurante XL", 1, 1, 1, 2);

insert into lugar_imagenes(lugar_id, url_imagen) values (1, "ruta/img1.png");
insert into lugar_imagenes(lugar_id, url_imagen) values (2, "ruta/img2.png");
insert into lugar_imagenes(lugar_id, url_imagen) values (1, "ruta/img3.png");
insert into lugar_imagenes(lugar_id, url_imagen) values (1, "ruta/img4.png");
insert into lugar_imagenes(lugar_id, url_imagen) values (2, "ruta/img5.jpg");

insert into lugar_telefonos(id_lugar, numero_telefono) values (2, "3129991234");
insert into lugar_telefonos(id_lugar, numero_telefono) values (1, "7508123");
insert into lugar_telefonos(id_lugar, numero_telefono) values (1, "3102126511");

insert into comentario(id, calificacion, comentario, fecha_creacion, respuesta, lugar_id, usuario_id) values (1, 3, "Muy regular u_u", "2021/04/01 12:32:12", "", 1, 5);
insert into comentario(id, calificacion, comentario, fecha_creacion, respuesta, lugar_id, usuario_id) values (2, 4, "Muy bueno", "2021/01/11 16:32:12", "", 1, 4);
insert into comentario(id, calificacion, comentario, fecha_creacion, respuesta, lugar_id, usuario_id) values (3, 1, "Mala atención", "2021/03/17 13:32:12", "", 2, 1);
insert into comentario(id, calificacion, comentario, fecha_creacion, respuesta, lugar_id, usuario_id) values (4, 5, "Excelente", "2021/04/21 07:11:01", "", 3, 1);

insert into favorito(id_usuario, id_lugar) values (1, 1);
insert into favorito(id_usuario, id_lugar) values (3, 1);
insert into favorito(id_usuario, id_lugar) values (1, 2);

insert into horario(id, dia_semana, hora_fin, hora_inicio, lugar_id) values (1, "Viernes", "20:00", "10:00", 1);
insert into horario(id, dia_semana, hora_fin, hora_inicio, lugar_id) values (2, "Sábado", "20:00", "10:00", 1);
insert into horario(id, dia_semana, hora_fin, hora_inicio, lugar_id) values (3, "Semana", "20:00", "10:00", 2);
insert into horario(id, dia_semana, hora_fin, hora_inicio, lugar_id) values (4, "Domingo", "20:00", "10:00", 1);