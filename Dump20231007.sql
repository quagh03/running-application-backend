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
                                  `total_distance` decimal(10,2) DEFAULT NULL,
                                  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `eventsession` (
                                `session_id` int NOT NULL AUTO_INCREMENT,
                                `event_id` int DEFAULT NULL,
                                `activities_id` int DEFAULT NULL,
                                PRIMARY KEY (`session_id`),
                                KEY `event_id` (`event_id`),
                                KEY `activities_id` (`activities_id`),
                                CONSTRAINT `eventsession_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `running_events` (`event_id`),
                                CONSTRAINT `eventsession_ibfk_3` FOREIGN KEY (`activities_id`) REFERENCES `activities` (`activities_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DELIMITER //

CREATE TRIGGER update_running_event_metrics
    AFTER INSERT ON eventsession
    FOR EACH ROW
BEGIN
    DECLARE total_participant INT;
    DECLARE total_distance DECIMAL(10,2);

    -- Calculate the total participant count for the event
    SELECT COUNT(DISTINCT a.strava_id) INTO total_participant
    FROM activities a
    WHERE a.activities_id IN (SELECT es.activities_id FROM eventsession es WHERE es.event_id = NEW.event_id);

    -- Calculate the total distance for the event
    SELECT SUM(a.distance) INTO total_distance
    FROM activities a
    WHERE a.activities_id IN (SELECT es.activities_id FROM eventsession es WHERE es.event_id = NEW.event_id);

    -- Update the participant and km columns in the running_events table
    UPDATE running_events
    SET participant = total_participant,
        km = total_distance
    WHERE event_id = NEW.event_id;
END //

DELIMITER ;
