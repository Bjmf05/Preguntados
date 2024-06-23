prompt PL/SQL Developer Export Tables for user UNA@XEPDB1
prompt Created by PC on domingo, 16 de junio de 2024
set feedback off
set define off

prompt Disabling triggers for PLAM_PREGUNTAS...
alter table PLAM_PREGUNTAS disable all triggers;
prompt Disabling triggers for PLAM_RESPUESTAS...
alter table PLAM_RESPUESTAS disable all triggers;
prompt Disabling foreign key constraints for PLAM_RESPUESTAS...
alter table PLAM_RESPUESTAS disable constraint RELACION_PREGUNTAS_A_RESPUESTAS;
prompt Deleting PLAM_RESPUESTAS...
delete from PLAM_RESPUESTAS;
commit;
prompt Deleting PLAM_PREGUNTAS...
delete from PLAM_PREGUNTAS;
commit;
prompt Loading PLAM_PREGUNTAS...
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (1, 'Que perdio el soldadito de plomo?', 'Arte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (2, 'Cuantas alas tiene una pulga?', 'Ciencia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (3, 'Quien pinto La ultima cena?', 'Arte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (4, 'Cual es la formula del agua?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (5, 'Que instrumento tocaba Beethoven?', 'Arte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (6, 'Que planeta es conocido como el rojo?', 'Ciencia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (7, 'En que anno murio Picasso?', 'Arte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (8, 'Que gas es esencial para la respiracion?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (9, 'Que escultor creo El David?', 'Arte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (10, 'Que es la teoria de la relatividad?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (11, 'Quien escribio Hamlet?', 'Arte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (12, 'Que metal es mas liviano?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (13, 'En que museo esta La Gioconda?', 'Arte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (14, 'Cual es el simbolo del oro?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (15, 'Quien pinto La noche estrellada?', 'Arte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (16, 'Que organo bombea la sangre?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (17, 'Que corriente artistica lidero Dali?', 'Arte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (18, 'Cual es el planeta mas grande?', 'Ciencia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (19, 'Que material usaba Rodin?', 'Arte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (20, 'Que organo se encarga de la digestion?', 'Ciencia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (21, 'Quien gano la Copa Mundial de la FIFA 2018?', 'Deporte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (22, 'Cual es el deporte mas popular en el mundo?', 'Deporte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (23, 'En que deporte se utiliza una raqueta?', 'Deporte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (24, 'Quien es considerado el mejor jugador de baloncesto?', 'Deporte', 'A', 3, 4);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (25, 'Que pais ha ganado mas medallas olimpicas?', 'Deporte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (26, 'En que deporte se usa un bate?', 'Deporte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (27, 'Que deporte se juega en Wimbledon?', 'Deporte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (28, 'Que significa MMA?', 'Deporte', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (29, 'Cual es el torneo de futbol mas importante de Europa?', 'Deporte', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (30, 'Cuantos jugadores tiene un equipo de futbol?', 'Deporte', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (31, 'Donde se encuentra el canton de Puerto Jimenez?', 'Geografia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (32, 'Cual es el area urbana mas poblada de la India?', 'Geografia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (33, 'En que pais se encuentra la Torre Eiffel?', 'Geografia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (34, 'Cual es la capital de Japon?', 'Geografia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (35, 'Donde se encuentra el rio Amazonas?', 'Geografia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (36, 'Cual es el pais mas grande del mundo?', 'Geografia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (37, 'Que continente alberga el desierto del Sahara?', 'Geografia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (38, 'En que oceano se encuentra la Gran Barrera de Coral?', 'Geografia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (39, 'Cual es la montana mas alta del mundo?', 'Geografia', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (40, 'Que pais tiene la mayor cantidad de islas?', 'Geografia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (41, 'Que actor interpreto a Jack Sparrow en Piratas del Caribe?', 'Entretenimiento', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (42, 'Quien escribio Harry Potter?', 'Entretenimiento', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (43, 'Cual es la pelicula mas taquillera de la historia?', 'Entretenimiento', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (44, 'En que anno se estreno la primera pelicula de Star Wars?', 'Entretenimiento', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (45, 'Quien es el director de la pelicula "Inception"?', 'Entretenimiento', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (46, 'Que banda lanzo el album "Abbey Road"?', 'Entretenimiento', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (47, 'Quien pinto la Mona Lisa?', 'Entretenimiento', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (48, 'Que serie de television popularizo la frase "Winter is Coming"?', 'Entretenimiento', 'A', 1, 2);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (49, 'Quien gano el Oscar al Mejor Actor en 2020?', 'Entretenimiento', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (50, 'Que deporte practica Roger Federer?', 'Entretenimiento', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (51, 'En que anno se descubrio America?', 'Historia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (52, 'Quien fue el primer emperador de Roma?', 'Historia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (53, 'En que anno comenzo la Segunda Guerra Mundial?', 'Historia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (54, 'Quien fue el lider de la Revolucion Rusa?', 'Historia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (55, 'En que pais se origino el Renacimiento?', 'Historia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (56, 'Cual es el documento que establecio la independencia de EE.UU?', 'Historia', 'A', 0, 1);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (57, 'Quien fue el primer presidente de Estados Unidos?', 'Historia', 'A', 3, 4);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (58, 'En que anno cayo el Muro de Berlin?', 'Historia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (59, 'Que imperio construyo el Coliseo?', 'Historia', 'A', 2, 3);
insert into PLAM_PREGUNTAS (pre_id, pre_contenido, pre_categoria, pre_estado, pre_cantidad_llamadas, pre_version)
values (60, 'Quien fue el primer hombre en el espacio?', 'Historia', 'A', 3, 4);
commit;
prompt 60 records loaded
prompt Loading PLAM_RESPUESTAS...
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (1, 1, 'Una pierna', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (1, 2, 'Un brazo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (1, 3, 'La cabeza', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (1, 4, 'El sombrero', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (2, 5, 'Ninguna', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (2, 6, 'Dos', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (2, 7, 'Cuatro', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (2, 8, 'Tres', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (3, 9, 'Leonardo da Vinci', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (3, 10, 'Pablo Picasso', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (3, 11, 'Vincent van Gogh', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (3, 12, 'Claude Monet', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (4, 13, 'H2O', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (4, 14, 'CO2', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (4, 15, 'H2', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (4, 16, 'O2', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (5, 17, 'Piano', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (5, 18, 'Violin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (5, 19, 'Flauta', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (5, 20, 'Guitarra', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (6, 21, 'Marte', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (6, 22, 'Venus', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (6, 23, 'Jupiter', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (6, 24, 'Saturno', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (7, 25, '1973', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (7, 26, '1965', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (7, 27, '1950', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (7, 28, '1980', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (8, 29, 'Oxigeno', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (8, 30, 'Hidrogeno', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (8, 31, 'Nitrogeno', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (8, 32, 'Helio', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (9, 33, 'Miguel Angel', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (9, 34, 'Donatello', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (9, 35, 'Rafael', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (9, 36, 'Bernini', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (10, 37, 'Albert Einstein', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (10, 38, 'Isaac Newton', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (10, 39, 'Nikola Tesla', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (10, 40, 'Galileo Galilei', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (11, 41, 'William Shakespeare', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (11, 42, 'Charles Dickens', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (11, 43, 'Jane Austen', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (11, 44, 'Mark Twain', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (12, 45, 'Litio', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (12, 46, 'Aluminio', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (12, 47, 'Hierro', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (12, 48, 'Plomo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (13, 49, 'Louvre', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (13, 50, 'Prado', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (13, 51, 'Tate Modern', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (13, 52, 'MOMA', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (14, 53, 'Au', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (14, 54, 'Ag', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (14, 55, 'Fe', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (14, 56, 'Cu', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (15, 57, 'Vincent van Gogh', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (15, 58, 'Paul Gauguin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (15, 59, 'Edvard Munch', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (15, 60, 'Henri Matisse', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (16, 61, 'Corazon', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (16, 62, 'Pulmon', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (16, 63, 'Higado', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (16, 64, 'Rinon', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (17, 65, 'Surrealismo', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (17, 66, 'Impresionismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (17, 67, 'Cubismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (17, 68, 'Realismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (18, 69, 'Jupiter', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (18, 70, 'Saturno', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (18, 71, 'Neptuno', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (18, 72, 'Urano', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (19, 73, 'Marmol', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (19, 74, 'Piedra', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (19, 75, 'Madera', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (19, 76, 'Metal', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (20, 77, 'Estomago', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (20, 78, 'Cerebro', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (20, 79, 'Intestino', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (20, 80, 'Pulmon', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (21, 81, 'Francia', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (21, 82, 'Brasil', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (21, 83, 'Alemania', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (21, 84, 'Argentina', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (22, 85, 'Futbol', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (22, 86, 'Baloncesto', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (22, 87, 'Tenis', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (22, 88, 'Beisbol', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (23, 89, 'Tenis', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (23, 90, 'Futbol', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (23, 91, 'Baloncesto', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (23, 92, 'Golf', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (24, 93, 'Michael Jordan', 'V', 3, 4);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (24, 94, 'LeBron James', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (24, 95, 'Kobe Bryant', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (24, 96, 'Shaquille O''Neal', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (25, 97, 'Estados Unidos', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (25, 98, 'China', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (25, 99, 'Rusia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (25, 100, 'Reino Unido', 'F', 0, 1);
commit;
prompt 100 records committed...
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (26, 101, 'Beisbol', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (26, 102, 'Futbol', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (26, 103, 'Tenis', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (26, 104, 'Natacion', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (27, 105, 'Tenis', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (27, 106, 'Futbol', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (27, 107, 'Baloncesto', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (27, 108, 'Atletismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (28, 109, 'Artes marciales mixtas', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (28, 110, 'Asociacion de Maraton y Atletismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (28, 111, 'Alianza Mundial de Atletismo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (28, 112, 'Alianza de Artes Marciales', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (29, 113, 'Liga de Campeones de la UEFA', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (29, 114, 'Copa America', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (29, 115, 'Copa del Mundo de Clubes', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (29, 116, 'Eurocopa', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (30, 117, 'Once', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (30, 118, 'Doce', 'F', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (30, 119, 'Diez', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (30, 120, 'Nueve', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (31, 121, 'Puntarenas', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (31, 122, 'Heredia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (31, 123, 'Guanacaste', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (31, 124, 'Limon', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (32, 125, 'Bombay', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (32, 126, 'Agra', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (32, 127, 'Chennai', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (32, 128, 'Jaipur', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (33, 129, 'Francia', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (33, 130, 'Alemania', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (33, 131, 'Italia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (33, 132, 'Espana', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (34, 133, 'Tokio', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (34, 134, 'Kioto', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (34, 135, 'Osaka', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (34, 136, 'Nagoya', 'F', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (35, 137, 'America del Sur', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (35, 138, 'Africa', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (35, 139, 'Asia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (35, 140, 'Europa', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (36, 141, 'Rusia', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (36, 142, 'Canada', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (36, 143, 'China', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (36, 144, 'Estados Unidos', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (37, 145, 'Africa', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (37, 146, 'Asia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (37, 147, 'America del Norte', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (37, 148, 'Oceania', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (38, 149, 'Oceano Pacifico', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (38, 150, 'Oceano Atlantico', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (38, 151, 'Oceano Indico', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (38, 152, 'Oceano Artico', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (39, 153, 'Everest', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (39, 154, 'K2', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (39, 155, 'Kangchenjunga', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (39, 156, 'Lhotse', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (40, 157, 'Suecia', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (40, 158, 'Noruega', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (40, 159, 'Filipinas', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (40, 160, 'Indonesia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (41, 161, 'Johnny Depp', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (41, 162, 'Orlando Bloom', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (41, 163, 'Keira Knightley', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (41, 164, 'Geoffrey Rush', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (42, 165, 'J.K. Rowling', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (42, 166, 'Stephen King', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (42, 167, 'George R.R. Martin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (42, 168, 'Suzanne Collins', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (43, 169, 'Avatar', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (43, 170, 'Titanic', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (43, 171, 'Avengers: Endgame', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (43, 172, 'Star Wars: The Force Awakens', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (44, 173, '1977', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (44, 174, '1980', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (44, 175, '1983', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (44, 176, '1974', 'F', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (45, 177, 'Christopher Nolan', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (45, 178, 'Steven Spielberg', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (45, 179, 'Quentin Tarantino', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (45, 180, 'James Cameron', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (46, 181, 'The Beatles', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (46, 182, 'The Rolling Stones', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (46, 183, 'Led Zeppelin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (46, 184, 'Pink Floyd', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (47, 185, 'Leonardo da Vinci', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (47, 186, 'Michelangelo', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (47, 187, 'Raphael', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (47, 188, 'Donatello', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (48, 189, 'Game of Thrones', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (48, 190, 'Breaking Bad', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (48, 191, 'The Walking Dead', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (48, 192, 'Westworld', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (49, 193, 'Joaquin Phoenix', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (49, 194, 'Brad Pitt', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (49, 195, 'Leonardo DiCaprio', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (49, 196, 'Antonio Banderas', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (50, 197, 'Tenis', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (50, 198, 'Futbol', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (50, 199, 'Baloncesto', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (50, 200, 'Golf', 'F', 0, 1);
commit;
prompt 200 records committed...
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (51, 201, '1492', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (51, 202, '1500', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (51, 203, '1482', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (51, 204, '1498', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (52, 205, 'Augusto', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (52, 206, 'Julio Cesar', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (52, 207, 'Neron', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (52, 208, 'Caligula', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (53, 209, '1939', 'V', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (53, 210, '1941', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (53, 211, '1938', 'F', 1, 2);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (53, 212, '1940', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (54, 213, 'Lenin', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (54, 214, 'Stalin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (54, 215, 'Trotsky', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (54, 216, 'Khrushchev', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (55, 217, 'Italia', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (55, 218, 'Francia', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (55, 219, 'Espana', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (55, 220, 'Alemania', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (56, 221, 'Declaracion de Independencia', 'V', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (56, 222, 'Constitucion', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (56, 223, 'Carta Magna', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (56, 224, 'Acta de Derechos', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (57, 225, 'George Washington', 'V', 3, 4);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (57, 226, 'Thomas Jefferson', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (57, 227, 'John Adams', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (57, 228, 'James Madison', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (58, 229, '1989', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (58, 230, '1990', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (58, 231, '1988', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (58, 232, '1991', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (59, 233, 'Imperio Romano', 'V', 2, 3);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (59, 234, 'Imperio Bizantino', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (59, 235, 'Imperio Otomano', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (59, 236, 'Imperio Persa', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (60, 237, 'Yuri Gagarin', 'V', 3, 4);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (60, 238, 'Neil Armstrong', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (60, 239, 'Buzz Aldrin', 'F', 0, 1);
insert into PLAM_RESPUESTAS (res_id_pregunta, res_id, res_contenido, res_tipo, res_cantidad_selecciones, res_version)
values (60, 240, 'Alan Shepard', 'F', 0, 1);
commit;
prompt 240 records loaded
prompt Enabling foreign key constraints for PLAM_RESPUESTAS...
alter table PLAM_RESPUESTAS enable constraint RELACION_PREGUNTAS_A_RESPUESTAS;
prompt Enabling triggers for PLAM_PREGUNTAS...
alter table PLAM_PREGUNTAS enable all triggers;
prompt Enabling triggers for PLAM_RESPUESTAS...
alter table PLAM_RESPUESTAS enable all triggers;

set feedback on
set define on
prompt Done