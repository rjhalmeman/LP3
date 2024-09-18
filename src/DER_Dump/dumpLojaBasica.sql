CREATE DATABASE  IF NOT EXISTS `LojaBasica` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `LojaBasica`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: LojaBasica
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cargo`
--

DROP TABLE IF EXISTS `Cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cargo` (
  `idCargo` int NOT NULL,
  `nomeCargo` varchar(45) NOT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cargo`
--

LOCK TABLES `Cargo` WRITE;
/*!40000 ALTER TABLE `Cargo` DISABLE KEYS */;
INSERT INTO `Cargo` VALUES (1,'Gerente'),(2,'Vendedor'),(3,'Auxiliar de limpeza');
/*!40000 ALTER TABLE `Cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cidade`
--

DROP TABLE IF EXISTS `Cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cidade` (
  `idCidade` int NOT NULL,
  `nomeCidade` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `siglaEstado` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idCidade`),
  KEY `fk_cidade_estado1_idx` (`siglaEstado`),
  CONSTRAINT `cidade_estado_FK` FOREIGN KEY (`siglaEstado`) REFERENCES `Estado` (`siglaEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cidade`
--

LOCK TABLES `Cidade` WRITE;
/*!40000 ALTER TABLE `Cidade` DISABLE KEYS */;
INSERT INTO `Cidade` VALUES (1,'Campo Mourão','pr'),(2,'Araruna','pr');
/*!40000 ALTER TABLE `Cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `rendaCliente` double NOT NULL,
  `dataDeCadastroCliente` date NOT NULL,
  `PessoaCpfPessoa` varchar(20) NOT NULL,
  PRIMARY KEY (`PessoaCpfPessoa`),
  CONSTRAINT `fk_Cliente_Pessoa1` FOREIGN KEY (`PessoaCpfPessoa`) REFERENCES `Pessoa` (`cpfPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES (10000,'2024-05-06','222'),(5000,'2024-06-05','333');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Endereco`
--

DROP TABLE IF EXISTS `Endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Endereco` (
  `idEndereco` int NOT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `referencia` varchar(45) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `CidadeIdCidade` int NOT NULL,
  PRIMARY KEY (`idEndereco`),
  KEY `fk_Endereco_Cidade1_idx` (`CidadeIdCidade`),
  CONSTRAINT `fk_Endereco_Cidade1` FOREIGN KEY (`CidadeIdCidade`) REFERENCES `Cidade` (`idCidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Endereco`
--

LOCK TABLES `Endereco` WRITE;
/*!40000 ALTER TABLE `Endereco` DISABLE KEYS */;
INSERT INTO `Endereco` VALUES (1,'Rua do tropeços','10','nenhuma','87300400',1),(2,'Rua dos buracos','50','lá no fim do mundo','89800899',2);
/*!40000 ALTER TABLE `Endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `EnderecoCidadeEstadoPais`
--

DROP TABLE IF EXISTS `EnderecoCidadeEstadoPais`;
/*!50001 DROP VIEW IF EXISTS `EnderecoCidadeEstadoPais`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `EnderecoCidadeEstadoPais` AS SELECT 
 1 AS `cpfPessoa`,
 1 AS `nomePessoa`,
 1 AS `logradouro`,
 1 AS `numero`,
 1 AS `nomeCidade`,
 1 AS `nomeEstado`,
 1 AS `nomePais`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Estado`
--

DROP TABLE IF EXISTS `Estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Estado` (
  `siglaEstado` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nomeEstado` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `PaisIdPais` int NOT NULL,
  PRIMARY KEY (`siglaEstado`),
  KEY `fk_Estado_Pais1_idx` (`PaisIdPais`),
  CONSTRAINT `fk_Estado_Pais1` FOREIGN KEY (`PaisIdPais`) REFERENCES `Pais` (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado`
--

LOCK TABLES `Estado` WRITE;
/*!40000 ALTER TABLE `Estado` DISABLE KEYS */;
INSERT INTO `Estado` VALUES ('ny','New York',1),('pr','Paraná',55),('sc','Santa Catarina',55),('sp','São Paulo',55);
/*!40000 ALTER TABLE `Estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Funcionario`
--

DROP TABLE IF EXISTS `Funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Funcionario` (
  `PessoaCpfPessoa` varchar(20) NOT NULL,
  `salario` double DEFAULT NULL,
  `CargosIdCargo` int NOT NULL,
  PRIMARY KEY (`PessoaCpfPessoa`),
  KEY `fk_Funcionario_Cargos1_idx` (`CargosIdCargo`),
  CONSTRAINT `fk_Funcionario_Cargos1` FOREIGN KEY (`CargosIdCargo`) REFERENCES `Cargo` (`idCargo`),
  CONSTRAINT `fk_funcionario_pessoa1` FOREIGN KEY (`PessoaCpfPessoa`) REFERENCES `Pessoa` (`cpfPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Funcionario`
--

LOCK TABLES `Funcionario` WRITE;
/*!40000 ALTER TABLE `Funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pais`
--

DROP TABLE IF EXISTS `Pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pais` (
  `idPais` int NOT NULL DEFAULT '55',
  `nomePais` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `siglaPais` varchar(3) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pais`
--

LOCK TABLES `Pais` WRITE;
/*!40000 ALTER TABLE `Pais` DISABLE KEYS */;
INSERT INTO `Pais` VALUES (1,'Estados Unidos da América','EUA'),(3,'Argentina','ARG'),(33,'asdfasf','555'),(55,'Brasil','BRA');
/*!40000 ALTER TABLE `Pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedido`
--

DROP TABLE IF EXISTS `Pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pedido` (
  `idPedido` int NOT NULL,
  `dataDoPedido` date NOT NULL,
  `ClientePessoaCpfPessoa` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_Pedido_Cliente1_idx` (`ClientePessoaCpfPessoa`),
  CONSTRAINT `fk_Pedido_Cliente1` FOREIGN KEY (`ClientePessoaCpfPessoa`) REFERENCES `Cliente` (`PessoaCpfPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedido`
--

LOCK TABLES `Pedido` WRITE;
/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
INSERT INTO `Pedido` VALUES (1,'2024-09-05','333'),(2,'2024-04-20','222'),(5,'2024-05-20','222');
/*!40000 ALTER TABLE `Pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PedidoHasProduto`
--

DROP TABLE IF EXISTS `PedidoHasProduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PedidoHasProduto` (
  `quantidade` int NOT NULL,
  `precoUnitarioProduto` double NOT NULL,
  `ProdutoIdProduto` int NOT NULL,
  `PedidoIdPedido` int NOT NULL,
  PRIMARY KEY (`ProdutoIdProduto`,`PedidoIdPedido`),
  KEY `fk_pedido_has_produto_produto1_idx` (`ProdutoIdProduto`),
  KEY `fk_PedidoHasProduto_Pedido1_idx` (`PedidoIdPedido`),
  CONSTRAINT `fk_pedido_has_produto_produto1` FOREIGN KEY (`ProdutoIdProduto`) REFERENCES `Produto` (`idProduto`),
  CONSTRAINT `fk_PedidoHasProduto_Pedido1` FOREIGN KEY (`PedidoIdPedido`) REFERENCES `Pedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PedidoHasProduto`
--

LOCK TABLES `PedidoHasProduto` WRITE;
/*!40000 ALTER TABLE `PedidoHasProduto` DISABLE KEYS */;
INSERT INTO `PedidoHasProduto` VALUES (2,22,1,1),(1,2,1,2),(1,0,1,5),(3,3,2,1),(1,7,2,5),(1,0,22,2),(1,0,22,5),(5,8,33,5);
/*!40000 ALTER TABLE `PedidoHasProduto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pessoa` (
  `cpfPessoa` varchar(20) NOT NULL,
  `nomePessoa` varchar(60) NOT NULL,
  `dataNascimentoPessoa` date NOT NULL,
  `EnderecoIdEndereco` int NOT NULL,
  PRIMARY KEY (`cpfPessoa`),
  KEY `fk_Pessoa_Endereco1_idx` (`EnderecoIdEndereco`),
  CONSTRAINT `fk_Pessoa_Endereco1` FOREIGN KEY (`EnderecoIdEndereco`) REFERENCES `Endereco` (`idEndereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES ('111','Berola','2024-05-23',1),('222','Zózoio Cego','2024-05-23',1),('333','xeroncio da silva','2000-05-10',2),('555','aaaaabbbbb','2025-10-10',1);
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Produto`
--

DROP TABLE IF EXISTS `Produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Produto` (
  `idProduto` int NOT NULL,
  `nomeProduto` varchar(45) NOT NULL,
  `quantidadeEmEstoque` int NOT NULL,
  `UnidadeDeMedidaSiglaUnidadeDeMedida` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idProduto`),
  KEY `fk_Produto_UnidadeDeMedida1_idx` (`UnidadeDeMedidaSiglaUnidadeDeMedida`),
  CONSTRAINT `fk_Produto_UnidadeDeMedida1` FOREIGN KEY (`UnidadeDeMedidaSiglaUnidadeDeMedida`) REFERENCES `UnidadeDeMedida` (`siglaUnidadeDeMedida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Produto`
--

LOCK TABLES `Produto` WRITE;
/*!40000 ALTER TABLE `Produto` DISABLE KEYS */;
INSERT INTO `Produto` VALUES (1,'Arroz Especial',100,'kg'),(2,'Feijão',50,'l'),(22,'vinte e dois',100,'g'),(33,'trinta e tres',33,'l '),(444,'4444',444,'un');
/*!40000 ALTER TABLE `Produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UnidadeDeMedida`
--

DROP TABLE IF EXISTS `UnidadeDeMedida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UnidadeDeMedida` (
  `siglaUnidadeDeMedida` varchar(2) NOT NULL,
  `nomeUnidadeDeMedida` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`siglaUnidadeDeMedida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UnidadeDeMedida`
--

LOCK TABLES `UnidadeDeMedida` WRITE;
/*!40000 ALTER TABLE `UnidadeDeMedida` DISABLE KEYS */;
INSERT INTO `UnidadeDeMedida` VALUES ('1','asdfasdfasdfdsa'),('g','Grama'),('kg','Quilograma'),('l','Litro'),('un','Unidade'),('xx','xxxxx');
/*!40000 ALTER TABLE `UnidadeDeMedida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `EnderecoCidadeEstadoPais`
--

/*!50001 DROP VIEW IF EXISTS `EnderecoCidadeEstadoPais`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `EnderecoCidadeEstadoPais` AS select `p`.`cpfPessoa` AS `cpfPessoa`,`p`.`nomePessoa` AS `nomePessoa`,`e`.`logradouro` AS `logradouro`,`e`.`numero` AS `numero`,`c`.`nomeCidade` AS `nomeCidade`,`est`.`nomeEstado` AS `nomeEstado`,`pais`.`nomePais` AS `nomePais` from ((((`Pessoa` `p` join `Endereco` `e`) join `Cidade` `c`) join `Estado` `est`) join `Pais` `pais`) where ((`p`.`EnderecoIdEndereco` = `e`.`idEndereco`) and (`e`.`CidadeIdCidade` = `c`.`idCidade`) and (`est`.`siglaEstado` = `c`.`siglaEstado`) and (`est`.`PaisIdPais` = `pais`.`idPais`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-18  6:08:33
