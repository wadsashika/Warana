-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2015 at 03:50 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `warana`
--

-- --------------------------------------------------------

--
-- Table structure for table `concepts`
--

CREATE TABLE IF NOT EXISTS `concepts` (
  `conceptId` int(11) NOT NULL AUTO_INCREMENT,
  `conceptName` varchar(255) NOT NULL,
  PRIMARY KEY (`conceptId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `concepts`
--

INSERT INTO `concepts` (`conceptId`, `conceptName`) VALUES
(1, 'computer programming'),
(2, 'years industry experience'),
(3, 'added advantage'),
(4, 'application'),
(5, 'strong development skills'),
(6, 'integrated development environment'),
(7, 'minimum'),
(8, 'package'),
(9, 'web services'),
(10, 'xml'),
(11, 'new challenges'),
(12, 'affinity'),
(13, 'java or c\\/c'),
(14, 'eclipse'),
(15, 'experience and knowledge'),
(16, 'ide'),
(17, 'computer science\\/engineering or equivalent'),
(18, 'amicable personality'),
(19, 'software mathematica'),
(20, 'excellent communication and interpersonal skills'),
(21, 'java'),
(22, 'language'),
(23, 'experience and knowledge on web services and xml'),
(24, 'world');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
