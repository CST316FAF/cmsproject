-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 17, 2015 at 06:46 AM
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

INSERT INTO `customer` (`P_ID`, `cfName`, `clName`, `cstreetAddy`, `cCity`, `cZip`, `cState`, `cPhone`) VALUES
(1, 'Fred', 'Durst', '123 Dbag street', 'New York City', 12345, 'New York', 1231231234);

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE IF NOT EXISTS `technician` (
  `P_ID` int(16) NOT NULL,
  `techID` int(16) NOT NULL,
  `techFName` varchar(16) NOT NULL,
  `techLName` varchar(16) NOT NULL,
  `techPhone` int(11) NOT NULL,
  `Location` longtext NOT NULL,
  `Type` mediumtext NOT NULL,
  `Appointment` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `technician`
--

INSERT INTO `technician` (`P_ID`, `techID`, `techFName`, `techLName`, `techPhone`, `Location`, `Type`, `Appointment`) VALUES
(1, 1, 'techFirstN', 'techLastN', 1234567890, '123 e main st', 'test', '2015-04-08'),
(1, 2, 'Joe', 'Blow', 2147483647, '12345 e asu st', 'test', '2015-04-30'),
(1, 3, 'Frank', 'Wong', 1232342345, '45 e main st', 'bug spraying', '2015-04-22'),
(1, 4, 'Davis', 'Happy', 1233454354, '89 S. Grumpy man st', 'Everything', '2015-04-13'),
(1, 5, 'Shaq', 'Oneil', 2147483647, '67 e guadalupe rd', 'Window Washing', '2015-05-20'),
(1, 6, 'Eyaad', 'Smith', 2147483647, '23 s. Mesa Dr.', 'Nothing', '2015-04-21'),
(1, 7, 'Danny', 'Trejo', 2147483647, '34 e kick ass st', 'Pool Cleaning', '2015-05-05'),
(1, 8, 'Mr', 'White', 2147483647, '67 New Mexico St.', 'Sell Meth', '2015-05-18'),
(1, 9, 'Jesse', 'Pinkman', 1232221342, '45 s power rd', 'Pest Control', '2015-05-01'),
(1, 10, 'Pink', 'Floyd', 1232343425, '69 E. When the Tigers st.', 'Pest Control', '2015-05-04');

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
 ADD PRIMARY KEY (`P_ID`);

--
-- Indexes for table `technician`
--
ALTER TABLE `technician`
 ADD PRIMARY KEY (`techID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`userID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
