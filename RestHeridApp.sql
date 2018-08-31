--
-- Crear base de datos: `restheridapp`
--
CREATE DATABASE IF NOT EXISTS `restheridapp` DEFAULT CHARACTER SET utf8;

--
-- Damos todos los privilegios al usuario: RESTheridApp 
-- con clave: RESTheridApp sobre BBDD: restheridapp
-- Si el usuario no existe lo crea
--
GRANT ALL ON `restheridapp`.* TO 'RESTheridApp'@'localhost' IDENTIFIED BY 'RESTheridApp';

USE `restheridapp`;
