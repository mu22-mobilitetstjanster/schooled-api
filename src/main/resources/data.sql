DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
    id int AUTO_INCREMENT primary key,
    name varchar(255),
    description varchar(255),
    attendees int(11)
);


INSERT INTO courses (name, description, attendees) VALUES ("Math", "Awesome subject", "25");
INSERT INTO courses (name, description, attendees) VALUES ("Physics", "Great subject", "15");
INSERT INTO courses (name, description, attendees) VALUES ("Biology", "Interesting subject", "35");