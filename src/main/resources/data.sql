-- Insert data into Users table
INSERT INTO Users (username, password) VALUES
('user', '456'), -- Password: user
('admin', '123'); -- Password: admin

-- Insert data into Price table
INSERT INTO Price (price) VALUES
('10.00'),
('15.00'),
('20.00');

-- Insert data into Movies table
INSERT INTO Movie (movie_title, movie_rating, movie_length, price_id) VALUES
('Movie A', 'PG-13', 120, 1),
('Movie B', 'R', 150, 2),
('Movie C', 'PG', 105, 3);

-- Insert data into Rooms table
INSERT INTO Room (capacity) VALUES
(50),
(75),
(100);

-- Insert data into Schedule table
INSERT INTO Schedule (start_time, end_time, date, movie_id, room_id) VALUES
('12:00 PM', '2:00 PM', '2023-11-15', 1, 1),
('3:00 PM', '5:30 PM', '2023-11-15', 2, 2),
('7:00 PM', '9:00 PM', '2023-11-15', 3, 3);


-- Insert additional data into Movies table
INSERT INTO Movie (movie_title, movie_rating, movie_length, price_id) VALUES
('Movie D', 'PG', 110, 1),
('Movie E', 'R', 130, 2),
('Movie F', 'PG-13', 95, 3),
('Movie G', 'PG', 125, 1),
('Movie H', 'R', 140, 2),
('Movie I', 'PG-13', 100, 3),
('Movie J', 'PG', 115, 1),
('Movie K', 'R', 160, 2),
('Movie L', 'PG-13', 90, 3);

-- Insert additional data into Schedule table
INSERT INTO Schedule (start_time, end_time, date, movie_id, room_id) VALUES
('10:00 AM', '12:00 PM', '2023-11-15', 4, 1),
('1:00 PM', '3:30 PM', '2023-11-15', 5, 2),
('5:00 PM', '7:00 PM', '2023-11-15', 6, 3),
('8:00 PM', '10:00 PM', '2023-11-15', 7, 1),
('11:00 AM', '1:30 PM', '2023-11-16', 8, 2),
('2:30 PM', '4:30 PM', '2023-11-16', 9, 3),
('6:00 PM', '8:00 PM', '2023-11-16', 10, 1),
('9:00 PM', '11:00 PM', '2023-11-16', 1, 2),
('12:00 PM', '2:30 PM', '2023-11-17', 2, 3),
('3:30 PM', '6:00 PM', '2023-11-17', 3, 1);
