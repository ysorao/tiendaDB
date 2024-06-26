
/*CREAR LA BASE DE DATOS, SI LA BASE EXISTE SE ELMINA Y SE CREA DE NUEVO*/;
DROP DATABASE  IF EXISTS `tienda`;
CREATE DATABASE  tienda;



/*USAR EL ESQUEMA tienda, PARA CREAR LOS OBJETOS DE BASES DE DATOS*/
USE tienda;

/* CREAR TABLA TIPO, SI LA TABLA EXISTE SE ELMINA Y SE CREA DE NUEVO*/
DROP TABLE IF EXISTS `tipo`;
CREATE TABLE `tipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*INSERTAR DATOS TABLA TIPO*/
LOCK TABLES `tipo` WRITE;
INSERT INTO `tipo` VALUES (1,'PAPELERIA'),(2,'DROGUERIA'),(3,'SUPERMERCADO');
UNLOCK TABLES;

/*CREAR TABALA PRODUCTO, SI LA TABLA EXISTE SE ELMINA Y SE CREA DE NUEVO*/
DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) DEFAULT NULL,
  `tipo` int DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `minimo` int DEFAULT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  `bodega` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/*INSERTAR DATOS EN LA TABLA PRODUCTO, SI LA TABLA EXISTE SE ELMINA Y SE CREA DE NUEVO*/
LOCK TABLES `producto` WRITE;
INSERT INTO `producto` VALUES (1,'Pan',3,150.00,20,'pan.png',95),(2,'Borrador',1,207.30,10,'borrador.png',80),(3,'Aspirina',2,109.50,8,'aspirina.png',100),(4,'Lapiz',1,550.00,5,'lapiz.png',50);
UNLOCK TABLES;

/*CREAR TABLA VENTAS, SI LA TABLA EXISTE SE ELMINA Y SE CREA DE NUEVO*/
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProducto` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `totalVenta` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idProducto` (`idProducto`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`)
) ;