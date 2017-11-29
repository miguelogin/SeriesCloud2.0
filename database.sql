-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 21-Nov-2016 às 01:10
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bancoserie`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `episodio`
--

CREATE TABLE `episodio` (
  `ID_EP` int(11) NOT NULL,
  `ID_Serie_FK` int(11) NOT NULL,
  `NOME_EP` varchar(100) NOT NULL,
  `TEMPO` int(5) NOT NULL,
  `GENERO` varchar(50) NOT NULL,
  `NUMERO_EP` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `episodio`
--

INSERT INTO `episodio` (`ID_EP`, `ID_Serie_FK`, `NOME_EP`, `TEMPO`, `GENERO`, `NUMERO_EP`) VALUES
(1, 1, 'QUANDO TUDO COMEÇOU - PILOTO', 20, 'TESTE', 1),
(5, 2, 'PRIMEIRO EP DA SEGUNDA SERIE TESTE', 45, 'DESCOBERTA', 1),
(6, 2, 'SEGUNDO EP DA SERIE NOVA', 42, 'DESCOBERTA', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ep_user`
--

CREATE TABLE `ep_user` (
  `ID_USER_FK` int(11) NOT NULL,
  `ID_EP_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `serie`
--

CREATE TABLE `serie` (
  `ID_SERIE` int(10) NOT NULL,
  `DT_INICIO_TEMP` date NOT NULL,
  `DT_FIM_TEMP` date NOT NULL,
  `TEMPORADA` int(3) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `DT_PILOTO` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `serie`
--

INSERT INTO `serie` (`ID_SERIE`, `DT_INICIO_TEMP`, `DT_FIM_TEMP`, `TEMPORADA`, `NOME`, `DT_PILOTO`) VALUES
(1, '2016-01-01', '2016-12-31', 1, 'SERIE 1', '2016-01-01'),
(2, '2016-10-02', '2016-10-04', 1, 'NOVA SERIE', '2016-10-02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `NOME_USER` varchar(50) NOT NULL,
  `LOGIN` varchar(16) NOT NULL,
  `SENHA` varchar(16) NOT NULL,
  `IDADE` int(3) NOT NULL,
  `TIPO` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`ID_USUARIO`, `NOME_USER`, `LOGIN`, `SENHA`, `IDADE`, `TIPO`) VALUES
(1, 'Cezar Vasconcelos', 'cezar', 'adminfoda', 20, 1),
(2, 'Miguel Deitos', 'miguel', 'admin2', 18, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `episodio`
--
ALTER TABLE `episodio`
  ADD PRIMARY KEY (`ID_EP`),
  ADD KEY `ID_serie` (`ID_Serie_FK`);

--
-- Indexes for table `ep_user`
--
ALTER TABLE `ep_user`
  ADD KEY `ID_USER_FK` (`ID_USER_FK`),
  ADD KEY `ID_EP_fk` (`ID_EP_FK`);

--
-- Indexes for table `serie`
--
ALTER TABLE `serie`
  ADD PRIMARY KEY (`ID_SERIE`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_USUARIO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `episodio`
--
ALTER TABLE `episodio`
  MODIFY `ID_EP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `serie`
--
ALTER TABLE `serie`
  MODIFY `ID_SERIE` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `episodio`
--
ALTER TABLE `episodio`
  ADD CONSTRAINT `episodio_ibfk_1` FOREIGN KEY (`ID_Serie_FK`) REFERENCES `serie` (`ID_SERIE`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ep_user`
--
ALTER TABLE `ep_user`
  ADD CONSTRAINT `ep_user_ibfk_1` FOREIGN KEY (`ID_USER_FK`) REFERENCES `usuario` (`ID_USUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ep_user_ibfk_2` FOREIGN KEY (`ID_EP_FK`) REFERENCES `episodio` (`ID_EP`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
