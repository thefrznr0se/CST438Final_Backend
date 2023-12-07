-- Insert data into Users table
INSERT INTO Users (username, password, role) VALUES
('user', '$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue', 'USER'), -- Password: user
('admin', '$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW', 'ADMIN'); -- Password: admin

-- Insert data into Price table
INSERT INTO Price (price) VALUES
('10.00'),
('15.00'),
('20.00');

-- Insert data into Movies table
INSERT INTO Movie (movie_title, movie_rating, movie_length, price_id) VALUES
('Oppenheimer', 'PG-13', 120, 1),
('Barbie', 'R', 150, 2),
('Hidden Figures', 'PG', 105, 3);

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
('Alice in Wonderland', 'G', 110, 1),
('The Grinch', 'R', 130, 2),
('Interstellar', 'PG-13', 95, 3),
('Inside Out', 'PG', 125, 1),
('Finding Nemo', 'R', 140, 2),
('Iron Man', 'PG-13', 100, 3),
('Batman', 'PG', 115, 1),
('Harry Potter and the Goblet of Fire', 'R', 160, 2),
('Cars', 'PG-13', 90, 3),
('Star Wars: A New Hope', 'PG', 121, 1);

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
