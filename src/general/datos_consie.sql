-- Cargo
insert into cargo (nombre_cargo) values ('cocinero');
insert into cargo (nombre_cargo) values ('docente');
insert into cargo (nombre_cargo) values ('secretario');
insert into cargo (nombre_cargo) values ('director');
insert into cargo (nombre_cargo) values ('coordinador');
insert into cargo (nombre_cargo) values ('sub-director');
insert into cargo (nombre_cargo) values ('obrero');
insert into cargo (nombre_cargo) values ('docente');
insert into cargo (nombre_cargo) values ('psicólogo');
insert into cargo (nombre_cargo) values ('informático');

-- Estado
insert into estado (nombre_estado) values ('amazonas');
insert into estado (nombre_estado) values ('apure');
insert into estado (nombre_estado) values ('aragua');
insert into estado (nombre_estado) values ('barinas');
insert into estado (nombre_estado) values ('bolívar');
insert into estado (nombre_estado) values ('carabobo');
insert into estado (nombre_estado) values ('caracas');
insert into estado (nombre_estado) values ('delta amacuro');
insert into estado (nombre_estado) values ('falcón');
insert into estado (nombre_estado) values ('guárico');
insert into estado (nombre_estado) values ('lara');
insert into estado (nombre_estado) values ('mérida');
insert into estado (nombre_estado) values ('miranda');
insert into estado (nombre_estado) values ('monagas');
insert into estado (nombre_estado) values ('nueva esparta');
insert into estado (nombre_estado) values ('sucre');
insert into estado (nombre_estado) values ('táchira');
insert into estado (nombre_estado) values ('trujillo');
insert into estado (nombre_estado) values ('vargas');
insert into estado (nombre_estado) values ('yaracuy');
insert into estado (nombre_estado) values ('zulia');

-- Funcion
insert into funcion (funcion) values('cargo');
insert into funcion (funcion) values('escuela');
insert into funcion (funcion) values('estado');
insert into funcion (funcion) values('estudiante');
insert into funcion (funcion) values('funcionario');
insert into funcion (funcion) values('grado');
insert into funcion (funcion) values('iniciar sesión');
insert into funcion (funcion) values('municipio');
insert into funcion (funcion) values('parrouia');
insert into funcion (funcion) values('personal');
insert into funcion (funcion) values('recaudo');
insert into funcion (funcion) values('representante');
insert into funcion (funcion) values('seccion');
insert into funcion (funcion) values('usuario');

-- Municipio
INSERT INTO municipio (nombre_municipio, id_estado) VALUES('san fernando', '2');
INSERT INTO municipio (nombre_municipio, id_estado) VALUES ('maracay', '3');
INSERT INTO municipio (nombre_municipio, id_estado) VALUES('valencia','6');
INSERT INTO municipio (nombre_municipio, id_estado) VALUES ('coro', '9');
INSERT INTO municipio (nombre_municipio, id_estado) VALUES('barquisimeto','11');
INSERT INTO municipio (nombre_municipio, id_estado) VALUES('mérida', '12');
insert into municipio (nombre_municipio, id_estado) values ('independencia', 20);
insert into municipio (nombre_municipio, id_estado) values ('san Felipe', 20);
insert into municipio (nombre_municipio, id_estado) values ('arístides Bastidas', 20);
insert into municipio (nombre_municipio, id_estado) values ('veroes', 20);
insert into municipio (nombre_municipio, id_estado) values ('manuel Monge', 20);
insert into municipio (nombre_municipio, id_estado) values ('bruzual', 20);
insert into municipio (nombre_municipio, id_estado) values ('sucre', 20);
insert into municipio (nombre_municipio, id_estado) values ('maracaibo', 21);
insert into municipio (nombre_municipio, id_estado) values ('peña', 20);

-- Parroquia
insert into parroquia (nombre_parroquia, id_municipio) values ('santa rita', 2);
insert into parroquia (nombre_parroquia, id_municipio) values ('independencia', 7);
insert into parroquia (nombre_parroquia, id_municipio) values ('san Felipe', 7);
insert into parroquia (nombre_parroquia, id_municipio) values ('san Pablo', 9);
insert into parroquia (nombre_parroquia, id_municipio) values ('chivacoa', 12);
INSERT INTO parroquia (nombre_parroquia, id_municipio) VALUES('guama','13');

-- Escuela
INSERT INTO escuela (nombre_escuela, turno_escuela, direccion_escuela, id_parroquia) VALUES ('juan josé de maya', 'ambos', 'avenida cartagena entre avenidas 28 y 29', '2');
insert into escuela (nombre_escuela, turno_escuela, direccion_escuela, id_parroquia) values ('los angeles', 'ambos', 'calle, avenida', 3);
INSERT INTO escuela (nombre_escuela, turno_escuela, direccion_escuela, id_parroquia) VALUES ('luisa de morales', 'ambos', 'calle, dirección', '4');
insert into escuela (nombre_escuela, turno_escuela, direccion_escuela, id_parroquia) values ('"santa maría"', 'ambos', 'calle, avenida', 5);

-- Estudiante
insert into estudiante (ci_estudiante, p_nombre_estudiante, s_nombre_estudiante, p_apellido_estudiante, s_apellido_estudiante, genero_estudiante, f_nacimiento_estudiante, direccion_estudiante, id_escuela) values ('26942316', 'diego', null, 'rodríguez', 'mendoza', 'masculino', '25-08-1999', 'san pablo', '4');

-- Usuario
insert into usuario (usuario, contrasenia) values ('admin', '21232f297a57a5a743894a0e4a801fc3');
insert into usuario (usuario, contrasenia) values ('eggo', '0d46279411daa880c814c1bc449d2cf3');
insert into usuario (usuario, contrasenia) values ('dan', '8598a34e1dd8da2eb944a7f21b074f98');

-- Funcionario
insert into funcionario values ('208455289', 'diego', null, 'rodríguez', 'mendoza', 'm', '1999-08-25', null, '10', '2');

-- Usuario_Funcion
insert into usuario_funcion values ('2', '1');
insert into usuario_funcion values ('2', '2');
insert into usuario_funcion values ('2', '3');
insert into usuario_funcion values ('2', '4');
insert into usuario_funcion values ('2', '5');
insert into usuario_funcion values ('2', '6');
insert into usuario_funcion values ('2', '7');
insert into usuario_funcion values ('2', '8');
insert into usuario_funcion values ('2', '9');
insert into usuario_funcion values ('2', '10');
insert into usuario_funcion values ('2', '11');
insert into usuario_funcion values ('2', '12');
insert into usuario_funcion values ('2', '13');
insert into usuario_funcion values ('2', '14');
