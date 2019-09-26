-- CREATE DATABASE consie;

-- DROP DATABASE consie;

create table cargo (
	id_cargo serial not null primary key,
	nombre_cargo varchar(20) not null
);

create table estado (
	id_estado serial not null primary key,
	nombre_estado varchar(20) not null
);

create table grado (
	id_grado serial not null primary key,
	numero int not null
);

create table municipio (
	id_municipio serial not null primary key,
	nombre_municipio varchar(20) not null,
	id_estado int not null,
	foreign key (id_estado) references estado (id_estado)
);

create table parroquia (
	id_parroquia serial not null primary key,
	nombre_parroquia varchar(20) not null,
	id_municipio int not null,
	foreign key (id_municipio) references municipio (id_municipio)
);

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
        direccion_pesonal varchar(200) not null,
        telf_personal varchar(12) not null,
	foreign key (id_cargo) references cargo (id_cargo)
);

create table escuela_personal (
	id_escuela int not null,
	ci_personal varchar(10) not null,
	foreign key (id_escuela) references escuela (id_escuela),
	foreign key (ci_personal) references personal (ci_personal)
);

create table recaudo (
	id_recaudo serial not null primary key,
	nombre_recaudo varchar(20) not null,
	frecuencia_entrega varchar(10) not null
);

create table escuela_recaudo (
	id_escuela int not null,
	id_recaudo int not null,
	foreign key (id_escuela) references escuela (id_escuela),
	foreign key (id_recaudo) references recaudo (id_recaudo)
);

create table representante (
	ci_representante varchar(10) not null primary key,
	p_nombre_representante varchar(20) not null,
	s_nombre_representante varchar(20),
	p_apellido_representante varchar(20) not null,
	s_apellido_representante varchar(20),
	genero_representante varchar(10) not null
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
	p_secreta varchar(1),
	respuesta varchar(20)
);

create table funcion (
	id_funcion serial not null primary key,
	funcion varchar(40) not null
);

create table funcionario (
	ci_funcionario int not null primary key,
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
