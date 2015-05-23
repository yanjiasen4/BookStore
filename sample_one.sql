-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-04-03 06:47:52
-- 服务器版本： 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sample_one`
--

-- --------------------------------------------------------

--
-- 表的结构 `book_list`
--

CREATE TABLE IF NOT EXISTS `book_list` (
  `BookName` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL DEFAULT '',
  `Price` float DEFAULT NULL,
  `Type` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `Author` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL DEFAULT '',
  `Pic` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL DEFAULT '' COMMENT '保存图书对应图片路径',
  `ID` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- 转存表中的数据 `book_list`
--

INSERT INTO `book_list` (`BookName`, `Price`, `Type`, `Author`, `Pic`, `ID`) VALUES
('Database System Concept', 99, 'Computer', 'Abraham Silberschatz', 'img/2.jpg', 1),
('Principle of Software Engineering', 48, 'Computer', 'Shen Beijun', 'img/3.jpg', 2),
('Principles of Computer System Design', 59, 'Computer', 'Jerome H. Saltzer', 'img/6.jpg', 3),
('程序员的自我修养', 65, 'computer', '俞甲子', 'img/5.jpg', 4);

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `U_ID` int(10) DEFAULT NULL,
  `B_ID` int(10) DEFAULT NULL,
  `Ispay` tinyint(1) NOT NULL DEFAULT '0',
  `Num` int(10) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(10) NOT NULL DEFAULT '0',
  `Name` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `Password` varchar(255) DEFAULT '',
  `Email` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`ID`, `Name`, `Password`, `Email`) VALUES
(0, 'Administer', 'ym19950823', '1158076176@qq.com'),
(1, 'John', 'jwq12345', 'mejohn@163.com'),
(2, 'Mac', 'mc19960512', 'mac@g.com'),
(3, 'testuser', 'public', '1158076176@qq.com'),
(4, '1', '2', '3'),
(5, 'yanjiasen4', 'asdffdsa', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_list`
--
ALTER TABLE `book_list`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `ID` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
