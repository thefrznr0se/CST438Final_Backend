-- Insert data into Users table
INSERT INTO Users (username, password) VALUES
('user', '$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue'), -- Password: user
('admin', '$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW'); -- Password: admin

-- Insert data into Price table
INSERT INTO Price (price) VALUES
('10.00'),
('15.00'),
('20.00');

-- Insert data into Movies table
INSERT INTO Movies (movie_title, movie_rating, movie_length, price_id) VALUES
('Movie A', 'PG-13', 120, 1),
('Movie B', 'R', 150, 2),
('Movie C', 'PG', 105, 3);

-- Insert data into Rooms table
INSERT INTO Rooms (capacity) VALUES
(50),
(75),
(100);

-- Insert data into Schedule table
INSERT INTO Schedule (start_time, end_time, date, movie_id, room_id) VALUES
('12:00 PM', '2:00 PM', '2023-11-15', 1, 1),
('3:00 PM', '5:30 PM', '2023-11-15', 2, 2),
('7:00 PM', '9:00 PM', '2023-11-15', 3, 3);
