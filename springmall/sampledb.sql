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
  `sample_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `sample_pw` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`sample_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 sample의 구조를 덤프합니다. samplefile
CREATE TABLE IF NOT EXISTS `samplefile` (
  `samplefile_no` int(10) NOT NULL AUTO_INCREMENT,
  `sample_no` int(10) NOT NULL,
  `samplefile_path` varchar(50) COLLATE utf8_bin NOT NULL,
  `samplefile_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `samplefile_ext` varchar(50) COLLATE utf8_bin NOT NULL,
  `samplefile_type` varchar(50) COLLATE utf8_bin NOT NULL,
  `samplefile_size` varchar(50) COLLATE utf8_bin NOT NULL,
  `samplefile_date` datetime NOT NULL,
  PRIMARY KEY (`samplefile_no`),
  KEY `FK_sample` (`sample_no`),
  CONSTRAINT `FK_sample` FOREIGN KEY (`sample_no`) REFERENCES `sample` (`sample_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
