CREATE DATBASE IF NOT EXISTS studentsDB;
USE StudentsDB;

CREATE TABLE IF NOT EXISTS students(
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    department VARCHAR(50)
);

CREATE TABLE IF EXISTS courses(
    course_id INT AUTO_INCREMENT PRIMARY KAY,
    course_name VARCHAR(100) NOT NULL 
);

CREATE TABLE IF NOT EXISTS enrollments(
    enroll_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY(coures_id) REFERENCES courses(course_id) ON DELETE CASCADE
)
