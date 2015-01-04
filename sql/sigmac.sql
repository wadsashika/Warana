-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 23, 2012 at 04:23 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sigmac`
--

-- --------------------------------------------------------

--
-- Table structure for table `concepts`
--

CREATE TABLE `concepts` (
  `conceptId` int(11) NOT NULL auto_increment,
  `conceptName` varchar(255) NOT NULL,
  PRIMARY KEY  (`conceptId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

-- --------------------------------------------------------

--
-- Table structure for table `concept_doc`
--

CREATE TABLE `concept_doc` (
  `conceptId` int(11) NOT NULL,
  `docId` int(11) NOT NULL,
  `frequency` int(11) NOT NULL,
  `titleStrength` float NOT NULL,
  `importance` float NOT NULL,
  `strength` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `docId` int(11) NOT NULL auto_increment,
  `docURI` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `size` float NOT NULL,
  PRIMARY KEY  (`docId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `related_concept`
--

CREATE TABLE `related_concept` (
  `conceptId` int(11) NOT NULL,
  `relId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `relationships`
--

CREATE TABLE `relationships` (
  `relId` int(11) NOT NULL auto_increment,
  `type` varchar(255) NOT NULL,
  `frequency` int(11) NOT NULL,
  `is_aStrength` int(11) NOT NULL,
  `part_ofStrength` int(11) NOT NULL,
  `strength` float NOT NULL,
  `conceptId` int(11) NOT NULL,
  `isHead` tinyint(1) NOT NULL,
  PRIMARY KEY  (`relId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=121 ;
