CREATE TABLE Users (
	user_id int NOT NULL AUTO_INCREMENT,
	username varchar(255) UNIQUE,
	password varchar(255),
	PRIMARY KEY (user_id));

CREATE TABLE Price (
    price_id INT NOT NULL AUTO_INCREMENT,
    price VARCHAR(250),
    PRIMARY KEY (price_id);

CREATE TABLE Movies (
    movie_id int not NULL AUTO_INCREMENT,
    movie_title VARCHAR(250),
    movie_rating varchar(20),
    movie_length int,
    price_id int, 
    PRIMARY KEY (movie_id),
    FOREIGN KEY (price_id) REFERENCES Price (price_id) on delete cascade);

CREATE TABLE Rooms (
    room_id int not null AUTO_INCREMENT,
    capacity int,
    PRIMARY KEY (room_id));

CREATE TABLE Schedule (
    schedule_id int not null AUTO_INCREMENT,
    start_time VARCHAR(250),
    end_time VARCHAR(250),
    date DATE,
    movie_id int,
    room_id int,
    PRIMARY KEY (schedule_id),
    FOREIGN KEY (movie_id) REFERENCES Movies (movie_id) on delete cascade,
    FOREIGN KEY (room_id) REFERENCES Rooms (room_id) on delete cascade);