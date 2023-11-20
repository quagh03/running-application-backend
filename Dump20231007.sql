DROP DATABASE running_event;

CREATE DATABASE  IF NOT EXISTS `running_event`;
USE `running_event`;

CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `strava_id` bigint DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `user_profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `strava_id` (`strava_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `activities` (
  `activities_id` int NOT NULL AUTO_INCREMENT,
  `activities_stravaid` bigint NOT NULL,
  `activity_name` varchar(255) DEFAULT NULL,
  `distance` decimal(10,2) DEFAULT NULL,
  `activities_type` varchar(255) DEFAULT NULL,
  `strava_id` bigint DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`activities_id`),
  KEY `strava_id` (`strava_id`),
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`strava_id`) REFERENCES `members` (`strava_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `authority` (
  `username` varchar(255) NOT NULL,
  `ROLE` enum('ROLE_MEMBER','ROLE_ADMIN') NOT NULL,
  PRIMARY KEY (`username`,`ROLE`),
  CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`username`) REFERENCES `members` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `running_events` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `participant` int DEFAULT NULL,
  `km` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eventsession` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `activities_id` int DEFAULT NULL,
  `session_date` datetime DEFAULT NULL,
  `distance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `event_id` (`event_id`),
  KEY `member_id` (`member_id`),
  KEY `activities_id` (`activities_id`),
  CONSTRAINT `eventsession_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `running_events` (`event_id`),
  CONSTRAINT `eventsession_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `eventsession_ibfk_3` FOREIGN KEY (`activities_id`) REFERENCES `activities` (`activities_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
