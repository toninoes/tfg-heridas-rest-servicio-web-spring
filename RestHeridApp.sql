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

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Tabla `activacion_usuario`
--

DROP TABLE IF EXISTS `activacion_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activacion_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_expiracion` datetime(6) DEFAULT NULL,
  `token_activacion` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfqeuf7ho4r98sq9v1pybsie7s` (`user_id`),
  CONSTRAINT `FKfqeuf7ho4r98sq9v1pybsie7s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES
	(1,'ROLE_ADMIN'),
	(2,'ROLE_SANITARIO'),
	(3,'ROLE_PACIENTE');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `centros`
--

DROP TABLE IF EXISTS `centros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centros` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ku0kqrmq5sm03j7tvqqa1f8am` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `centros`
--

LOCK TABLES `centros` WRITE;
/*!40000 ALTER TABLE `centros` DISABLE KEYS */;
INSERT INTO `centros` VALUES
	(1,'c/ Lope de Vega, 12. 11008 Cádiz','C.S. El Olivillo','956123456'),
	(2,'c/ Avenida de la Bahía, s/n. 11007 Cádiz','C.S. Loreto-Puntales','956121212'),
	(3,'Avda. Cayetano del Toro, 4. 11100 San Fernando (Cádiz)','C.S. La Ardila','951623458'),
	(4,'c/ Doctor Cellier, s/n. 11100 San Fernando (Cádiz)','C.S. Rodriguez Arias','951741456');
/*!40000 ALTER TABLE `centros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `orden` bigint(20) NOT NULL,
  `paciente_id` bigint(20) NOT NULL,
  `sala_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj3l4qvkar6txwqtkbh1rlq9j5` (`paciente_id`),
  KEY `FKcb601734ph6w94d3k1xjexfod` (`sala_id`),
  CONSTRAINT `FKcb601734ph6w94d3k1xjexfod` FOREIGN KEY (`sala_id`) REFERENCES `salas` (`id`),
  CONSTRAINT `FKj3l4qvkar6txwqtkbh1rlq9j5` FOREIGN KEY (`paciente_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `cuidados`
--

DROP TABLE IF EXISTS `cuidados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuidados` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creacion` datetime(6) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `grupodiagnostico_id` bigint(20) NOT NULL,
  `sanitario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrqwx3rjbfhb92hjqrnfj018jl` (`grupodiagnostico_id`),
  KEY `FKjvpq6fbuisbib67p12ahlqu29` (`sanitario_id`),
  CONSTRAINT `FKjvpq6fbuisbib67p12ahlqu29` FOREIGN KEY (`sanitario_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrqwx3rjbfhb92hjqrnfj018jl` FOREIGN KEY (`grupodiagnostico_id`) REFERENCES `grupodiagnostico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `curas`
--

DROP TABLE IF EXISTS `curas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actualizacion` datetime(6) NOT NULL,
  `creacion` datetime(6) NOT NULL,
  `evolucion` varchar(255) DEFAULT NULL,
  `recomendaciones` varchar(255) DEFAULT NULL,
  `tratamiento` varchar(255) DEFAULT NULL,
  `valorada` bit(1) DEFAULT NULL,
  `proceso_id` bigint(20) NOT NULL,
  `sanitario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjjd359ksv40ukephggalfd2gf` (`proceso_id`),
  KEY `FKbujjwojrn14ygl5omiqd5tv9l` (`sanitario_id`),
  CONSTRAINT `FKbujjwojrn14ygl5omiqd5tv9l` FOREIGN KEY (`sanitario_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjjd359ksv40ukephggalfd2gf` FOREIGN KEY (`proceso_id`) REFERENCES `procesos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `diagnosticos`
--

DROP TABLE IF EXISTS `diagnosticos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosticos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `grupo_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tk0c26fjmi4jt50225rnk7wij` (`nombre`),
  KEY `FKpvn2q3wqhtc6pid95otpenmyl` (`grupo_id`),
  CONSTRAINT `FKpvn2q3wqhtc6pid95otpenmyl` FOREIGN KEY (`grupo_id`) REFERENCES `grupodiagnostico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `diagnosticos`
--

LOCK TABLES `diagnosticos` WRITE;
/*!40000 ALTER TABLE `diagnosticos` DISABLE KEYS */;
INSERT INTO `diagnosticos` VALUES
	(1,'L02.1','Absceso cutáneo',1),
	(2,'L02.2','Forúnculo',1),
	(3,'L02.3','Antrax',1),
	(4,'L05','Quiste pilonidal con absceso',1),
	(5,'L08','Otras infecciones locales de la piel y tejido subcutáneo',1),
	(6,'T14.1','Heridas cutáneas',2),
	(7,'T14.2','Cuerpos extraños',2),
	(8,'T14.3','Traumatismos ungueales',3),
	(9,'L60.1','Uña encarnada',3),
	(10,'L60.2','Onicodistrofias',3),
	(11,'L60.3','Onicomicosis',3),
	(12,'L60.4','Otros trastornos de las uñas',3),
	(13,'L72.1','Quiste epidérmico',4),
	(14,'L72.2','Quiste tricodérmico',4),
	(15,'L72.3','Otros quistes foliculares de la piel y del tejido subcutáneo',4),
	(16,'L73.1','Foliculitis',5),
	(17,'L73.2','Hidradenitis supurativa',5),
	(18,'L73.3','Otros trastornos foliculares no especificados',5),
	(19,'B07','Verrugas viricas',6),
	(20,'B08.1','Molusco contagioso',6),
	(21,'B08.2','Condilomas acuminados',6),
	(22,'B09','Otras infecciones víricas de la piel',6),
	(23,'D17.1','Lipoma',7),
	(24,'D17.2','Fibrolipoma',7),
	(25,'D18','Hemangiomas',7),
	(26,'D22','Nevo melanocitico',7),
	(27,'D23.1','Fibromas',7),
	(28,'D23.2','Dermatofibromas',7),
	(29,'D23.3','Neurofibromas',7),
	(30,'D23.4','Otros tumores benignos no especificados',7),
	(31,'L82','Queratosis seborreicas',8),
	(32,'L84','Callos y callosidades',8),
	(33,'L90','Fibrosis y afecciones cicatriciales de la piel',8),
	(34,'L91','Cicatriz queloide',8),
	(35,'L97','Úlcera de miembro inferior',8),
	(36,'L98.1','Granuloma piogeno',8),
	(37,'L98.2','Otros trastornos de la piel y del tejido subcutáneo',8);
/*!40000 ALTER TABLE `diagnosticos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `grupodiagnostico`
--

DROP TABLE IF EXISTS `grupodiagnostico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupodiagnostico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_34hjog9fxct8nnj8rhpl2dann` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `grupodiagnostico`
--

LOCK TABLES `grupodiagnostico` WRITE;
/*!40000 ALTER TABLE `grupodiagnostico` DISABLE KEYS */;
INSERT INTO `grupodiagnostico` VALUES
	(1,'Infecciones locales de la piel y del tejido subcutáneo'),
	(2,'Traumatismos cutáneos'),
	(3,'Trastornos/traumatismos de las uñas'),
	(4,'Quistes foliculares de la piel y del tejido subcutáneo'),
	(5,'Otros trastornos foliculares'),
	(6,'Infecciones virales caracterizadas por lesiones de la piel y de las membranas mucosas'),
	(7,'Tumores benignos de la piel'),
	(8,'Otras enfermedades de la piel');
/*!40000 ALTER TABLE `grupodiagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagenes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creacion` datetime(6) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `cura_id` bigint(20) NOT NULL,
  `actualizacion` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKldst25mumnb1x7bdoj0f1929w` (`cura_id`),
  CONSTRAINT `FKldst25mumnb1x7bdoj0f1929w` FOREIGN KEY (`cura_id`) REFERENCES `curas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `id` bigint(20) NOT NULL,
  `actualizacion` datetime(6) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `creacion` datetime(6) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nacimiento` datetime(6) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hol387x0ourgruynyqewdhv37` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `procedimientos`
--

DROP TABLE IF EXISTS `procedimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedimientos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n5pwok4pbet3h3o5vvjf4nx2g` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `procedimientos`
--

LOCK TABLES `procedimientos` WRITE;
/*!40000 ALTER TABLE `procedimientos` DISABLE KEYS */;
INSERT INTO `procedimientos` VALUES
	(1,'86.01','Aspiración de piel y tejido subcutáneo'),
	(2,'86.04','Incisión con drenaje de piel y tejido subcutáneo'),
	(3,'86.05','Incisión con extracción de cuerpo extraño de piel y tejido subcutáneo'),
	(4,'86.11','Biopsia de piel y tejido subcutáneo'),
	(5,'86.2.0','Exéresis de la lesión'),
	(6,'86.2.1','Escisión o destrucción de lesión'),
	(7,'86.21','Extirpación de quiste o seno pilonidal'),
	(8,'86.22','Desbridamiento escisional de herida, infección o quemadura'),
	(9,'86.23','Extracción de uña, lecho de uña o pliegue de uña'),
	(10,'86.24','Quimiocirugía de piel'),
	(11,'86.25','Abrasión dérmica'),
	(12,'86.27','Desbridamiento de uña, base de uña o pliegue de uña'),
	(13,'86.5','Sutura u otro cierre de piel y tejido subcutáneo'),
	(14,'91.62','E.M. Muestra de piel y otro tegumento cultivo'),
	(15,'92.01','Desbridamiento cortante'),
	(16,'92.02','Desbridamiento quirúrgico'),
	(17,'92.03','Escisión fusiforme'),
	(18,'92.04','Escisión tangencial'),
	(19,'92.05','Drenaje de hematoma subungueal'),
	(20,'92.06','Reimplantación de lámina ungueal'),
	(21,'92.07','Matricectomía mecánica (parcial/total)'),
	(22,'92.08','Matricectomía química (parcial/total)'),
	(23,'92.09','Matricectomía eléctrica (parcial/total)'),
	(24,'92.10','Avulsión química de uña'),
	(25,'98.2','Extracción sin incisión de otro cuerpo extraño'),
	(26,'100','Otros procedimientos');
/*!40000 ALTER TABLE `procedimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `procesos`
--

DROP TABLE IF EXISTS `procesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procesos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actualizacion` datetime(6) NOT NULL,
  `anamnesis` varchar(255) DEFAULT NULL,
  `creacion` datetime(6) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `diagnostico_id` bigint(20) NOT NULL,
  `paciente_id` bigint(20) NOT NULL,
  `procedimiento_id` bigint(20) NOT NULL,
  `diagnostico` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk7hof5qavalmmpq2l28pceiq0` (`diagnostico_id`),
  KEY `FKih6c6mkyggfe41w60d83jx74q` (`paciente_id`),
  KEY `FKea39x5000cyxw96b986qqydqc` (`procedimiento_id`),
  CONSTRAINT `FKea39x5000cyxw96b986qqydqc` FOREIGN KEY (`procedimiento_id`) REFERENCES `procedimientos` (`id`),
  CONSTRAINT `FKih6c6mkyggfe41w60d83jx74q` FOREIGN KEY (`paciente_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKk7hof5qavalmmpq2l28pceiq0` FOREIGN KEY (`diagnostico_id`) REFERENCES `diagnosticos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Tabla `sala_configs`
--

DROP TABLE IF EXISTS `sala_configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala_configs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cupo` int(11) NOT NULL,
  `domingo` bit(1) DEFAULT NULL,
  `horaini` int(11) NOT NULL,
  `jueves` bit(1) DEFAULT NULL,
  `lunes` bit(1) DEFAULT NULL,
  `martes` bit(1) DEFAULT NULL,
  `miercoles` bit(1) DEFAULT NULL,
  `minini` int(11) NOT NULL,
  `sabado` bit(1) DEFAULT NULL,
  `viernes` bit(1) DEFAULT NULL,
  `sala_id` bigint(20) NOT NULL,
  `minutos_paciente` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2y63ua19h4aa1ohtnggtu84ek` (`sala_id`),
  CONSTRAINT `FKsfd4f2t2lc85iv543mt1n0kfu` FOREIGN KEY (`sala_id`) REFERENCES `salas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `sala_configs`
--

LOCK TABLES `sala_configs` WRITE;
/*!40000 ALTER TABLE `sala_configs` DISABLE KEYS */;
INSERT INTO `sala_configs` VALUES
	(3,20,'\0',15,'','','','',45,'\0','',14,15),
	(4,5,'\0',10,'\0','\0','\0','\0',0,'','\0',12,35),
	(5,20,'\0',9,'','','','',0,'\0','',1,10),
	(6,15,'\0',9,'','','','',30,'\0','',13,20),
	(7,5,'\0',10,'\0','\0','\0','\0',0,'','\0',10,45),
	(8,10,'\0',16,'','','','',0,'\0','',3,40),
	(9,15,'\0',10,'','','','',0,'\0','',15,20),
	(10,25,'\0',16,'','','','',0,'\0','',16,15),
	(11,9,'\0',20,'','\0','','\0',30,'\0','\0',17,50);
/*!40000 ALTER TABLE `sala_configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `salas`
--

DROP TABLE IF EXISTS `salas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `centro_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi5a9n6310gjruk8hn8q2so8vm` (`centro_id`),
  CONSTRAINT `FKi5a9n6310gjruk8hn8q2so8vm` FOREIGN KEY (`centro_id`) REFERENCES `centros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `salas`
--

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES
	(1,'Sala de Curas (L-V; mañana)',2),
	(3,'Cirugía Menor (L-V; Tardes)',2),
	(10,'Sala Intervenciones (S; mañana)',4),
	(12,'Cirugía Menor (Sábados M)',2),
	(13,'Sala de Curas (L-V; mañana)',4),
	(14,'Sala 2 (L-V; tardes)',3),
	(15,'Sala 1 (mañanas)',1),
	(16,'Sala 1 (tardes)',1),
	(17,'Sala de enfermería',2);
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastpasswordresetdate` datetime(6) DEFAULT NULL,
  `lastname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nacimiento` datetime(6) DEFAULT NULL,
  `historia` bigint(20) DEFAULT NULL,
  `colegiado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_jq0ta6mef3p0o47ysw6sflcdl` (`dni`),
  UNIQUE KEY `UK_q74s0p4y0ltxgf41l03uy6eu7` (`historia`),
  UNIQUE KEY `UK_iy5dbmkd6qsfw59pbfd64e3h4` (`colegiado`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
	('ad',1,'admin@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','admin@user.es','52929190A','1975-08-14 01:00:00.000000',NULL,NULL),
	('sa',2,'sanitario@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','sanitario@user.es','52929190B','1975-08-14 01:00:00.000000',NULL,1),
	('pa',3,'enfermo@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','enfermo@user.es','52929190C','1975-08-14 01:00:00.000000',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
  KEY `FKpqlsjpkybgos9w2svcri7j8xy` (`user_id`),
  CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
  CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `user_centro`
--

DROP TABLE IF EXISTS `user_centro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_centro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fin` datetime(6) DEFAULT NULL,
  `inicio` datetime(6) NOT NULL,
  `centro_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3e2ka9r8cwbf2ml89vokq2kod` (`centro_id`),
  KEY `FKs6hcdy5ysqceesga1hyx2f0b3` (`user_id`),
  CONSTRAINT `FK3e2ka9r8cwbf2ml89vokq2kod` FOREIGN KEY (`centro_id`) REFERENCES `centros` (`id`),
  CONSTRAINT `FKs6hcdy5ysqceesga1hyx2f0b3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Datos `user_centro`
--

LOCK TABLES `user_centro` WRITE;
/*!40000 ALTER TABLE `user_centro` DISABLE KEYS */;
INSERT INTO `user_centro` VALUES
	(1,NULL,'2018-08-09 15:35:58.439000',1,1),
	(2,NULL,'2018-08-22 18:28:33.071000',1,2),
	(3,NULL,'2018-08-22 18:28:33.071000',1,3);
/*!40000 ALTER TABLE `user_centro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Tabla `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valoraciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `nota` double NOT NULL,
  `observaciones` varchar(280) DEFAULT NULL,
  `sanitario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa7p0qnq0am62pnwebspyhvih7` (`sanitario_id`),
  CONSTRAINT `FKa7p0qnq0am62pnwebspyhvih7` FOREIGN KEY (`sanitario_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
