-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2014 at 11:00 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cdcol`
--

-- --------------------------------------------------------

--
-- Table structure for table `cds`
--

CREATE TABLE IF NOT EXISTS `cds` (
  `titel` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `interpret` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `jahr` int(11) DEFAULT NULL,
`id` bigint(20) unsigned NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `cds`
--

INSERT INTO `cds` (`titel`, `interpret`, `jahr`, `id`) VALUES
('Beauty', 'Ryuichi Sakamoto', 1990, 1),
('Goodbye Country (Hello Nightclub)', 'Groove Armada', 2001, 4),
('Glee', 'Bran Van 3000', 1997, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cds`
--
ALTER TABLE `cds`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cds`
--
ALTER TABLE `cds`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;--
-- Database: `phpmyadmin`
--

-- --------------------------------------------------------

--
-- Table structure for table `pma_bookmark`
--

CREATE TABLE IF NOT EXISTS `pma_bookmark` (
`id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_column_info`
--

CREATE TABLE IF NOT EXISTS `pma_column_info` (
`id` int(5) unsigned NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin' AUTO_INCREMENT=53 ;

--
-- Dumping data for table `pma_column_info`
--

INSERT INTO `pma_column_info` (`id`, `db_name`, `table_name`, `column_name`, `comment`, `mimetype`, `transformation`, `transformation_options`) VALUES
(1, 'warana1', 'user', 'id', '', '', '_', ''),
(2, 'warana1', 'user', 'first_name', '', '', '_', ''),
(3, 'warana1', 'user', 'last_name', '', '', '_', ''),
(4, 'warana1', 'user', 'user_name', '', '', '_', ''),
(5, 'warana1', 'user', 'password', '', '', '_', ''),
(6, 'warana1', 'user', 'email', '', '', '_', ''),
(7, 'warana1', 'user', 'path', '', '', '_', ''),
(8, 'warana1', 'user', 'previllage_level', '', '', '_', ''),
(9, 'warana1', 'candidate', 'id', '', '', '_', ''),
(10, 'warana1', 'candidate', 'score', '', '', '_', ''),
(11, 'warana1', 'candidate', 'pic_path', '', '', '_', ''),
(12, 'warana1', 'educational_details', 'id', '', '', '_', ''),
(13, 'warana1', 'educational_details', 'candidate_id', '', '', '_', ''),
(14, 'warana1', 'educational_details', 'institution_name', '', '', '_', ''),
(15, 'warana1', 'educational_details', 'duration', '', '', '_', ''),
(47, 'warana1', 'candidate_technology', 'id', '', '', '_', ''),
(48, 'warana1', 'candidate_technology', 'candidate_id', '', '', '_', ''),
(45, 'warana1', 'educational_details', 'specialization', '', '', '_', ''),
(49, 'warana1', 'candidate_technology', 'technology_id', '', '', '_', ''),
(46, 'warana1', 'educational_details', 'grade', '', '', '_', ''),
(23, 'warana1', 'project_details', 'id', '', '', '_', ''),
(24, 'warana1', 'project_details', 'candidate_id', '', '', '_', ''),
(25, 'warana1', 'project_details', 'proj_name', '', '', '_', ''),
(26, 'warana1', 'project_details', 'description', '', '', '_', ''),
(27, 'warana1', 'work_experience', 'id', '', '', '_', ''),
(28, 'warana1', 'work_experience', 'candidate_id', '', '', '_', ''),
(29, 'warana1', 'work_experience', 'company_name', '', '', '_', ''),
(30, 'warana1', 'work_experience', 'post', '', '', '_', ''),
(31, 'warana1', 'work_experience', 'duration', '', '', '_', ''),
(32, 'warana1', 'publications', 'id', '', '', '_', ''),
(33, 'warana1', 'publications', 'candidate_id', '', '', '_', ''),
(34, 'warana1', 'publications', 'name', '', '', '_', ''),
(35, 'warana1', 'publications', 'description', '', '', '_', ''),
(36, 'warana1', 'awards_and_achievements', 'id', '', '', '_', ''),
(37, 'warana1', 'awards_and_achievements', 'candidate_id', '', '', '_', ''),
(38, 'warana1', 'awards_and_achievements', 'description', '', '', '_', ''),
(39, 'warana1', 'candidate', 'technologies', '', '', '_', ''),
(40, 'warana1', 'candidate', 'name', '', '', '_', ''),
(41, 'warana1', 'candidate', 'address', '', '', '_', ''),
(42, 'warana1', 'candidate', 'email', '', '', '_', ''),
(43, 'warana1', 'candidate', 'gender', '', '', '_', ''),
(44, 'warana1', 'candidate', 'marital_status', '', '', '_', ''),
(50, 'warana1', 'technology', 'id', '', '', '_', ''),
(51, 'warana1', 'technology', 'technology', '', '', '_', ''),
(52, 'warana1', 'technology', 'description', '', '', '_', '');

-- --------------------------------------------------------

--
-- Table structure for table `pma_designer_coords`
--

CREATE TABLE IF NOT EXISTS `pma_designer_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `v` tinyint(4) DEFAULT NULL,
  `h` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for Designer';

--
-- Dumping data for table `pma_designer_coords`
--

INSERT INTO `pma_designer_coords` (`db_name`, `table_name`, `x`, `y`, `v`, `h`) VALUES
('warana1', 'awards_and_achievements', 30, 479, 1, 1),
('warana1', 'candidate', 348, 218, 1, 1),
('warana1', 'educational_details', 48, 60, 1, 1),
('warana1', 'candidate_technology', 642, 124, 1, 1),
('warana1', 'project_details', 645, 264, 1, 1),
('warana1', 'publications', 48, 261, 1, 1),
('warana1', 'user', 353, 497, 1, 1),
('warana1', 'work_experience', 645, 480, 1, 1),
('warana1', 'technology', 358, 58, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pma_history`
--

CREATE TABLE IF NOT EXISTS `pma_history` (
`id` bigint(20) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_navigationhiding`
--

CREATE TABLE IF NOT EXISTS `pma_navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Table structure for table `pma_pdf_pages`
--

CREATE TABLE IF NOT EXISTS `pma_pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
`page_nr` int(10) unsigned NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin' AUTO_INCREMENT=6 ;

--
-- Dumping data for table `pma_pdf_pages`
--

INSERT INTO `pma_pdf_pages` (`db_name`, `page_nr`, `page_descr`) VALUES
('warana1', 1, 'no description'),
('warana1', 2, 'erd'),
('warana1', 3, 'fffff'),
('warana1', 4, 't'),
('warana1', 5, 'no description');

-- --------------------------------------------------------

--
-- Table structure for table `pma_recent`
--

CREATE TABLE IF NOT EXISTS `pma_recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma_recent`
--

INSERT INTO `pma_recent` (`username`, `tables`) VALUES
('root', '[{"db":"warana1","table":"candidate_technology"},{"db":"warana1","table":"technology"},{"db":"warana1","table":"educational_details"},{"db":"warana1","table":"candidate"},{"db":"warana1","table":"work_experience"},{"db":"warana1","table":"publications"},{"db":"warana1","table":"project_details"},{"db":"warana1","table":"awards_and_achievements"},{"db":"warana1","table":"user"},{"db":"phpmyadmin","table":"pma_column_info"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma_relation`
--

CREATE TABLE IF NOT EXISTS `pma_relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Table structure for table `pma_savedsearches`
--

CREATE TABLE IF NOT EXISTS `pma_savedsearches` (
`id` int(5) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_coords`
--

CREATE TABLE IF NOT EXISTS `pma_table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float unsigned NOT NULL DEFAULT '0',
  `y` float unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

--
-- Dumping data for table `pma_table_coords`
--

INSERT INTO `pma_table_coords` (`db_name`, `table_name`, `pdf_page_number`, `x`, `y`) VALUES
('warana1', 'awards_and_achievements', 1, 1, 160),
('warana1', 'candidate', 1, 116, 95),
('warana1', 'educational_details', 1, 16, 20),
('warana1', 'candidate_technology', 5, 214, 41),
('warana1', 'project_details', 1, 215, 88),
('warana1', 'publications', 1, 16, 87),
('warana1', 'user', 1, 120, 200),
('warana1', 'work_experience', 1, 215, 160),
('warana1', 'awards_and_achievements', 2, 1, 160),
('warana1', 'candidate', 2, 116, 95),
('warana1', 'educational_details', 2, 16, 20),
('warana1', 'candidate', 5, 116, 73),
('warana1', 'project_details', 2, 215, 88),
('warana1', 'publications', 2, 16, 87),
('warana1', 'user', 2, 120, 200),
('warana1', 'work_experience', 2, 215, 160),
('warana1', 'awards_and_achievements', 3, 1, 160),
('warana1', 'candidate', 3, 116, 95),
('warana1', 'educational_details', 3, 16, 20),
('warana1', 'project_details', 3, 215, 88),
('warana1', 'publications', 3, 16, 87),
('warana1', 'user', 3, 120, 200),
('warana1', 'work_experience', 3, 215, 160),
('warana1', 'awards_and_achievements', 4, 1, 160),
('warana1', 'candidate', 4, 116, 95),
('warana1', 'educational_details', 4, 16, 20),
('warana1', 'awards_and_achievements', 5, 10, 160),
('warana1', 'project_details', 4, 215, 88),
('warana1', 'publications', 4, 16, 87),
('warana1', 'user', 4, 120, 200),
('warana1', 'work_experience', 4, 215, 160),
('warana1', 'educational_details', 5, 16, 20),
('warana1', 'project_details', 5, 215, 88),
('warana1', 'publications', 5, 16, 87),
('warana1', 'technology', 5, 119, 19),
('warana1', 'user', 5, 118, 166),
('warana1', 'work_experience', 5, 215, 160);

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_info`
--

CREATE TABLE IF NOT EXISTS `pma_table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_uiprefs`
--

CREATE TABLE IF NOT EXISTS `pma_table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Table structure for table `pma_tracking`
--

CREATE TABLE IF NOT EXISTS `pma_tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) unsigned NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_userconfig`
--

CREATE TABLE IF NOT EXISTS `pma_userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data for table `pma_userconfig`
--

INSERT INTO `pma_userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2014-11-08 09:00:14', '{"collation_connection":"utf8mb4_general_ci"}');

-- --------------------------------------------------------

--
-- Table structure for table `pma_usergroups`
--

CREATE TABLE IF NOT EXISTS `pma_usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Table structure for table `pma_users`
--

CREATE TABLE IF NOT EXISTS `pma_users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma_designer_coords`
--
ALTER TABLE `pma_designer_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_history`
--
ALTER TABLE `pma_history`
 ADD PRIMARY KEY (`id`), ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma_navigationhiding`
--
ALTER TABLE `pma_navigationhiding`
 ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
 ADD PRIMARY KEY (`page_nr`), ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma_recent`
--
ALTER TABLE `pma_recent`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_relation`
--
ALTER TABLE `pma_relation`
 ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`), ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma_table_coords`
--
ALTER TABLE `pma_table_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma_table_info`
--
ALTER TABLE `pma_table_info`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_table_uiprefs`
--
ALTER TABLE `pma_table_uiprefs`
 ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma_tracking`
--
ALTER TABLE `pma_tracking`
 ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma_userconfig`
--
ALTER TABLE `pma_userconfig`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_usergroups`
--
ALTER TABLE `pma_usergroups`
 ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma_users`
--
ALTER TABLE `pma_users`
 ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT for table `pma_history`
--
ALTER TABLE `pma_history`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
MODIFY `page_nr` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT;--
-- Database: `test`
--
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
--
-- Database: `webauth`
--

-- --------------------------------------------------------

--
-- Table structure for table `user_pwd`
--

CREATE TABLE IF NOT EXISTS `user_pwd` (
  `name` char(30) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `pass` char(32) COLLATE latin1_general_ci NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `user_pwd`
--

INSERT INTO `user_pwd` (`name`, `pass`) VALUES
('xampp', 'wampp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_pwd`
--
ALTER TABLE `user_pwd`
 ADD PRIMARY KEY (`name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
