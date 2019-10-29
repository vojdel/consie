-- CREATE DATABASE consie;

-- DROP DATABASE consie;

create table cargo (
	id_cargo serial not null primary key,
	nombre_cargo varchar(20) not null
);

CREATE TABLE IF NOT EXISTS estado (
  id_estado int NOT NULL,
  estado varchar(250) NOT NULL,
  iso_3166_2 varchar(4) NOT NULL,
  PRIMARY KEY (id_estado)
);

/*
CREATE TABLE IF NOT EXISTS estado (
  id_estado serial NOT NULL primary key,
  estado varchar(250) NOT NULL
);
*/

create table grado (
	id_grado serial not null primary key,
	numero int not null
);

create table indicador (
	id_indicador serial not null primary key,
	nombre_indicador varchar(100) not null,
	prioridad_indicador int not null
);

CREATE TABLE IF NOT EXISTS municipio (
  id_municipio int NOT NULL PRIMARY KEY,
  id_estado int NOT NULL,
  municipio varchar(100) NOT NULL,
  FOREIGN KEY (id_estado) REFERENCES estado (id_estado)
  ON DELETE CASCADE ON UPDATE CASCADE
);

/*
CREATE TABLE IF NOT EXISTS municipio (
  id_municipio serial NOT NULL primary key,
  id_estado int NOT NULL,
  municipio varchar(100) NOT NULL,
  foreign KEY (id_estado) references estado (id_estado)
  ON DELETE CASCADE ON UPDATE CASCADE);
*/

CREATE TABLE IF NOT EXISTS parroquia (
  id_parroquia int NOT NULL PRIMARY KEY,
  id_municipio int NOT NULL,
  parroquia varchar(250) NOT NULL,
  FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio)
  ON DELETE CASCADE ON UPDATE CASCADE
);

/*
CREATE TABLE IF NOT EXISTS parroquia (
  id_parroquia serial NOT NULL primary key,
  id_municipio int NOT NULL,
  parroquia varchar(250) NOT NULL,
  foreign KEY (id_municipio) references municipio (id_municipio)
  ON DELETE CASCADE ON UPDATE CASCADE);
 */

create table escuela (
	id_escuela serial not null primary key,
	nombre_escuela varchar(50) not null,
	turno_escuela varchar(15) not null,
	direccion_escuela text not null,
	id_parroquia int not null,
	foreign key (id_parroquia) references parroquia (id_parroquia)
);

create table estudiante (
	ci_estudiante varchar(10) not null primary key,
	p_nombre_estudiante varchar(20) not null,
	s_nombre_estudiante varchar(20) default '',
	p_apellido_estudiante varchar(20) not null,
	s_apellido_estudiante varchar(20) default '',
	genero_estudiante varchar(10) not null,
	f_nacimiento_estudiante date,
	direccion_estudiante text not null,
	id_escuela int not null,
	foreign key (id_escuela) references escuela (id_escuela)
);

create table personal (
	ci_personal varchar(10) not null primary key,
	p_nombre_personal varchar(20) not null,
	s_nombre_personal varchar(20),
	p_apellido_personal varchar(20) not null,
	s_apellido_personal varchar(20),
	genero_personal varchar(10) not null,
	id_cargo int not null,
        direccion_personal varchar(200),
        telf_personal varchar(12),
	foreign key (id_cargo) references cargo (id_cargo)
);

create table escuela_personal (
        id_escuela_personal serial NOT NULL,
	id_escuela int not null,
	ci_personal varchar(10) not null,
        CONSTRAINT pk_ep PRIMARY KEY (id_escuela_personal),
	foreign key (id_escuela) references escuela (id_escuela),
	foreign key (ci_personal) references personal (ci_personal)
);

create table recaudo (
	id_recaudo serial not null primary key,
	nombre_recaudo varchar(200) not null,
	num_frecuencia int,
	frecuencia_entrega varchar(10) not null
);

create table escuela_recaudo (
        id_escuela_recaudo serial NOT NULL,
	id_escuela int not null,
	id_recaudo int not null,
        CONSTRAINT pk_ider PRIMARY KEY (id_escuela_recaudo),
	foreign key (id_escuela) references escuela (id_escuela),
	foreign key (id_recaudo) references recaudo (id_recaudo)
);

CREATE TABLE entrega_recaudo(
        id_entrega_recaudo serial NOT NULL,
        id_escuela_recuado integer,
        fecha_entrega date,
        CONSTRAINT pk_ers PRIMARY KEY (id_entrega_recaudo),
        CONSTRAINT fk_ers FOREIGN KEY (id_escuela_recuado)
        REFERENCES escuela_recaudo (id_escuela_recaudo) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table representante (
	ci_representante varchar(10) not null primary key,
	p_nombre_representante varchar(20) not null,
	s_nombre_representante varchar(20),
	p_apellido_representante varchar(20) not null,
	s_apellido_representante varchar(20),
	genero_representante varchar(10) not null,
        direccion_personal varchar(200),
        telf_personal varchar(12)
);

create table estudiante_representante (
	ci_estudiante varchar(10) not null,
	ci_representante varchar(10) not null,
	foreign key (ci_estudiante) references estudiante (ci_estudiante),
	foreign key (ci_representante) references representante (ci_representante)
);

create table usuario (
	id_usuario serial not null primary key,
	usuario varchar(15) not null,
	contrasenia varchar(32) not null,
	p_secreta int,
	respuesta varchar(32)
);

create table funcion (
	id_funcion serial not null primary key,
	funcion varchar(40) not null
);

create table funcionario (
	ci_funcionario varchar(10) not null primary key,
	p_nombre_fun varchar(20) not null,
	s_nombre_fun varchar(20),
	p_apellido_fun varchar(20) not null,
	s_apellido_fun varchar(20),
	genero character(1) not null,
	f_nacimiento date,
	telefono varchar(15),
	direccion varchar(200),
	id_cargo int not null,
	id_usuario int,
	foreign key (id_cargo) references cargo (id_cargo),
	foreign key (id_usuario) references usuario (id_usuario)
);

create table usuario_funcion (
	id_usuario int not null,
	id_funcion int not null,
	consultar boolean,
	agregar boolean,
	modificar boolean,
	eliminar boolean,
	foreign key (id_usuario) references usuario (id_usuario),
	foreign key (id_funcion) references funcion (id_funcion)
);

create table seccion (
	id_seccion serial not null primary key,
	descripccion varchar(20) not null
);

CREATE TABLE estudiante_seccion (
  id_estudiante varchar(32)  NOT NULL,
  id_seccion int4 NOT NULL,
  CONSTRAINT fk_est3 FOREIGN KEY (id_estudiante) REFERENCES estudiante (ci_estudiante) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_sec3 FOREIGN KEY (id_seccion) REFERENCES seccion (id_seccion) ON DELETE NO ACTION ON UPDATE NO ACTION
)
;

/*CREATE TABLE estudiante_anio_escolar (
  id_estudiante varchar(32) NOT NULL,
  id_anio_escolar int4 NOT NULL,
  CONSTRAINT fk_anio1 FOREIGN KEY (id_anio_escolar) REFERENCES anio_escolar (id_anio_escolar) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_est2 FOREIGN KEY (id_estudiante) REFERENCES estudiante (ci_estudiante) ON DELETE NO ACTION ON UPDATE NO ACTION
)*/
;

/*CREATE TABLE estudiante_anio_escolar (
  id_estudiante int4 NOT NULL,
  id_anio_escolar int4 NOT NULL,
  CONSTRAINT fk_anio1 FOREIGN KEY (id_anio_escolar) REFERENCES anio_escolar (id_anio_escolar) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_est2 FOREIGN KEY (id_estudiante) REFERENCES estado (id_estado) ON DELETE NO ACTION ON UPDATE NO ACTION
)*/
;

-- bitacora
create table if not exists bitacora (
	id_bitacora serial not null primary key,
	operacion text not null,
	tabla text not null,
	id_usuario int,
	valor_viejo text,
	valor_nuevo text,
	fecha_hora timestamp without time zone default localtimestamp(0)
);


-- Crear Vistas
-- Cargo
create or replace view vista_cargo as
	select
		id_cargo,
		nombre_cargo
	from cargo;
	
-- Estado
create or replace view vista_estado as
	SELECT 
		id_estado, 
		estado
	FROM estado;

-- Municipio
create or replace view vista_municipio as
	SELECT 
		m.id_municipio, 
		m.municipio,
		e.id_estado,
		e.estado
	FROM municipio m
	inner join vista_estado e on m.id_estado = e.id_estado;

-- Parroquia
create or replace view vista_parroquia as
	SELECT 
		p.id_parroquia, 
		p.parroquia,
		m.id_municipio,
		m.municipio,
		e.id_estado,
		e.estado
	FROM parroquia p
	inner join municipio m on p.id_municipio = m.id_municipio
	inner join estado e on m.id_estado = e.id_estado;

-- Escuela
create or replace view vista_escuela as
	SELECT 
		esc.id_escuela, 
		esc.nombre_escuela, 
		esc.turno_escuela, 
		esc.direccion_escuela, 
		p.id_parroquia,
		p.parroquia,
		m.id_municipio,
		m.municipio,
		est.id_estado,
		est.estado
	FROM escuela esc
	inner join vista_parroquia p on esc.id_parroquia = p.id_parroquia
	inner join vista_municipio m on p.id_municipio = m.id_municipio
	inner join vista_estado est on m.id_estado = est.id_estado;

-- Estudiante
create or replace view vista_estudiante as 
	SELECT 
		estu.ci_estudiante, 
		estu.p_nombre_estudiante, 
		estu.s_nombre_estudiante, 
		estu.p_apellido_estudiante, 
		estu.s_apellido_estudiante, 
		estu.genero_estudiante, 
		estu.f_nacimiento_estudiante, 
		estu.direccion_estudiante, 
		esc.id_escuela,
		esc.nombre_escuela,
		esc.turno_escuela,
		esc.direccion_escuela,
		p.id_parroquia,
		p.parroquia,
		m.id_municipio,
		m.municipio,
		est.id_estado,
		est.estado
	FROM estudiante estu
	inner join vista_escuela esc on estu.id_escuela = esc.id_escuela
	inner join vista_parroquia p on esc.id_parroquia = p.id_parroquia
	inner join vista_municipio m on p.id_municipio = m.id_municipio
	inner join vista_estado est on m.id_estado = est.id_estado;

-- Funcion
create or replace view vista_funcion as
	SELECT 
		id_funcion, 
		funcion
	FROM funcion;

create or replace view vista_funcionario as
	SELECT 
		f.ci_funcionario, 
		f.p_nombre_fun, 
		f.s_nombre_fun, 
		f.p_apellido_fun, 
		f.s_apellido_fun, 
		f.genero, 
		f.f_nacimiento, 
		f.telefono, 
		f.direccion, 
		c.id_cargo, 
		c.nombre_cargo,
		f.id_usuario
	FROM funcionario f
	inner join cargo c on f.id_cargo = c.id_cargo;


---------- Crear Funciones ----------
---------- Otras ----------
-- Calcular edad
/*create or replace function calcular_edad(f_nacimiento date) returns int as $calcular_edad$
begin
	declare edad date;
	edad := to_char(current_date() - f_nacimiento);
	extract
	returns edad;
end;
$calcular_edad$ language plpgsql;*/

CREATE OR REPLACE FUNCTION minusculas_cargo() RETURNS trigger AS $minusculas_cargo$
BEGIN
	NEW.nombre_cargo = lower(NEW.nombre_cargo);
	RETURN NEW;
END;
$minusculas_cargo$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_escuela() RETURNS trigger AS $minusculas_escuela$
BEGIN
	NEW.nombre_escuela = lower(NEW.nombre_escuela);
	new.turno_escuela = lower(new.turno_escuela);
	new.direccion_escuela = lower(new.direccion_escuela);
	RETURN NEW;
END;
$minusculas_escuela$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_estado() RETURNS trigger AS $minusculas_estado$
BEGIN
	NEW.estado = lower(NEW.estado);
	RETURN NEW;
END;
$minusculas_estado$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_estudiante() RETURNS trigger AS $minusculas_estudiante$
BEGIN
	NEW.p_nombre_estudiante = lower(NEW.p_nombre_estudiante);
	NEW.s_nombre_estudiante = lower(NEW.s_nombre_estudiante);
	NEW.p_apellido_estudiante = lower(NEW.p_apellido_estudiante);
	NEW.s_apellido_estudiante = lower(NEW.s_apellido_estudiante);
	NEW.genero_estudiante = lower(NEW.genero_estudiante);
	NEW.direccion_estudiante = lower(NEW.direccion_estudiante);
	RETURN NEW;
END;
$minusculas_estudiante$ LANGUAGE plpgsql;

--Bitácora
create or replace function llenar_bitacora() returns trigger as $llenar_bitacora$
begin
	if TG_OP = 'INSERT' then
		insert into bitacora (operacion, tabla, valor_nuevo)
		values (TG_OP, TG_TABLE_NAME, new);
		end if;
	if TG_OP = 'UPDATE' then
		insert into bitacora (operacion, tabla, valor_viejo, valor_nuevo)
		values (TG_OP, TG_TABLE_NAME, old, new);
		end if;
	if TG_OP = 'DELETE' then
		insert into bitacora (operacion, tabla, valor_viejo)
		values (TG_OP, TG_TABLE_NAME, old);
	end if;
	return new;
end;
$llenar_bitacora$ language plpgsql;


---------- Crear Funciones Trigger ----------
CREATE TRIGGER tg_minusculas_cargo
BEFORE INSERT OR UPDATE
ON cargo
FOR EACH ROW
EXECUTE PROCEDURE minusculas_cargo();

CREATE TRIGGER tg_minusculas_escuela
BEFORE INSERT OR UPDATE
ON escuela
FOR EACH ROW
EXECUTE PROCEDURE minusculas_escuela();

CREATE TRIGGER tg_minusculas_estado
BEFORE INSERT OR UPDATE
ON estado
FOR EACH ROW
EXECUTE PROCEDURE minusculas_estado();

CREATE TRIGGER tg_minusculas_estudiante
BEFORE INSERT OR UPDATE
ON estudiante
FOR EACH ROW
EXECUTE PROCEDURE minusculas_estudiante();

create trigger tg_bitacora
before insert or update or delete
on cargo
for each row
execute procedure llenar_bitacora();
-- bitacora
create table if not exists bitacora (
	id_bitacora serial not null primary key,
	operacion text not null,
	tabla text not null,
	id_usuario int,
	valor_viejo text,
	valor_nuevo text,
	fecha_hora timestamp without time zone default localtimestamp(0)
);


-- Crear Vistas
-- Cargo
create or replace view vista_cargo as
	select
		id_cargo,
		nombre_cargo
	from cargo;
	
-- Estado
create or replace view vista_estado as
	SELECT 
		id_estado, 
		estado
	FROM estado;

-- Municipio
create or replace view vista_municipio as
	SELECT 
		m.id_municipio, 
		m.municipio,
		e.id_estado,
		e.estado
	FROM municipio m
	inner join vista_estado e on m.id_estado = e.id_estado;

-- Parroquia
create or replace view vista_parroquia as
	SELECT 
		p.id_parroquia, 
		p.parroquia,
		m.id_municipio,
		m.municipio,
		e.id_estado,
		e.estado
	FROM parroquia p
	inner join municipio m on p.id_municipio = m.id_municipio
	inner join estado e on m.id_estado = e.id_estado;

-- Escuela
create or replace view vista_escuela as
	SELECT 
		esc.id_escuela, 
		esc.nombre_escuela, 
		esc.turno_escuela, 
		esc.direccion_escuela, 
		p.id_parroquia,
		p.parroquia,
		m.id_municipio,
		m.municipio,
		est.id_estado,
		est.estado
	FROM escuela esc
	inner join vista_parroquia p on esc.id_parroquia = p.id_parroquia
	inner join vista_municipio m on p.id_municipio = m.id_municipio
	inner join vista_estado est on m.id_estado = est.id_estado;

-- Estudiante
create or replace view vista_estudiante as 
	SELECT 
		estu.ci_estudiante, 
		estu.p_nombre_estudiante, 
		estu.s_nombre_estudiante, 
		estu.p_apellido_estudiante, 
		estu.s_apellido_estudiante, 
		estu.genero_estudiante, 
		estu.f_nacimiento_estudiante, 
		estu.direccion_estudiante, 
		esc.id_escuela,
		esc.nombre_escuela,
		esc.turno_escuela,
		esc.direccion_escuela,
		p.id_parroquia,
		p.parroquia,
		m.id_municipio,
		m.municipio,
		est.id_estado,
		est.estado
	FROM estudiante estu
	inner join vista_escuela esc on estu.id_escuela = esc.id_escuela
	inner join vista_parroquia p on esc.id_parroquia = p.id_parroquia
	inner join vista_municipio m on p.id_municipio = m.id_municipio
	inner join vista_estado est on m.id_estado = est.id_estado;

-- Funcion
create or replace view vista_funcion as
	SELECT 
		id_funcion, 
		funcion
	FROM funcion;

create or replace view vista_funcionario as
	SELECT 
		f.ci_funcionario, 
		f.p_nombre_fun, 
		f.s_nombre_fun, 
		f.p_apellido_fun, 
		f.s_apellido_fun, 
		f.genero, 
		f.f_nacimiento, 
		f.telefono, 
		f.direccion, 
		c.id_cargo, 
		c.nombre_cargo,
		f.id_usuario
	FROM funcionario f
	inner join cargo c on f.id_cargo = c.id_cargo;


---------- Crear Funciones ----------
---------- Otras ----------
-- Calcular edad
/*create or replace function calcular_edad(f_nacimiento date) returns int as $calcular_edad$
begin
	declare edad date;
	edad := to_char(current_date() - f_nacimiento);
	extract
	returns edad;
end;
$calcular_edad$ language plpgsql;*/

CREATE OR REPLACE FUNCTION minusculas_cargo() RETURNS trigger AS $minusculas_cargo$
BEGIN
	NEW.nombre_cargo = lower(NEW.nombre_cargo);
	RETURN NEW;
END;
$minusculas_cargo$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_escuela() RETURNS trigger AS $minusculas_escuela$
BEGIN
	NEW.nombre_escuela = lower(NEW.nombre_escuela);
	new.turno_escuela = lower(new.turno_escuela);
	new.direccion_escuela = lower(new.direccion_escuela);
	RETURN NEW;
END;
$minusculas_escuela$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_estado() RETURNS trigger AS $minusculas_estado$
BEGIN
	NEW.estado = lower(NEW.estado);
	RETURN NEW;
END;
$minusculas_estado$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION minusculas_estudiante() RETURNS trigger AS $minusculas_estudiante$
BEGIN
	NEW.p_nombre_estudiante = lower(NEW.p_nombre_estudiante);
	NEW.s_nombre_estudiante = lower(NEW.s_nombre_estudiante);
	NEW.p_apellido_estudiante = lower(NEW.p_apellido_estudiante);
	NEW.s_apellido_estudiante = lower(NEW.s_apellido_estudiante);
	NEW.genero_estudiante = lower(NEW.genero_estudiante);
	NEW.direccion_estudiante = lower(NEW.direccion_estudiante);
	RETURN NEW;
END;
$minusculas_estudiante$ LANGUAGE plpgsql;

--Bitácora
create or replace function llenar_bitacora() returns trigger as $llenar_bitacora$
begin
	if TG_OP = 'INSERT' then
		insert into bitacora (operacion, tabla, valor_nuevo)
		values (TG_OP, TG_TABLE_NAME, new);
		end if;
	if TG_OP = 'UPDATE' then
		insert into bitacora (operacion, tabla, valor_viejo, valor_nuevo)
		values (TG_OP, TG_TABLE_NAME, old, new);
		end if;
	if TG_OP = 'DELETE' then
		insert into bitacora (operacion, tabla, valor_viejo)
		values (TG_OP, TG_TABLE_NAME, old);
	end if;
	return new;
end;
$llenar_bitacora$ language plpgsql;


---------- Crear Funciones Trigger ----------
CREATE TRIGGER tg_minusculas_cargo
BEFORE INSERT OR UPDATE
ON cargo
FOR EACH ROW
EXECUTE PROCEDURE minusculas_cargo();

CREATE TRIGGER tg_minusculas_escuela
BEFORE INSERT OR UPDATE
ON escuela
FOR EACH ROW
EXECUTE PROCEDURE minusculas_escuela();

CREATE TRIGGER tg_minusculas_estado
BEFORE INSERT OR UPDATE
ON estado
FOR EACH ROW
EXECUTE PROCEDURE minusculas_estado();

CREATE TRIGGER tg_minusculas_estudiante
BEFORE INSERT OR UPDATE
ON estudiante
FOR EACH ROW
EXECUTE PROCEDURE minusculas_estudiante();

create trigger tg_bitacora
before insert or update or delete
on cargo
for each row
execute procedure llenar_bitacora();
