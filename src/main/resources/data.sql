--
-- GRUPODIAGNOSTICO
--
INSERT INTO `grupodiagnostico` VALUES 	(1,'Infecciones locales de la piel y del tejido subcutáneo'),
										(2,'Traumatismos cutáneos'),
										(3,'Trastornos/traumatismos de las uñas'),
										(4,'Quistes foliculares de la piel y del tejido subcutáneo'),
										(5,'Otros trastornos foliculares'),
										(6,'Infecciones virales caracterizadas por lesiones de la piel y de las membranas mucosas'),
										(7,'Tumores benignos de la piel'),
										(8,'Otras enfermedades de la piel');


--
-- PROCEDIMIENTOS
--
INSERT INTO `procedimientos` VALUES	(1,'86.01','Aspiración de piel y tejido subcutáneo'),
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


--
-- DIAGNOSTICOS
--
INSERT INTO `diagnosticos` VALUES 	(1,'L02','Absceso cutáneo',1),
									(2,'L02','Forúnculo',1),
									(3,'L02','Antrax',1),
									(4,'L05','Quiste pilonidal con absceso',1),
									(5,'L08','Otras infecciones locales de la piel y tejido subcutáneo',1),
									(6,'T14','Heridas cutáneas',2),
									(7,'T14','Cuerpos extraños',2),
									(8,'T14','Traumatismos ungueales',3),
									(9,'L60','Uña encarnada',3),
									(10,'L60','Onicodistrofias',3),
									(11,'L60','Onicomicosis',3),
									(12,'L60','Otros trastornos de las uñas',3),
									(13,'L72','Quiste epidérmico',4),
									(14,'L72','Quiste tricodérmico',4),
									(15,'L72','Otros quistes foliculares de la piel y del tejido subcutáneo',4),
									(16,'L73','Foliculitis',5),
									(17,'L73','Hidradenitis supurativa',5),
									(18,'L73','Otros trastornos foliculares no especificados',5),
									(19,'B07','Verrugas viricas',6),
									(20,'B08','Molusco contagioso',6),
									(21,'B08','Condilomas acuminados',6),
									(22,'B09','Otras infecciones víricas de la piel',6),
									(23,'D17','Lipoma',7),
									(24,'D17','Fibrolipoma',7),
									(25,'D18','Hemangiomas',7),
									(26,'D22','Nevo melanocitico',7),
									(27,'D23','Fibromas',7),
									(28,'D23','Dermatofibromas',7),
									(29,'D23','Neurofibromas',7),
									(30,'D23','Otros tumores benignos no especificados',7),
									(31,'L82','Queratosis seborreicas',8),
									(32,'L84','Callos y callosidades',8),
									(33,'L90','Fibrosis y afecciones cicatriciales de la piel',8),
									(34,'L91','Cicatriz queloide',8),
									(35,'L97','Úlcera de miembro inferior',8),
									(36,'L98','Granuloma piogeno',8),
									(37,'L98','Otros trastornos de la piel y del tejido subcutáneo',8);


--
-- GRUPO_PROCEDIMIENTO
--
INSERT INTO `grupo_procedimiento` VALUES (1,1),(1,2),(2,3),(7,4),(7,5),(7,6),(8,6),(4,7),(2,8),(8,8),(3,9),(6,10),(8,10),(6,11),(7,11),(8,11),(3,12),(2,13),(4,13),(5,13),(7,13),(1,14),(3,14),(8,15),(8,16),(5,17),(7,17),(7,18),(8,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24),(2,25),(1,26),(2,26),(3,26),(4,26),(5,26),(6,26),(7,26),(8,26);


--
-- AUTHORITY
--
INSERT INTO `authority` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_SANITARIO'),(3,'ROLE_PACIENTE');


--
-- USER
--
INSERT INTO `user` VALUES 	(1,'paciente@user.es','','Paciente','2018-07-01 19:26:06.862000','Paciente','$2a$10$BRRtMHxLQNkkNzbwC2ZSCeQsNFyW8GU9aal7un2T91ShHD.ImnCLa',NULL,'paciente@user.es'),
							(2,'sanitario@user.es','','Sanitario','2018-07-01 19:26:07.049000','Sanitario','$2a$10$zkludnbw9WR/cgf8pf1OGe/9qv93/FXqxZiqDMfKnbvMeAkFwUVYG',NULL,'sanitario@user.es'),
							(3,'admin@user.es','','Admin','2018-07-01 19:26:07.193000','Admin','$2a$10$IETtrezVDbKnLPcGV4lPEeyba4oDBkjiqUoeknajTvVrToS/.Z05e',NULL,'admin@user.es');


--
-- PACIENTES
--
INSERT INTO `pacientes` VALUES ('2018-07-01 19:26:06.909000','2018-07-01 19:26:06.862000',NULL,1,'2018-07-01 19:26:06.862000',1);


--
-- SANITARIOS
--
INSERT INTO `sanitarios` VALUES ('2018-07-01 19:26:07.049000',7699,'2018-07-01 19:26:07.049000','52929190A','2018-07-01 19:26:07.049000',2);


--
-- USER_AUTHORITY
--
INSERT INTO `user_authority` VALUES (1,3),(2,2),(3,1);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
