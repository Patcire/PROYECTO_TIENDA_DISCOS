--datos iniciales de la bbbdd de la tienda de discos
	
	DELETE FROM USUARIOS;
	
	INSERT INTO USUARIOS(DNI, NOMBRE, APELLIDOS, CORREO, CONTRASENIA)                                                            
	VALUES ('12345678A','PEPE', 'DE PRUEBA', 'EJEMPLO@HOTMAIL.COM', '1234');
	
	INSERT INTO USUARIOS(DNI, NOMBRE, APELLIDOS, CORREO, CONTRASENIA) 
	VALUES ('12345678B','sean', 'booth', 'sean@gmail.com', 'pestillo');
	
	INSERT INTO USUARIOS(DNI, NOMBRE, APELLIDOS, CORREO, CONTRASENIA) 
	VALUES ('12345678C','a', 'a', 'a', 'a');
	
	COMMIT;
	
	--UPDATE USUARIOS SET CORREO='sean@gmail.com		';
	
	