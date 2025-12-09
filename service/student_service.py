from db.connection import DBConnection 
from entity.student import Student 
from util.validators import validate_name, validate_age, validate_department

class StudentService:
    def add_student(self,student: Student):
        validate_name(student.name)
        validate_age(student.age)
        conn = DBConnection.get_connection()
        cursor = conn.cursor()
        try:
            query = "INSERT INTO Students (name,age, department)VALUES (%s,%s,%s)"
