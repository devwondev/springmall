-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- sample 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `sample` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `sample`;


-- 테이블 sample의 구조를 덤프합니다. sample
CREATE TABLE IF NOT EXISTS `sample` (
  `sample_no` int(10) NOT NULL AUTO_INCREMENT,
  `sample_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sample_pw` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sample_no`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table sample.sample: ~50 rows (대략적)
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` (`sample_no`, `sample_id`, `sample_pw`) VALUES
	(1, 'Kurt', 'Prator'),
	(2, 'Yelena', 'Dingwall'),
	(3, 'Dilly', 'Linskey'),
	(4, 'Myra', 'Gilliam'),
	(5, 'Tommy', 'Gateley'),
	(6, 'Thor', 'Tapsell'),
	(7, 'Lyn', 'Brotheridge'),
	(8, 'Heriberto', 'Roskell'),
	(9, 'Jake', 'Sheard'),
	(10, 'Kirbie', 'Widdowfield'),
	(11, 'Kesley', 'Marginson'),
	(12, 'Elroy', 'Nannetti'),
	(13, 'Clark', 'Ulyat'),
	(14, 'Mariele', 'Tigwell'),
	(15, 'Saunder', 'Dowyer'),
	(16, 'Merrielle', 'Wharfe'),
	(17, 'Arnuad', 'Grange'),
	(18, 'Annabell', 'Guinn'),
	(19, 'Hortensia', 'Bahike'),
	(20, 'Shalna', 'Lorant'),
	(21, 'Sanderson', 'Climie'),
	(22, 'Franciska', 'Baumaier'),
	(23, 'Allyce', 'Tomas'),
	(24, 'Magnum', 'Crighton'),
	(25, 'Elise', 'Thornally'),
	(26, 'Hercules', 'Crenage'),
	(27, 'Benjamin', 'Anker'),
	(28, 'Svend', 'Danielsson'),
	(29, 'Glyn', 'Tommasuzzi'),
	(30, 'Angie', 'Miguet'),
	(31, 'York', 'Ethelstone'),
	(32, 'Ellie', 'Lamport'),
	(33, 'Hubert', 'Guymer'),
	(34, 'Garnet', 'Shorie'),
	(35, 'Stewart', 'Rainsbury'),
	(36, 'Diana', 'Pinchin'),
	(37, 'Meir', 'Gogarty'),
	(38, 'Beniamino', 'Titchmarsh'),
	(39, 'Darci', 'Tomasutti'),
	(40, 'Kasey', 'Ayerst'),
	(41, 'Lewiss', 'Kestin'),
	(42, 'Olvan', 'Toland'),
	(43, 'Felike', 'Woolford'),
	(44, 'Keith', 'De Cruce'),
	(45, 'Joby', 'Ranscombe'),
	(46, 'Rog', 'Ferronier'),
	(47, 'Laney', 'Mohammad'),
	(48, 'Anatol', 'Reboul'),
	(49, 'Amos', 'Behnecke'),
	(50, 'Sampson', 'Tuff');
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
