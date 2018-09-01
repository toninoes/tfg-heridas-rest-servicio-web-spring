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

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: restheridapp
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `activacion_usuario`
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
-- Dumping data for table `activacion_usuario`
--

LOCK TABLES `activacion_usuario` WRITE;
/*!40000 ALTER TABLE `activacion_usuario` DISABLE KEYS */;
INSERT INTO `activacion_usuario` VALUES (9,'2018-08-23 18:28:33.227000','407bf71a-ec87-4f28-a00f-b6faa55070aa',43);
/*!40000 ALTER TABLE `activacion_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
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
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_SANITARIO'),(3,'ROLE_PACIENTE');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centros`
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
-- Dumping data for table `centros`
--

LOCK TABLES `centros` WRITE;
/*!40000 ALTER TABLE `centros` DISABLE KEYS */;
INSERT INTO `centros` VALUES (1,'c/ Lope de Vega, 12. 11008 Cádiz','C.S. El Olivillo','956123456'),(2,'c/ Avenida de la Bahía, s/n. 11007 Cádiz','C.S. Loreto-Puntales','956121212'),(3,'Avda. Cayetano del Toro, 4. 11100 San Fernando (Cádiz)','C.S. La Ardila','951623458'),(4,'c/ Doctor Cellier, s/n. 11100 San Fernando (Cádiz)','C.S. Rodriguez Arias','951741456');
/*!40000 ALTER TABLE `centros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citas`
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
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
INSERT INTO `citas` VALUES (1,'2015-04-04',1,38,10),(7,'2016-01-01',1,38,1),(9,'2018-08-18',2,39,12),(10,'2018-08-12',4,38,12),(14,'2018-08-29',1,38,1),(15,'2018-08-15',2,38,3),(16,'2018-08-31',4,38,3),(17,'2018-08-31',1,39,3),(18,'2018-09-13',1,38,1);
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuidados`
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
-- Dumping data for table `cuidados`
--

LOCK TABLES `cuidados` WRITE;
/*!40000 ALTER TABLE `cuidados` DISABLE KEYS */;
INSERT INTO `cuidados` VALUES (1,'2018-01-01 00:00:00.000000','popopo88871','ssss88',8,36),(2,'2018-08-19 20:53:30.486000','adios','Hola',1,36);
/*!40000 ALTER TABLE `cuidados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curas`
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
-- Dumping data for table `curas`
--

LOCK TABLES `curas` WRITE;
/*!40000 ALTER TABLE `curas` DISABLE KEYS */;
INSERT INTO `curas` VALUES (1,'2018-08-15 20:24:49.760000','2018-01-01 00:00:00.000000','Lorem fistrum pecador se calle ustée diodeno la caidita tiene musho peligro jarl hasta luego Lucas te va a hasé pupitaa amatomaa me cago en tus muelas. ','nada','perfecto','',3,36),(2,'2018-08-17 13:07:05.195000','2018-02-14 13:16:44.067000','Te voy a borrar el cerito se calle ustée pupita no te digo trigo por no llamarte Rodrigor condemor a gramenawer. Se calle ustée torpedo caballo blanco caballo negroorl ese que llega a wan a gramenawer al ataquerl te va a hasé pupitaa.','AAA','AAA','',3,36),(3,'2018-08-16 11:29:47.034000','2018-03-04 10:16:44.067000','Diodeno no te digo trigo por no llamarte Rodrigor la caidita sexuarl. Llevame al sircoo papaar papaar caballo blanco caballo negroorl a peich. Papaar papaar no puedor condemor apetecan diodeno me cago en tus muelas condemor se calle ustée.','BBB','BBBB','',3,36),(4,'2018-08-16 11:30:33.045000','2018-04-11 04:16:44.067000','Torpedo hasta luego Lucas sexuarl ese pedazo de me cago en tus muelas está la cosa muy malar está la cosa muy malar. Llevame al sircoo a peich diodeno tiene musho peligro.','CCC','CCC','',4,37),(5,'2018-08-16 10:47:09.001000','2018-05-12 13:06:44.067000','No puedor ese pedazo de caballo blanco caballo negroorl se calle ustée ese que llega a wan no puedor tiene musho peligro quietooor pecador.','DDDD','DDDD','',4,36),(6,'2018-08-15 22:18:15.919000','2018-06-14 12:16:44.067000','Fistro a gramenawer a wan fistro por la gloria de mi madre torpedo apetecan apetecan sexuarl jarl te voy a borrar el cerito. Ese hombree diodeno llevame al sircoo quietooor se calle ustée se calle ustée.','EEEE','EEEE','',4,37),(7,'2018-08-22 20:12:21.952000','2018-08-18 12:26:47.211000','muy bie','maravillosos','perfecto','',1,36),(8,'2018-08-22 20:12:30.054000','2018-08-20 00:45:40.300000','thgfc','h g no kjon fg','thffgjinjf','',2,36),(9,'2018-08-22 20:12:26.058000','2018-08-20 00:46:04.662000',NULL,NULL,'Exéresis de la lesión','',7,36),(10,'2018-08-22 20:12:16.270000','2018-08-20 00:46:27.754000',NULL,NULL,'Exéresis de la lesión','',8,36),(11,'2018-08-31 20:28:17.814000','2018-08-31 20:28:17.814000',NULL,NULL,'Biopsia de piel y tejido subcutáneo','\0',9,36);
/*!40000 ALTER TABLE `curas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosticos`
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
-- Dumping data for table `diagnosticos`
--

LOCK TABLES `diagnosticos` WRITE;
/*!40000 ALTER TABLE `diagnosticos` DISABLE KEYS */;
INSERT INTO `diagnosticos` VALUES (1,'L02','Absceso cutáneo',1),(2,'L02','Forúnculo',1),(3,'L02','Antrax',1),(4,'L05','Quiste pilonidal con absceso',1),(5,'L08','Otras infecciones locales de la piel y tejido subcutáneo',1),(6,'T14','Heridas cutáneas',2),(7,'T14','Cuerpos extraños',2),(8,'T14','Traumatismos ungueales',3),(9,'L60','Uña encarnada',3),(10,'L60','Onicodistrofias',3),(11,'L60','Onicomicosis',3),(12,'L60','Otros trastornos de las uñas',3),(13,'L72','Quiste epidérmico',4),(14,'L72','Quiste tricodérmico',4),(15,'L72','Otros quistes foliculares de la piel y del tejido subcutáneo',4),(16,'L73','Foliculitis',5),(17,'L73','Hidradenitis supurativa',5),(18,'L73','Otros trastornos foliculares no especificados',5),(19,'B07','Verrugas viricas',6),(20,'B08','Molusco contagioso',6),(21,'B08','Condilomas acuminados',6),(22,'B09','Otras infecciones víricas de la piel',6),(23,'D17','Lipoma',7),(24,'D17','Fibrolipoma',7),(25,'D18','Hemangiomas',7),(26,'D22','Nevo melanocitico',7),(27,'D23','Fibromas',7),(28,'D23','Dermatofibromas',7),(29,'D23','Neurofibromas',7),(30,'D23','Otros tumores benignos no especificados',7),(31,'L82','Queratosis seborreicas',8),(32,'L84','Callos y callosidades',8),(33,'L90','Fibrosis y afecciones cicatriciales de la piel',8),(34,'L91','Cicatriz queloide',8),(35,'L97','Úlcera de miembro inferior',8),(36,'L98','Granuloma piogeno',8),(37,'L98','Otros trastornos de la piel y del tejido subcutáneo',8);
/*!40000 ALTER TABLE `diagnosticos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupodiagnostico`
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
-- Dumping data for table `grupodiagnostico`
--

LOCK TABLES `grupodiagnostico` WRITE;
/*!40000 ALTER TABLE `grupodiagnostico` DISABLE KEYS */;
INSERT INTO `grupodiagnostico` VALUES (1,'Infecciones locales de la piel y del tejido subcutáneo'),(6,'Infecciones virales caracterizadas por lesiones de la piel y de las membranas mucosas'),(8,'Otras enfermedades de la piel'),(5,'Otros trastornos foliculares'),(4,'Quistes foliculares de la piel y del tejido subcutáneo'),(3,'Trastornos/traumatismos de las uñas'),(2,'Traumatismos cutáneos'),(7,'Tumores benignos de la piel');
/*!40000 ALTER TABLE `grupodiagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
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
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (1,'2018-08-14 13:17:12.529000','nada','2018-08-14_111711164.jpg',2,'0000-00-00 00:00:00.000000'),(2,'2018-08-14 19:45:46.047000','hola','2018-08-14_194542131.jpg',2,'0000-00-00 00:00:00.000000'),(3,'2018-08-18 12:27:25.888000','mira que cosa más bonita','2018-08-18_122727995.jpg',7,'0000-00-00 00:00:00.000000'),(4,'2018-08-18 12:29:27.262000','pie','2018-08-18_122930723.jpg',7,'0000-00-00 00:00:00.000000'),(5,'2018-08-18 13:06:31.106000','Holaaaaa','DZZ28TWUMAAF8pN.jpg',1,'0000-00-00 00:00:00.000000'),(6,'2018-08-18 13:08:31.448000','Holaaaaa','DZZ28TWUMAAF8pN.jpg',1,'0000-00-00 00:00:00.000000'),(7,'2018-08-18 13:41:13.136000','Holaaaaa','abril.jpeg',1,'0000-00-00 00:00:00.000000'),(8,'2018-08-18 21:53:51.981000','tios weno','2018-08-18_215350111.jpg',6,'0000-00-00 00:00:00.000000'),(9,'2018-08-19 01:26:20.965000','','2018-08-18_232618654.jpg',7,'0000-00-00 00:00:00.000000'),(10,'2018-08-20 00:47:18.801000','ggg','2018-08-20_004718639.jpg',10,'0000-00-00 00:00:00.000000'),(11,'2018-08-23 00:02:57.837000','','2018-08-23_000259833.jpg',9,'0000-00-00 00:00:00.000000'),(12,'2018-08-31 20:35:50.308000','','2018-08-31_203553987.jpg',11,'2018-08-31 20:35:50.308000'),(13,'2018-08-31 20:48:17.405000','','2018-08-31_204820713.jpg',11,'2018-08-31 20:48:17.405000');
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
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
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimientos`
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
-- Dumping data for table `procedimientos`
--

LOCK TABLES `procedimientos` WRITE;
/*!40000 ALTER TABLE `procedimientos` DISABLE KEYS */;
INSERT INTO `procedimientos` VALUES (1,'86.01','Aspiración de piel y tejido subcutáneo'),(2,'86.04','Incisión con drenaje de piel y tejido subcutáneo'),(3,'86.05','Incisión con extracción de cuerpo extraño de piel y tejido subcutáneo'),(4,'86.11','Biopsia de piel y tejido subcutáneo'),(5,'86.2.0','Exéresis de la lesión'),(6,'86.2.1','Escisión o destrucción de lesión'),(7,'86.21','Extirpación de quiste o seno pilonidal'),(8,'86.22','Desbridamiento escisional de herida, infección o quemadura'),(9,'86.23','Extracción de uña, lecho de uña o pliegue de uña'),(10,'86.24','Quimiocirugía de piel'),(11,'86.25','Abrasión dérmica'),(12,'86.27','Desbridamiento de uña, base de uña o pliegue de uña'),(13,'86.5','Sutura u otro cierre de piel y tejido subcutáneo'),(14,'91.62','E.M. Muestra de piel y otro tegumento cultivo'),(15,'92.01','Desbridamiento cortante'),(16,'92.02','Desbridamiento quirúrgico'),(17,'92.03','Escisión fusiforme'),(18,'92.04','Escisión tangencial'),(19,'92.05','Drenaje de hematoma subungueal'),(20,'92.06','Reimplantación de lámina ungueal'),(21,'92.07','Matricectomía mecánica (parcial/total)'),(22,'92.08','Matricectomía química (parcial/total)'),(23,'92.09','Matricectomía eléctrica (parcial/total)'),(24,'92.10','Avulsión química de uña'),(25,'98.2','Extracción sin incisión de otro cuerpo extraño'),(26,'100','Otros procedimientos');
/*!40000 ALTER TABLE `procedimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procesos`
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
-- Dumping data for table `procesos`
--

LOCK TABLES `procesos` WRITE;
/*!40000 ALTER TABLE `procesos` DISABLE KEYS */;
INSERT INTO `procesos` VALUES (1,'2018-01-01 00:00:00.000000','Bienvenido al Chiquito Ipsum, el generador de texto de relleno para tus diseños de antes de los dolores.','2018-01-01 00:00:00.000000','Lorem fistrum adipisicing duis elit velit consectetur va usté muy cargadoo papaar papaar fistro. Qué dise usteer voluptate magna te voy a borrar el cerito ad esse ese hombree occaecat te voy a borrar el cerito sit amet esse.',1,38,1,NULL,NULL),(2,'2018-08-14 13:16:44.035000','qqq','2018-08-14 13:16:44.035000','sss',32,38,4,NULL,NULL),(3,'2018-08-14 13:16:44.035000','qqq','2018-08-14 13:16:44.035000','sss',32,38,4,NULL,NULL),(4,'2018-08-14 13:16:44.035000','qqq','2018-08-14 13:16:44.035000','sss',32,38,4,NULL,NULL),(5,'2018-08-14 13:16:44.035000','qqq','2018-08-14 13:16:44.035000','sss',1,38,4,NULL,NULL),(6,'2018-08-14 13:16:44.035000','qqq','2018-08-14 13:16:44.035000','sss',16,38,4,NULL,NULL),(7,'2018-08-20 00:46:04.658000','pop','2018-08-20 00:46:04.658000','yuh',33,38,5,NULL,NULL),(8,'2018-08-20 00:46:27.738000','thjgf','2018-08-20 00:46:27.738000','ijhvf',34,38,5,NULL,NULL),(9,'2018-08-31 20:28:17.783000','guifffgg','2018-08-31 20:28:17.783000','higcf',4,43,4,NULL,NULL);
/*!40000 ALTER TABLE `procesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala_configs`
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
-- Dumping data for table `sala_configs`
--

LOCK TABLES `sala_configs` WRITE;
/*!40000 ALTER TABLE `sala_configs` DISABLE KEYS */;
INSERT INTO `sala_configs` VALUES (3,20,'\0',15,'','','','',45,'\0','',14,15),(4,5,'\0',10,'\0','\0','\0','\0',0,'','\0',12,35),(5,20,'\0',9,'','','','',0,'\0','',1,10),(6,15,'\0',9,'','','','',30,'\0','',13,20),(7,5,'\0',10,'\0','\0','\0','\0',0,'','\0',10,45),(8,10,'\0',16,'','','','',0,'\0','',3,40),(9,15,'\0',10,'','','','',0,'\0','',15,20),(10,25,'\0',16,'','','','',0,'\0','',16,15),(11,9,'\0',20,'','\0','','\0',30,'\0','\0',17,50);
/*!40000 ALTER TABLE `sala_configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salas`
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
-- Dumping data for table `salas`
--

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES (1,'Sala de Curas (L-V; mañana)',2),(3,'Cirugía Menor (L-V; Tardes)',2),(10,'Sala Intervenciones (S; mañana)',4),(12,'Cirugía Menor (Sábados M)',2),(13,'Sala de Curas (L-V; mañana)',4),(14,'Sala 2 (L-V; tardes)',3),(15,'Sala 1 (mañanas)',1),(16,'Sala 1 (tardes)',1),(17,'caca',2);
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
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
INSERT INTO `user` VALUES ('ad',1,'admin@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','admin@user.es','52929190A','1975-08-14 01:00:00.000000',NULL,NULL),('sa',2,'sanitario@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','sanitario@user.es','52929190B','1975-08-14 01:00:00.000000',NULL,1),('pa',3,'enfermo@user.es','','ANTONIO','2018-01-01 00:00:00.000000','RUIZ RONDÁN','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e','enfermo@user.es','52929190C','1975-08-14 01:00:00.000000',1,NULL),('sa',36,'prueba@user.es','','MANUEL','2018-07-23 12:10:57.348000','MONTERO AGUILAR','$2a$10$27PhGnBkt1/z1cO/soOmcecPEekNoEESZmDoRoUR915vuQNiuGKPu','prueba@user.es','123412754','1996-07-23 00:00:00.000000',NULL,12089),('sa',37,'noa@user.es','','ANTONIO','2018-07-23 12:18:11.267000','RUIZ RONDÁN','$2a$10$mnsPwzxDOPwJ/MZIhG5vNOOKR7YNK1XpHC98Vz3nGh/XF6shwdG2G','noa@user.es','741852000','2015-11-11 01:00:00.000000',NULL,12087),('pa',38,'paciente@user.es','','FULANITO(38)','2018-08-09 20:45:41.816000','FERNÁNDEZ','$2a$10$QDPxgrxHmA6laHPP.08tHOfDRtedvNrIvH4q7zWqfea.Qqoc5oNBi','paciente@user.es','147852321','2010-08-09 02:00:00.000000',4,NULL),('pa',39,'paciente2@user.es','','MENGANITO(39)','2018-08-11 19:41:48.533000','MÉNDEZ','$2a$10$fh2KGtd9WztjVQ8FgQBKKuJ3L6qYCL5yhvsvfqHenOvoQNnT3axMC','paciente2@user.es','222222222','1975-08-15 01:00:00.000000',2,NULL),('ad',40,'toninoes@hotmail.com','','AAAAAS','2018-08-20 12:09:27.562000','QQQQQQQQQQQ','$2a$10$bgdi9oxFWyatAC2FIEcZX.gnMBAwLT05x8ROEfRG2WYzAtXAXKB06','toninoes@user.es','QWERTYUIO','2018-08-09 00:00:00.000000',NULL,NULL),('pa',43,'toninoes@gmail.com','','TONI','2018-08-22 18:28:33.212000','DE CADY','$2a$10$y0xjOjAs.qq1yhbmgzJDROzXl8i1qQFVpxgwfTAtVM/nP1AWIcoue','toninoes@gmail.com','567897654','1975-08-14 00:00:00.000000',3,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
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
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (36,2),(37,2),(38,3),(39,3),(1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_centro`
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
-- Dumping data for table `user_centro`
--

LOCK TABLES `user_centro` WRITE;
/*!40000 ALTER TABLE `user_centro` DISABLE KEYS */;
INSERT INTO `user_centro` VALUES (50,NULL,'2018-08-09 15:35:58.439000',1,1),(51,NULL,'2018-08-09 15:36:25.101000',3,37),(52,NULL,'2018-08-09 15:37:36.123000',2,36),(53,NULL,'2018-08-09 20:45:41.586000',2,38),(54,NULL,'2018-08-11 19:41:48.310000',2,39),(55,NULL,'2018-08-20 12:09:27.390000',3,40),(58,NULL,'2018-08-22 18:28:33.071000',1,43),(59,NULL,'2018-08-22 18:28:33.071000',1,3);
/*!40000 ALTER TABLE `user_centro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoraciones`
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

--
-- Dumping data for table `valoraciones`
--

LOCK TABLES `valoraciones` WRITE;
/*!40000 ALTER TABLE `valoraciones` DISABLE KEYS */;
INSERT INTO `valoraciones` VALUES (1,'2018-07-03',4,'La caidita apetecan pecador jarl papaar papaar condemor va usté muy cargadoo ese pedazo de. Amatomaa a wan ahorarr a wan al ataquerl fistro te va a hasé pupitaa ahorarr. Diodenoo te va a hasé pupitaa qué dise usteer ese pedazo de jarl a wan ese pedazo de por la',36),(2,'2018-07-11',4,'Lorem fistrum se calle ustée tiene musho peligro por la gloria de mi madre. Hasta luego Lucas te voy a borrar el cerito torpedo hasta luego Lucas la caidita hasta luego Lucas te va a hasé pupitaa. Ahorarr va usté muy cargadoo caballo blanco caballo negroorl diodeno tiene musho.',36),(3,'2018-07-25',8,'va usté muy cargadoo condemor mamaar tiene musho peligro me cago en tus muelas ese hombree al ataquerl. A gramenawer no puedor apetecan ese pedazo de apetecan ese pedazo de apetecan a wan a gramenawer quietooor.',36),(4,'2018-08-09',6,'La caidita apetecan pecador jarl papaar papaar condemor va usté muy cargadoo ese pedazo de. Amatomaa a wan ahorarr a wan al ataquerl fistro te va a hasé pupitaa ahorarr. Diodenoo te va a hasé pupitaa qué dise usteer ese pedazo de jarl a wan ese pedazo de por la gloria',36),(5,'2018-08-11',9,'malar. Jarl pecador no puedor torpedo amatomaa diodeno ahorarr no te digo trigo por no llamarte Rodrigor pupita. Diodeno fistro ese pedazo de ese hombree ese hombree a gramenawer hasta luego Lucas.',36),(6,'2018-07-01',1,'muy mal',37),(7,'2018-07-05',7,'un 7 va de sobra',37),(8,'2018-07-21',4,'muy maleducado un 4',37),(9,'2018-08-10',5,'justito un 5',37),(10,'2018-08-14',6,'no esta mal. un 6',37),(11,'2018-08-15',8,'hola',37),(12,'2018-08-15',8,'hola',37),(13,'2018-08-15',8,'hola',36),(14,'2018-08-15',5,'killo',37),(15,'2018-08-16',5,'Lorem fistrum ese que llega apetecan torpedo te va a hasé pupitaa al ataquerl papaar papaar pecador. Ese pedazo de pecador caballo blanco caballo negroorl qué dise usteer de la pradera diodeno quietooor a peich me cago en tus muelas caballo blanco caballo negroorl.',36),(16,'2018-08-16',2.5,'Muy mal',36),(17,'2018-08-16',10,'muy bueno el enfermero que me atendió.',37),(18,'2018-08-17',7.5,'Me ha parecido todo muy bien.',36),(19,'2018-08-22',5,'',36),(20,'2018-08-22',0,'',36),(21,'2018-08-22',10,'',36),(22,'2018-08-22',7.5,'',36);
/*!40000 ALTER TABLE `valoraciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-01 12:00:24
