Name: AKshat Jhawar
Student ID: 101259602
Run the following code to create the table
CREATE TABLE Students(
	student_id SERIAL PRIMARY KEY,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	email TEXT UNIQUE NOT NULL,
	enrollment_date DATE
);

Then populate it using
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');

YouTube: https://youtu.be/XQ2_ECmVPok