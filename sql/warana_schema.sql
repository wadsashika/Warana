-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2014 at 11:17 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `warana1`
--

-- --------------------------------------------------------

--
-- Table structure for table `awards_and_achievements`
--

CREATE TABLE IF NOT EXISTS `awards_and_achievements` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE IF NOT EXISTS `candidate` (
`id` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '-1',
  `pic_path` varchar(150) NOT NULL,
  `technologies` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `marital_status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `candidate_technology`
--

CREATE TABLE IF NOT EXISTS `candidate_technology` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `technology_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `educational_details`
--

CREATE TABLE IF NOT EXISTS `educational_details` (
  `id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `institution_name` varchar(100) NOT NULL,
  `duration` varchar(100) NOT NULL,
  `specialization` varchar(200) DEFAULT NULL,
  `grade` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_details`
--

CREATE TABLE IF NOT EXISTS `project_details` (
  `id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `proj_name` varchar(150) NOT NULL,
  `description` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `publications`
--

CREATE TABLE IF NOT EXISTS `publications` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `technology`
--

CREATE TABLE IF NOT EXISTS `technology` (
  `id` int(11) NOT NULL DEFAULT '0',
  `technology` varchar(150) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `path` varchar(150) NOT NULL,
  `previllage_level` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `work_experience`
--

CREATE TABLE IF NOT EXISTS `work_experience` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `company_name` varchar(150) NOT NULL,
  `post` varchar(150) NOT NULL,
  `duration` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `awards_and_achievements`
--
ALTER TABLE `awards_and_achievements`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `candidate`
--
ALTER TABLE `candidate`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `candidate_technology`
--
ALTER TABLE `candidate_technology`
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`), ADD KEY `technology_id_id` (`technology_id`);

--
-- Indexes for table `educational_details`
--
ALTER TABLE `educational_details`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `project_details`
--
ALTER TABLE `project_details`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `publications`
--
ALTER TABLE `publications`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `technology`
--
ALTER TABLE `technology`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `work_experience`
--
ALTER TABLE `work_experience`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `candidate_id_id` (`candidate_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `awards_and_achievements`
--
ALTER TABLE `awards_and_achievements`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `candidate_technology`
--
ALTER TABLE `candidate_technology`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `publications`
--
ALTER TABLE `publications`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `work_experience`
--
ALTER TABLE `work_experience`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `awards_and_achievements`
--
ALTER TABLE `awards_and_achievements`
ADD CONSTRAINT `awards_and_achievements_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`);

--
-- Constraints for table `candidate_technology`
--
ALTER TABLE `candidate_technology`
ADD CONSTRAINT `candidate_technology_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
ADD CONSTRAINT `candidate_technology_ibfk_2` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`id`);

--
-- Constraints for table `educational_details`
--
ALTER TABLE `educational_details`
ADD CONSTRAINT `educational_details_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`);

--
-- Constraints for table `project_details`
--
ALTER TABLE `project_details`
ADD CONSTRAINT `project_details_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`);

--
-- Constraints for table `publications`
--
ALTER TABLE `publications`
ADD CONSTRAINT `publications_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`);

--
-- Constraints for table `work_experience`
--
ALTER TABLE `work_experience`
ADD CONSTRAINT `work_experience_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
