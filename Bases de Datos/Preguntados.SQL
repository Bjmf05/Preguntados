/*
Created: 08/05/2024
Modified: 11/06/2024
Model: Oracle 19c
Database: Oracle 19c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE plam_preguntas_seq01
    INCREMENT BY 1
    START WITH 1 NOMAXVALUE
 MINVALUE 1
 NOCACHE
;

CREATE SEQUENCE plam_respuestas_seq01
    INCREMENT BY 1
    START WITH 1 NOMAXVALUE
 MINVALUE 1
 NOCACHE
;

CREATE SEQUENCE plam_jugadores_seq01
    INCREMENT BY 1
    START WITH 1 NOMAXVALUE
 MINVALUE 1
 NOCACHE
;

CREATE SEQUENCE plam_partidas_seq01
    INCREMENT BY 1
    START WITH 1 NOMAXVALUE
 MINVALUE 1
 NOCACHE
;

CREATE SEQUENCE plam_competidores_seq01
    INCREMENT BY 1
    START WITH 1 NOMAXVALUE
 MINVALUE 1
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table plam_preguntas

CREATE TABLE plam_preguntas
(
    pre_id                Number NOT NULL,
    pre_contenido         Varchar2(150 ) NOT NULL,
    pre_categoria         Varchar2(20 ) NOT NULL,
    pre_estado            Varchar2(1 ) DEFAULT 'A' NOT NULL
        CONSTRAINT plam_preguntas_ck01 CHECK (pre_estado in ('A','I')),
    pre_cantidad_llamadas Number NOT NULL,
    pre_version           Number NOT NULL
)
;

-- Create indexes for table plam_preguntas

CREATE INDEX plam_preguntas_ind01 ON plam_preguntas (pre_id)
;

-- Add keys for table plam_preguntas

ALTER TABLE plam_preguntas
    ADD CONSTRAINT PK_plam_preguntas PRIMARY KEY (pre_id)
;

-- Table and Columns comments section

COMMENT
ON COLUMN plam_preguntas.pre_id IS 'Id de la pregunta'
;
COMMENT
ON COLUMN plam_preguntas.pre_contenido IS 'Contenido de la pregunta'
;
COMMENT
ON COLUMN plam_preguntas.pre_categoria IS 'Categoria de la pregunta'
;
COMMENT
ON COLUMN plam_preguntas.pre_estado IS 'Estado de la pregunta (A:Activa, I:Inactiva)'
;
COMMENT
ON COLUMN plam_preguntas.pre_cantidad_llamadas IS 'Cantidad de veces llamada la pregunta'
;
COMMENT
ON COLUMN plam_preguntas.pre_version IS 'Version de la pregunta'
;

-- Table plam_respuestas

CREATE TABLE plam_respuestas
(
    res_id_pregunta          Number           NOT NULL,
    res_id                   Number           NOT NULL,
    res_contenido            Varchar2(50 ) NOT NULL,
    res_tipo                 Varchar2(1 ) NOT NULL
        CONSTRAINT plam_respuestas_ck01 CHECK (res_tipo in ('V','F')),
    res_cantidad_selecciones Number           NOT NULL,
    res_version              Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table plam_respuestas

CREATE INDEX plam_respuestas_ind01 ON plam_respuestas (res_id)
;

-- Add keys for table plam_respuestas

ALTER TABLE plam_respuestas
    ADD CONSTRAINT PK_plam_respuestas PRIMARY KEY (res_id_pregunta, res_id)
;

-- Table and Columns comments section

COMMENT
ON COLUMN plam_respuestas.res_id_pregunta IS 'Id de la pregunta'
;
COMMENT
ON COLUMN plam_respuestas.res_id IS 'Id de la respuesta'
;
COMMENT
ON COLUMN plam_respuestas.res_contenido IS 'Contenido de la respuesta'
;
COMMENT
ON COLUMN plam_respuestas.res_tipo IS 'Tipo de respuesta (V: Verdadera, F:Falsa)'
;
COMMENT
ON COLUMN plam_respuestas.res_cantidad_selecciones IS 'Cantidad de veces seleccionada'
;
COMMENT
ON COLUMN plam_respuestas.res_version IS 'Version de la respuesta'
;

-- Table plam_jugadores

CREATE TABLE plam_jugadores
(
    jug_id                         Number           NOT NULL,
    jug_nombre                     Varchar2(30 ) NOT NULL,
    jug_partidas_jugadas           Number DEFAULT 0 NOT NULL,
    jug_partidas_ganadas           Number DEFAULT 0 NOT NULL,
    jug_cantidad_preguntas         Number DEFAULT 0 NOT NULL,
    jug_cantidad_aciertos          Number DEFAULT 0 NOT NULL,
    jug_cantidad_a_deporte         Number           NOT NULL,
    jug_cantidad_a_historia        Number DEFAULT 0 NOT NULL,
    jug_cantidad_a_arte            Number DEFAULT 0 NOT NULL,
    jug_cantidad_a_ciencia         Number DEFAULT 0 NOT NULL,
    jug_cantidad_a_geografia       Number DEFAULT 0 NOT NULL,
    jug_cantidad_a_entretenimiento Number DEFAULT 0 NOT NULL,
    jug_version                    Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table plam_jugadores

CREATE INDEX plam_jugadores_ind01 ON plam_jugadores (jug_id)
;

-- Add keys for table plam_jugadores

ALTER TABLE plam_jugadores
    ADD CONSTRAINT PK_plam_jugadores PRIMARY KEY (jug_id)
;

-- Table and Columns comments section

COMMENT
ON COLUMN plam_jugadores.jug_id IS 'Id del jugador'
;
COMMENT
ON COLUMN plam_jugadores.jug_nombre IS 'Nombre del jugador'
;
COMMENT
ON COLUMN plam_jugadores.jug_partidas_jugadas IS 'Cantidad de partidas jugadas'
;
COMMENT
ON COLUMN plam_jugadores.jug_partidas_ganadas IS 'Cantidad de partidas ganadas'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_preguntas IS 'Cantidad de preguntas contestadas.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_aciertos IS 'Cantidad de aciertos en preguntas.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_deporte IS 'Cantidad de aciertos en deportes.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_historia IS 'Cantidad de aciertos en historia.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_arte IS 'Cantidad de aciertos en arte.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_ciencia IS 'Cantidad de aciertos en ciencia.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_geografia IS 'Cantidad de aciertos en geografia.'
;
COMMENT
ON COLUMN plam_jugadores.jug_cantidad_a_entretenimiento IS 'Cantidad de aciertos en entretenimiento.'
;
COMMENT
ON COLUMN plam_jugadores.jug_version IS 'Version del jugador'
;

-- Table plam_partidas

CREATE TABLE plam_partidas
(
    part_id            Number           NOT NULL,
    part_fecha         Date             NOT NULL,
    part_nombre        Varchar2(50 ) NOT NULL,
    part_jugadores     Number           NOT NULL,
    part_dificultad    NVarchar2(30) NOT NULL,
    part_tiempo_limite Varchar2(10 ),
    part_estado        Varchar2(1 ) NOT NULL
        CONSTRAINT plam_partidas_ck1 CHECK (part_estado in ('F','A')),
    part_ronda         Number           NOT NULL,
    part_version       Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table plam_partidas

CREATE INDEX plam_partidas_ind01 ON plam_partidas (part_id)
;

-- Add keys for table plam_partidas

ALTER TABLE plam_partidas
    ADD CONSTRAINT PK_plam_partidas PRIMARY KEY (part_id)
;

-- Table and Columns comments section

COMMENT
ON COLUMN plam_partidas.part_id IS 'Id de las partidas'
;
COMMENT
ON COLUMN plam_partidas.part_fecha IS 'Fecha de la partida'
;
COMMENT
ON COLUMN plam_partidas.part_nombre IS 'Nombre de la partida'
;
COMMENT
ON COLUMN plam_partidas.part_jugadores IS 'Cantidad de jugadores'
;
COMMENT
ON COLUMN plam_partidas.part_dificultad IS 'Dificultad de la partida'
;
COMMENT
ON COLUMN plam_partidas.part_tiempo_limite IS 'Tiempo limite de la partida.(Opcional)'
;
COMMENT
ON COLUMN plam_partidas.part_estado IS 'Estado de la partida(F:Finalizada, A:Activa)'
;
COMMENT
ON COLUMN plam_partidas.part_ronda IS 'Ronda de la partida '
;
COMMENT
ON COLUMN plam_partidas.part_version IS 'Version de la partida'
;

-- Table plam_competidores

CREATE TABLE plam_competidores
(
    com_id_partida       Number           NOT NULL,
    com_id_jugador       Number           NOT NULL,
    com_id               Number           NOT NULL,
    com_numero_jugador   Number           NOT NULL,
    com_posicion_ficha   Number DEFAULT 1 NOT NULL,
    com_ficha            Varchar2(100 ) NOT NULL,
    com_turno            Varchar2(1 ) NOT NULL
        CONSTRAINT plam_competidores_ck1 CHECK (com_turno in ('A','O')),
    com_geografia        Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck2 CHECK (com_geografia in ('A','I')),
    com_arte             Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck3 CHECK (com_arte in ('A','I')),
    com_ciencias         Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck4 CHECK (com_ciencias in ('A','I')),
    com_historia         Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck5 CHECK (com_historia in ('A','I')),
    com_entretenimiento  Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck6 CHECK (com_entretenimiento in ('A','I')),
    com_deporte          Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT plam_competidores_ck7 CHECK (com_deporte in ('A','I')),
    com_comodin_doble    Number           NOT NULL,
    com_comodin_pasar    Number DEFAULT 0 NOT NULL,
    com_comodin_bomba    Number DEFAULT 0 NOT NULL,
    com_comodin_tiro     Number DEFAULT 0 NOT NULL,
    com_ayudas_optenidas Number DEFAULT 0,
    com_version          Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table plam_competidores

CREATE INDEX plam_competidores_ind01 ON plam_competidores (com_id)
;

-- Add keys for table plam_competidores

ALTER TABLE plam_competidores
    ADD CONSTRAINT PK_plam_competidores PRIMARY KEY (com_id_jugador, com_id_partida, com_id)
;

-- Table and Columns comments section

COMMENT
ON COLUMN plam_competidores.com_id_partida IS 'Id de la partida del juego'
;
COMMENT
ON COLUMN plam_competidores.com_id_jugador IS 'Id del jugador'
;
COMMENT
ON COLUMN plam_competidores.com_id IS 'Id del competidor'
;
COMMENT
ON COLUMN plam_competidores.com_numero_jugador IS 'El numero de jugador es una forma de mostrar su posicion en el tablero'
;
COMMENT
ON COLUMN plam_competidores.com_posicion_ficha IS 'Posicion de la ficha del competidor'
;
COMMENT
ON COLUMN plam_competidores.com_ficha IS 'Ficha que esta usando el jugador.'
;
COMMENT
ON COLUMN plam_competidores.com_turno IS 'Turno del jugadro(A:Actual,O:Otro)'
;
COMMENT
ON COLUMN plam_competidores.com_geografia IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_arte IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_ciencias IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_historia IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_entretenimiento IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_deporte IS 'Estado del icono(A:Activo, I:Inactivo)'
;
COMMENT
ON COLUMN plam_competidores.com_comodin_doble IS 'Cantidad de veces a usar el comodin doble oportuinidad.'
;
COMMENT
ON COLUMN plam_competidores.com_comodin_pasar IS 'Cantidad de veces a usar el comodin pasar.'
;
COMMENT
ON COLUMN plam_competidores.com_comodin_bomba IS 'Cantidad de veces a usar el comodin bomba.'
;
COMMENT
ON COLUMN plam_competidores.com_comodin_tiro IS 'Cantidad de veces a usar el comodin tiro extra.'
;
COMMENT
ON COLUMN plam_competidores.com_ayudas_optenidas IS 'Cantidad de ayudas optenidas en partida nivel intermedio'
;
COMMENT
ON COLUMN plam_competidores.com_version IS 'Version del competidor'
;

-- Table plam_preguntas_partidas

CREATE TABLE plam_preguntas_partidas
(
    ext_id_pregunta Number NOT NULL,
    ext_id_partida  Number NOT NULL
)
;

-- Add keys for table plam_preguntas_partidas

ALTER TABLE plam_preguntas_partidas
    ADD CONSTRAINT PK_plam_preguntas_partidas PRIMARY KEY (ext_id_pregunta, ext_id_partida)
;

-- Trigger for sequence plam_preguntas_seq01 for column pre_id in table plam_preguntas ---------
CREATE
OR REPLACE TRIGGER plam_preguntas_tgr01 BEFORE INSERT
ON plam_preguntas FOR EACH ROW
BEGIN
if
:new.pre_id is null or :new.pre_id <=0 then
  :new.pre_id := plam_preguntas_seq01.nextval;
end if;
END;
/
CREATE
OR REPLACE TRIGGER plam_preguntas_tgr02 AFTER
UPDATE OF pre_id
ON plam_preguntas FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR
(-20010,'Cannot update column pre_id in table plam_preguntas as it uses sequence.');
END;
/

-- Trigger for sequence plam_respuestas_seq01 for column res_id in table plam_respuestas ---------
CREATE
OR REPLACE TRIGGER plam_respuestas_tgr01 BEFORE INSERT
ON plam_respuestas FOR EACH ROW
BEGIN
if
:new.res_id is null or :new.res_id <=0 then
  :new.res_id := plam_respuestas_seq01.nextval;
end if;
END;
/
CREATE
OR REPLACE TRIGGER plam_respuestas_tgr02 AFTER
UPDATE OF res_id
ON plam_respuestas FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR
(-20010,'Cannot update column res_id in table plam_respuestas as it uses sequence.');
END;
/

-- Trigger for sequence plam_jugadores_seq01 for column jug_id in table plam_jugadores ---------
CREATE
OR REPLACE TRIGGER plam_jugadores_tgr01 BEFORE INSERT
ON plam_jugadores FOR EACH ROW
BEGIN
if
:new.jug_id is null or :new.jug_id <=0 then
  :new.jug_id := plam_jugadores_seq01.nextval;
end if;
END;
/
CREATE
OR REPLACE TRIGGER plam_jugadores_tgr02 AFTER
UPDATE OF jug_id
ON plam_jugadores FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR
(-20010,'Cannot update column jug_id in table plam_jugadores as it uses sequence.');
END;
/

-- Trigger for sequence plam_partidas_seq01 for column part_id in table plam_partidas ---------
CREATE
OR REPLACE TRIGGER plam_partidas_tgr01 BEFORE INSERT
ON plam_partidas FOR EACH ROW
BEGIN
if
:new.part_id is null or :new.part_id <=0 then
  :new.part_id := plam_partidas_seq01.nextval;
end if;
END;
/
CREATE
OR REPLACE TRIGGER plam_partidas_tgr02 AFTER
UPDATE OF part_id
ON plam_partidas FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR
(-20010,'Cannot update column part_id in table plam_partidas as it uses sequence.');
END;
/

-- Trigger for sequence plam_competidores_seq01 for column com_id in table plam_competidores ---------
CREATE
OR REPLACE TRIGGER plam_competidores_tgr01 BEFORE INSERT
ON plam_competidores FOR EACH ROW
BEGIN
if
:new.com_id is null or :new.com_id <=0 then
  :new.com_id := plam_competidores_seq01.nextval;
end if;
END;
/
CREATE
OR REPLACE TRIGGER plam_competidores_tgr02 AFTER
UPDATE OF com_id
ON plam_competidores FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR
(-20010,'Cannot update column com_id in table plam_competidores as it uses sequence.');
END;
/
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE plam_respuestas
    ADD CONSTRAINT relacion_preguntas_a_respuestas FOREIGN KEY (res_id_pregunta) REFERENCES plam_preguntas (pre_id)
;



ALTER TABLE plam_competidores
    ADD CONSTRAINT relacion_jugadores_a_competidores FOREIGN KEY (com_id_jugador) REFERENCES plam_jugadores (jug_id)
;



ALTER TABLE plam_competidores
    ADD CONSTRAINT relacion_partidas_a_competidores FOREIGN KEY (com_id_partida) REFERENCES plam_partidas (part_id)
;



ALTER TABLE plam_preguntas_partidas
    ADD CONSTRAINT relacion_preguntas_a_partidas FOREIGN KEY (ext_id_pregunta) REFERENCES plam_preguntas (pre_id)
;



ALTER TABLE plam_preguntas_partidas
    ADD CONSTRAINT relacion_partidas_a_preguntas FOREIGN KEY (ext_id_partida) REFERENCES plam_partidas (part_id)
;





