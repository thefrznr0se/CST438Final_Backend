INSERT INTO Users (User_id, username, password)  VALUES 
(1, 'Aaron', '1234');

INSERT INTO Price (price_id, price)  VALUES 
(1, '$12.50');

INSERT INTO Movies (movie_id, movie_title, movie_rating, movie_length, price_id)  VALUES 
(1, 'Iron Man', '4/5', 135, 1);

INSERT INTO Rooms (room_id, capacity)  VALUES 
(101, 50);

INSERT INTO Schedule (schedule_id, start_time, end_time, date, movie_id, room_id)  VALUES 
(1, '2:00pm', '4:00pm', '2021-09-01', 1, 101);