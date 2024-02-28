create database radio_station;
USE radio_station;
CREATE TABLE `advertising_list` (
  `advertising_id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`advertising_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `interview_list` (
  `interview_id` int NOT NULL AUTO_INCREMENT,
  `respondent` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`interview_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `song_list` (
  `song_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `performer` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `working_broadcasters` (
  `broadcaster_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `resume` varchar(70) NOT NULL,
  `work_experience` int NOT NULL,
  PRIMARY KEY (`broadcaster_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


