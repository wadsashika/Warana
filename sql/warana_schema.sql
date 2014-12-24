-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2014 at 11:26 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

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
-- Table structure for table `awards_and_achievements`
--

CREATE TABLE IF NOT EXISTS `awards_and_achievements` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `awards_and_achievements`
--

INSERT INTO `awards_and_achievements` (`id`, `candidate_id`, `description`) VALUES
(1, 44, 'This is sample achievement');

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE IF NOT EXISTS `candidate` (
`id` int(11) NOT NULL,
  `score` int(11) DEFAULT '0',
  `pic_path` varchar(150) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `marital_status` varchar(7) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`id`, `score`, `pic_path`, `name`, `address`, `email`, `gender`, `marital_status`) VALUES
(43, 79, NULL, 'Dewmi Anicitus', NULL, 'dewmi.anicitus@gmail.com', NULL, NULL),
(44, 65, 'test/Path', 'Nadeeshaan', 'Manavila,Walahanduwa,Galle', 'nsg@gmail.com', 'male', 'single'),
(45, 35, NULL, 'Dulanga Sashika', 'Galle, Sri Lanka', 'dulanga@warana.com', 'male', 'single'),
(46, 90, NULL, 'Anushka Mahesh', 'Galle, Sri Lanka', 'anushka@gmail.com', 'male', 'single');

-- --------------------------------------------------------

--
-- Table structure for table `candidate_technology`
--

CREATE TABLE IF NOT EXISTS `candidate_technology` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `technology_id` int(11) NOT NULL,
  `percentage` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `candidate_technology`
--

INSERT INTO `candidate_technology` (`id`, `candidate_id`, `technology_id`, `percentage`) VALUES
(1, 43, 2, 60),
(2, 43, 5, 30),
(3, 45, 2, 22),
(4, 45, 1, 34),
(5, 43, 3, 10),
(6, 46, 6, 45),
(7, 44, 5, 12),
(8, 46, 1, 55),
(9, 45, 6, 44),
(10, 44, 3, 88);

-- --------------------------------------------------------

--
-- Table structure for table `educational_details`
--

CREATE TABLE IF NOT EXISTS `educational_details` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `institution_name` varchar(100) DEFAULT NULL,
  `duration` varchar(100) DEFAULT NULL,
  `specialization` varchar(200) DEFAULT NULL,
  `grade` varchar(200) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `educational_details`
--

INSERT INTO `educational_details` (`id`, `candidate_id`, `institution_name`, `duration`, `specialization`, `grade`) VALUES
(1, 44, 'Manavila Upananda College', '1996-2006', 'GCE O/L', 'N/A'),
(2, 44, 'Mahinda College, Galle', '2007-2009', 'GCE A/L', 'N/A');

-- --------------------------------------------------------

--
-- Table structure for table `project_details`
--

CREATE TABLE IF NOT EXISTS `project_details` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `proj_name` varchar(150) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `project_details`
--

INSERT INTO `project_details` (`id`, `candidate_id`, `proj_name`, `description`) VALUES
(1, 44, 'Project 1', 'Sample Project1'),
(2, 44, 'Project 2', 'Sample Project2');

-- --------------------------------------------------------

--
-- Table structure for table `publications`
--

CREATE TABLE IF NOT EXISTS `publications` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` varchar(150) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `publications`
--

INSERT INTO `publications` (`id`, `candidate_id`, `name`, `description`) VALUES
(3, 44, 'Publication 1', 'Sample Publication1'),
(4, 44, 'Publication 2', 'Sample Publication2');

-- --------------------------------------------------------

--
-- Table structure for table `referee`
--

CREATE TABLE IF NOT EXISTS `referee` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `title` varchar(250) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `technology`
--

CREATE TABLE IF NOT EXISTS `technology` (
`id` int(11) NOT NULL,
  `technology` varchar(50) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `technology`
--

INSERT INTO `technology` (`id`, `technology`, `description`) VALUES
(1, 'java', 'Java'),
(2, 'C#', 'C# is an OOP language'),
(3, 'Ruby', 'Ruby'),
(4, 'Data Mining', 'Data Mining is used in combination with Information Retrieval'),
(5, 'Information Extraction', 'Information Extraction'),
(6, 'Natural Language Processing', 'Natural Language Processing');

-- --------------------------------------------------------

--
-- Table structure for table `uploaded_resumes`
--

CREATE TABLE IF NOT EXISTS `uploaded_resumes` (
`id` bigint(20) NOT NULL,
  `file_name` varchar(250) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `uploaded_resumes`
--

INSERT INTO `uploaded_resumes` (`id`, `file_name`, `date`, `status`) VALUES
(1, '090018T_Amarasinghe G. N.pdf', '2014-12-04', 'NOT PROCESSED'),
(2, '090052P_Bandara W.W.M.P.P.pdf', '2014-12-05', 'DELETED'),
(3, '090008M_ABEYWICKRAMA H.V.pdf', '2014-12-05', 'DELETED'),
(4, '090022B_Anicitus P.D.D.pdf', '2014-12-05', 'NOT PROCESSED'),
(5, '090028a_Ariyarathna A.L.pdf', '2014-12-05', 'NOT PROCESSED');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `user_name`, `password`, `email`, `path`, `previllage_level`) VALUES
(1, 'Nadeeshaan', 'Gunasinghe', 'nsg@gmail.com', 'nsg', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `work_experience`
--

CREATE TABLE IF NOT EXISTS `work_experience` (
`id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `company_name` varchar(150) NOT NULL,
  `post` varchar(150) DEFAULT NULL,
  `duration_from` varchar(25) DEFAULT NULL,
  `duration_to` varchar(25) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `work_experience`
--

INSERT INTO `work_experience` (`id`, `candidate_id`, `company_name`, `post`, `duration_from`, `duration_to`) VALUES
(1, 44, 'WSO2 Lanka (Pvt) Ltd', 'Trainee Software Engineer', '2013', '2014'),
(2, 44, 'Joomla!', 'Opensource Contributor', '2014 March', '2014 August');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `awards_and_achievements`
--
ALTER TABLE `awards_and_achievements`
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`);

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
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `project_details`
--
ALTER TABLE `project_details`
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `publications`
--
ALTER TABLE `publications`
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`);

--
-- Indexes for table `referee`
--
ALTER TABLE `referee`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `technology`
--
ALTER TABLE `technology`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploaded_resumes`
--
ALTER TABLE `uploaded_resumes`
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
 ADD PRIMARY KEY (`id`), ADD KEY `candidate_id_id` (`candidate_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `awards_and_achievements`
--
ALTER TABLE `awards_and_achievements`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `candidate_technology`
--
ALTER TABLE `candidate_technology`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `educational_details`
--
ALTER TABLE `educational_details`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `project_details`
--
ALTER TABLE `project_details`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `publications`
--
ALTER TABLE `publications`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `referee`
--
ALTER TABLE `referee`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `technology`
--
ALTER TABLE `technology`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `uploaded_resumes`
--
ALTER TABLE `uploaded_resumes`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `work_experience`
--
ALTER TABLE `work_experience`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
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
