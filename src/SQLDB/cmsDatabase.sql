-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 01, 2015 at 09:27 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cmsDatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `userID` int(16) NOT NULL,
  `cfName` varchar(16) NOT NULL,
  `clName` varchar(16) NOT NULL,
  `cstreetAddy` varchar(32) NOT NULL,
  `cCity` varchar(24) NOT NULL,
  `cZip` int(5) NOT NULL,
  `cState` varchar(24) NOT NULL,
  `cPhone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`userID`, `cfName`, `clName`, `cstreetAddy`, `cCity`, `cZip`, `cState`, `cPhone`) VALUES
(1, 'Fred', 'Durst', '123 Dbag street', 'New York City', 12345, 'New York', 1231231234);

-- --------------------------------------------------------

--
-- Table structure for table `statusnotes`
--

CREATE TABLE IF NOT EXISTS `statusnotes` (
  `id` int(11) NOT NULL,
  `notetitle` text NOT NULL,
  `notecontent` text NOT NULL,
  `technician` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statusnotes`
--

INSERT INTO `statusnotes` (`id`, `notetitle`, `notecontent`, `technician`) VALUES
(1, 'Test Title', 'This is a note test.', 'Bob');

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE IF NOT EXISTS `technician` (
  `userID` int(16) NOT NULL,
  `techID` int(16) NOT NULL,
  `techFName` varchar(16) NOT NULL,
  `techLName` varchar(16) NOT NULL,
  `techPhone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `technician`
--

INSERT INTO `technician` (`userID`, `techID`, `techFName`, `techLName`, `techPhone`) VALUES
(1, 1, 'techFirstN', 'techLastN', 1234567890);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(16) NOT NULL,
  `userName` varchar(16) NOT NULL,
  `passWd` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `passWd`) VALUES
(1, 'test', 'testtest'),
(2, 'austin', 'austin123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `statusnotes`
--
ALTER TABLE `statusnotes`
 ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `technician`
--
ALTER TABLE `technician`
 ADD PRIMARY KEY (`userID`), ADD UNIQUE KEY `techID` (`techID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`userID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
