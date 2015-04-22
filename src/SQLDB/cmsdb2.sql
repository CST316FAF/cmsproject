-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 22, 2015 at 09:23 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cmsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `P_ID` int(16) NOT NULL,
  `CustomerID` int(128) NOT NULL,
  `cfName` varchar(16) NOT NULL,
  `clName` varchar(16) NOT NULL,
  `cstreetAddy` varchar(32) NOT NULL,
  `cCity` varchar(24) NOT NULL,
  `cZip` int(5) NOT NULL,
  `cState` varchar(24) NOT NULL,
  `cPhone` int(255) NOT NULL,
  `cEmail` varchar(48) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`P_ID`, `CustomerID`, `cfName`, `clName`, `cstreetAddy`, `cCity`, `cZip`, `cState`, `cPhone`, `cEmail`) VALUES
(1, 1, 'Fred', 'Durst', '123 Dbag street', 'New York City', 12345, 'New York', 1231231234, 'limp2thebiz@gmail.com'),
(1, 2, 'Katie', 'Blunt', '345 E. Power Rd', 'Gilbert', 85234, 'AZ', 2147483647, 'failedandfurious@gmail.com'),
(1, 3, 'Master', 'Chief', '857 S. Cooper Rd', 'Mesa', 85342, 'AZ', 2147483647, 'failedandfurious@gmail.com'),
(1, 4, 'Greg', 'Hunt', '112 E. Elliot Rd', 'Gilbert', 85786, 'AZ', 2147483647, 'failedandfurious@gmail.com'),
(1, 5, 'Billy', 'Bob', '438 Rittenhouse Rd.', 'Queen Creek', 85142, 'AZ', 2147483647, 'failedandfurious@gmail.com'),
(1, 6, 'Austin', 'Pruitt', '123 east st', 'Queen Creek', 85142, 'AZ', 1232342431, 'asdf@gmail.com'),
(1, 7, 'Davis', 'Kendry', '123 Wazzup St', 'Gilbert', 85234, 'AZ', 1232346758, 'davis@gmail.com'),
(1, 8, 'test', 'test', 'test', 'test', 12345, 'test', 1234543456, 'e@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE IF NOT EXISTS `jobs` (
  `P_ID` int(16) NOT NULL,
  `jobsID` int(32) NOT NULL,
  `CustomerID` int(32) NOT NULL,
  `title` text NOT NULL,
  `cost` int(255) NOT NULL,
  `date` date NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `problem` tinyint(1) NOT NULL,
  `notes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`P_ID`, `jobsID`, `CustomerID`, `title`, `cost`, `date`, `completed`, `problem`, `notes`) VALUES
(1, 1, 1, 'Pool cleaning', 400, '2015-04-16', 0, 0, 'Pool is extremely dirty, allow extra time to clean'),
(1, 2, 2, 'Window Cleaning', 250, '2015-04-30', 0, 0, 'Clean all windows, especially the second story ones.'),
(1, 3, 3, 'Yard work', 120, '2015-04-08', 1, 0, 'Cleanup customer''s front yard.');

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE IF NOT EXISTS `technician` (
  `P_ID` int(16) NOT NULL,
  `techID` int(16) NOT NULL,
  `jobsID` int(32) NOT NULL,
  `techFName` varchar(16) NOT NULL,
  `techLName` varchar(16) NOT NULL,
  `techPhone` int(128) NOT NULL,
  `techemail` varchar(32) NOT NULL,
  `Location` longtext NOT NULL,
  `tstate` varchar(24) NOT NULL,
  `tcity` varchar(24) NOT NULL,
  `Type` mediumtext NOT NULL,
  `Appointment` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `technician`
--

INSERT INTO `technician` (`P_ID`, `techID`, `jobsID`, `techFName`, `techLName`, `techPhone`, `techemail`, `Location`, `tstate`, `tcity`, `Type`, `Appointment`) VALUES
(1, 1, 0, 'techFirstN', 'techLastN', 1234567890, 'failedandfurious@gmail.com', '123 e main st', 'AZ', 'Phoenix', 'test', '2015-04-08'),
(1, 2, 0, 'Joe', 'Blow', 2147483647, 'failedandfurious@gmail.com', '12345 e asu st', 'AZ', 'Phoenix', 'test', '2015-04-30'),
(1, 3, 0, 'Frank', 'Wong', 1232342345, 'failedandfurious@gmail.com', '45 e main st', 'AZ', 'Mesa', 'bug spraying', '2015-04-22'),
(1, 4, 1, 'Davis', 'Happy', 1233454354, 'failedandfurious@gmail.com', '89 S. Grumpy man st', 'AZ', 'Gilbert', 'Everything', '2015-04-13'),
(1, 5, 0, 'Shaq', 'Oneil', 2147483647, 'failedandfurious@gmail.com', '67 e guadalupe rd', 'AZ', 'Phoenix', 'Window Washing', '2015-05-20'),
(1, 6, 0, 'Eyaad', 'Smith', 2147483647, 'failedandfurious@gmail.com', '23 s. Mesa Dr.', 'AZ', 'Mesa', 'Nothing', '2015-04-21'),
(1, 7, 0, 'Danny', 'Trejo', 2147483647, 'failedandfurious@gmail.com', '34 e kick ass st', 'AZ', 'Mesa', 'Pool Cleaning', '2015-05-05'),
(1, 8, 0, 'Mr', 'White', 2147483647, 'failedandfurious@gmail.com', '67 New Mexico St.', 'AZ', 'Phoenix', 'Sell Meth', '2015-05-18'),
(1, 9, 2, 'Jesse', 'Pinkman', 1232221342, 'failedandfurious@gmail.com', '45 s power rd', 'AZ', 'Mesa', 'Pest Control', '2015-05-01'),
(1, 10, 3, 'Pink', 'Floyd', 1232343425, 'failedandfurious@gmail.com', '69 E. When the Tigers st.', 'AZ', 'Gilbert', 'Pest Control', '2015-05-04');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `P_ID` int(16) NOT NULL,
  `userName` varchar(16) NOT NULL,
  `passWd` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`P_ID`, `userName`, `passWd`) VALUES
(1, 'test', 'testtest'),
(2, 'austin', 'austin123'),
(3, 'davis', 'davis123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
 ADD PRIMARY KEY (`jobsID`);

--
-- Indexes for table `technician`
--
ALTER TABLE `technician`
 ADD PRIMARY KEY (`techID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`P_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
