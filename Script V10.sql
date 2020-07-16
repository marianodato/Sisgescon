--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos` (
  `ca_id` int(11) NOT NULL AUTO_INCREMENT,
  `ca_congreso_id` int(11) NOT NULL,
  `ca_titulo` varchar(40) NOT NULL,
  `ca_resumen` varchar(250) DEFAULT NULL,
  `ca_estado` int(11) NOT NULL,
  `ca_idioma` int(11) NOT NULL,
  PRIMARY KEY (`ca_id`),
  KEY `FK_SISGESCON_ARTICULOS_IDIOMAS` (`ca_idioma`),
  KEY `FK_ARTICULOS_CONGRESOS` (`ca_congreso_id`),
  KEY `FK_SISGESCON_ARTICULOS_ARTICULOS_ESTADOS` (`ca_estado`),
  CONSTRAINT `FK_ARTICULOS_CONGRESOS` FOREIGN KEY (`ca_congreso_id`) REFERENCES `congresos` (`c_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SISGESCON_ARTICULOS_ARTICULOS_ESTADOS` FOREIGN KEY (`ca_estado`) REFERENCES `articulos_estados` (`ae_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SISGESCON_ARTICULOS_IDIOMAS` FOREIGN KEY (`ca_idioma`) REFERENCES `idiomas` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulos_autores`
--

DROP TABLE IF EXISTS `articulos_autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos_autores` (
  `aa_articulo_id` int(11) NOT NULL,
  `aa_usuario_id` int(11) DEFAULT NULL,
  KEY `FK_ARTICULOS_AUTORES_ARTICULOS` (`aa_articulo_id`),
  KEY `FK_ARTICULOS_AUTORES_USUARIOS` (`aa_usuario_id`),
  CONSTRAINT `FK_ARTICULOS_AUTORES_ARTICULOS` FOREIGN KEY (`aa_articulo_id`) REFERENCES `articulos` (`ca_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ARTICULOS_AUTORES_USUARIOS` FOREIGN KEY (`aa_usuario_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulos_autores_secundarios`
--

DROP TABLE IF EXISTS `articulos_autores_secundarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos_autores_secundarios` (
  `aas_id` int(11) NOT NULL AUTO_INCREMENT,
  `aas_articulo_id` int(11) NOT NULL,
  `ass_nombre` varchar(45) NOT NULL,
  `ass_apellido` varchar(45) NOT NULL,
  `ass_mail` varchar(45) NOT NULL,
  `ass_filiacion` varchar(45) NOT NULL,
  `ass_oic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`aas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulos_estados`
--

DROP TABLE IF EXISTS `articulos_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos_estados` (
  `ae_id` int(11) NOT NULL AUTO_INCREMENT,
  `ae_descripcion` char(10) NOT NULL,
  PRIMARY KEY (`ae_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */

--
-- Table structure for table `articulos_evaluaciones`
--

DROP TABLE IF EXISTS `articulos_evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos_evaluaciones` (
  `aev_id` int(11) NOT NULL AUTO_INCREMENT,
  `aev_usuario_id` int(11) NOT NULL,
  `aev_calificacion` int(11) DEFAULT NULL,
  `aev_comentario_autor` varchar(50) DEFAULT NULL,
  `aev_comentario_organizador` varchar(50) DEFAULT NULL,
  `aev_fecha` date DEFAULT NULL,
  `aev_articulo_id` int(11) NOT NULL,
  PRIMARY KEY (`aev_id`),
  KEY `fk_articulo_evaluador_idx` (`aev_usuario_id`),
  KEY `fk_articulo_id_idx` (`aev_articulo_id`),
  CONSTRAINT `fk_articulo_evaluador` FOREIGN KEY (`aev_usuario_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_id` FOREIGN KEY (`aev_articulo_id`) REFERENCES `articulos` (`ca_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulos_versiones`
--

DROP TABLE IF EXISTS `articulos_versiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos_versiones` (
  `av_id` int(11) NOT NULL AUTO_INCREMENT,
  `av_articulo_id` int(11) NOT NULL,
  `av_contenido` longblob NOT NULL,
  `av_version` int(11) NOT NULL,
  PRIMARY KEY (`av_id`),
  KEY `FK_ARTICULOS_VERSIONES_SISGESCON_ARTICULOS` (`av_articulo_id`),
  CONSTRAINT `FK_ARTICULOS_VERSIONES_SISGESCON_ARTICULOS` FOREIGN KEY (`av_articulo_id`) REFERENCES `articulos` (`ca_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auditorias`
--

DROP TABLE IF EXISTS `auditorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditorias` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_tipo` int(11) DEFAULT NULL,
  `a_descripcion` char(10) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `FK_AUDITORIAS_AUDITORIAS_TPO` (`a_tipo`),
  CONSTRAINT `FK_AUDITORIAS_AUDITORIAS_TPO` FOREIGN KEY (`a_tipo`) REFERENCES `auditorias_tpo` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auditorias_tpo`
--

DROP TABLE IF EXISTS `auditorias_tpo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditorias_tpo` (
  `at_id` int(11) NOT NULL AUTO_INCREMENT,
  `at_auditorias_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auditorias_usuarios`
--

DROP TABLE IF EXISTS `auditorias_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditorias_usuarios` (
  `au_auditoria_id` int(11) DEFAULT NULL,
  `au_usuarios_id` int(11) DEFAULT NULL,
  KEY `FK_AUDITORIAS_USUARIOS_AUDITORIAS` (`au_auditoria_id`),
  KEY `FK_AUDITORIAS_USUARIOS_USUARIOS` (`au_usuarios_id`),
  CONSTRAINT `FK_AUDITORIAS_USUARIOS_AUDITORIAS` FOREIGN KEY (`au_auditoria_id`) REFERENCES `auditorias` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_AUDITORIAS_USUARIOS_USUARIOS` FOREIGN KEY (`au_usuarios_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `congresos`
--

DROP TABLE IF EXISTS `congresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `congresos` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_nombre` varchar(200) NOT NULL,
  `c_inicio` date NOT NULL,
  `c_fin` date NOT NULL,
  `c_universidad_sede` varchar(50) NOT NULL,
  `c_ini_presentacion_articulos` date NOT NULL,
  `c_fin_presentacion_articulos` date NOT NULL,
  `c_nombre_acronimo` varchar(12) NOT NULL,
  `c_homepage` varchar(50) DEFAULT NULL,
  `c_email` varchar(50) NOT NULL,
  `c_anio` int(11) NOT NULL,
  `c_ini_eval_articulos` date NOT NULL,
  `c_fin_eval_articulos` date NOT NULL,
  `c_ini_conf_articulos` date NOT NULL,
  `c_fin_conf_articulos` date NOT NULL,
  `c_fin_versiones_articulos` date NOT NULL,
  `c_fin_notificacion_autores` date NOT NULL,
  `c_ini_notificacion_autores` date NOT NULL,
  `c_ini_versiones_articulos` date NOT NULL,
  `c_ini_inscripcion` date NOT NULL,
  `c_fin_inscripcion` date NOT NULL,
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `IX_CONGRESOS` (`c_nombre`,`c_nombre_acronimo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `idiomas`
--

DROP TABLE IF EXISTS `idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idiomas` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_descripcion` char(10) DEFAULT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mails`
--

DROP TABLE IF EXISTS `mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mails` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_descripcion` varchar(500) DEFAULT NULL,
  `m_idioma_id` int(11) DEFAULT NULL,
  `m_tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `FK_MAILS_IDIOMAS` (`m_idioma_id`),
  KEY `FK_MAILS_MAILS_TIPOS` (`m_tipo`),
  CONSTRAINT `FK_MAILS_IDIOMAS` FOREIGN KEY (`m_idioma_id`) REFERENCES `idiomas` (`i_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MAILS_MAILS_TIPOS` FOREIGN KEY (`m_tipo`) REFERENCES `mails_tipos` (`mt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mails_tipos`
--

DROP TABLE IF EXISTS `mails_tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mails_tipos` (
  `mt_id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_descripcion` char(50) DEFAULT NULL,
  PRIMARY KEY (`mt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificaciones` (
  `n_id` int(11) NOT NULL,
  `n_descripcion` varchar(200) DEFAULT NULL,
  `n_usuario_id` int(11) NOT NULL,
  `n_visto` bit(1) NOT NULL DEFAULT b'0',
  `n_fecha` date NOT NULL,
  `n_notificado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`n_id`),
  KEY `pk_notificacion_usuario_idx` (`n_usuario_id`),
  CONSTRAINT `pk_notificacion_usuario` FOREIGN KEY (`n_usuario_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_nombre` varchar(50) NOT NULL,
  `u_apellido` varchar(50) NOT NULL,
  `u_direccion` varchar(50) NOT NULL,
  `u_ciudad` varchar(50) NOT NULL,
  `u_mail` varchar(50) NOT NULL,
  `u_password` varchar(100) NOT NULL,
  `u_usuario` varchar(50) NOT NULL,
  `u_cp` varchar(50) NOT NULL,
  `u_pais` varchar(50) NOT NULL,
  `u_sitioweb` varchar(50) DEFAULT NULL,
  `u_filiacion` varchar(50) NOT NULL,
  `u_miembro_uic` tinyint(1) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios_articulos`
--

DROP TABLE IF EXISTS `usuarios_articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_articulos` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_usuario_id` int(11) NOT NULL,
  `c_articulo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `FK_USUARIOS_ARTICULOS_ARTICULOS` (`c_articulo_id`),
  KEY `FK_USUARIOS_SISGESCON_USUARIOS` (`c_usuario_id`),
  CONSTRAINT `FK_USUARIOS_ARTICULOS_ARTICULOS` FOREIGN KEY (`c_articulo_id`) REFERENCES `articulos` (`ca_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIOS_SISGESCON_USUARIOS` FOREIGN KEY (`c_usuario_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios_congresos_perfiles`
--

DROP TABLE IF EXISTS `usuarios_congresos_perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_congresos_perfiles` (
  `ucp_usuario_id` int(11) NOT NULL,
  `ucp_congreso_id` int(11) NOT NULL,
  `ucp_perfil_id` int(11) NOT NULL,
  KEY `FK_USUARIOS_CONGRESOS_PERFILES_PERFILES` (`ucp_perfil_id`),
  KEY `FK_USUARIOS_CONGRESOS_PERFILES_USUARIOS` (`ucp_usuario_id`),
  KEY `FK_USUARIOS_CONGRESOS_PERFILES_CONGRESOS` (`ucp_congreso_id`),
  CONSTRAINT `FK_USUARIOS_CONGRESOS_PERFILES_CONGRESOS` FOREIGN KEY (`ucp_congreso_id`) REFERENCES `congresos` (`c_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIOS_CONGRESOS_PERFILES_PERFILES` FOREIGN KEY (`ucp_perfil_id`) REFERENCES `perfiles` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIOS_CONGRESOS_PERFILES_USUARIOS` FOREIGN KEY (`ucp_usuario_id`) REFERENCES `usuarios` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;