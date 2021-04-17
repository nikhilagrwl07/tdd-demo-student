DROP TABLE IF EXISTS student;

CREATE TABLE student (
  rollNumber VARCHAR(250)  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO student (rollNumber, first_name, last_name) VALUES
  ('1', 'Nikhil', 'Agrawal'),
  ('2', 'Ritu', 'Agrawal'),
  ('3', 'Dummy', 'Agrawal');